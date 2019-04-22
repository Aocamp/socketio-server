package server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import model.Message;
import model.Room;
import model.User;

import service.MessageService;
import service.RoomService;
import service.UserService;

public class Server implements Runnable{
    private String roomName;

    private Long userId;
    private Long roomId;

    @Override
    public void run() {
        server();
    }

    private void server(){
        Configuration config = new Configuration();
        config.setHostname("192.168.43.220");
        config.setPort(8888);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener((client)->{
            System.out.println("client connected");
            onConnection(server);
        });

        onNewMessage(server);

        server.start();
    }

    private void onConnection(SocketIOServer server){
        server.addEventListener("connection", User.class, (userClient, user, ackRequest) -> {
            server.addEventListener("room", Room.class, (roomClient, room, ackRequest2) -> {
                System.out.println("username: " + user.getUserLogin());

                UserService userService = new UserService();
                userId = userService.getUserIdByLogin(user);

                if (userId == null ){
                    userService.addUser(user);
                    userId = userService.getUserIdByLogin(user);
                }

                user.setId(userId);

                RoomService roomService = new RoomService();
                Room roomInfo = roomService.getRoomByUserId(user);
                roomId = roomInfo.getId();

                if (roomId == null){
                    room.setUserId(userId);
                    roomService.addRoom(room);
                    Room getRoom = roomService.getRoomByUserId(user);

                    roomName = getRoom.getRoomName();
                    roomId = getRoom.getId();

                    roomClient.joinRoom(roomName);
                    room.setId(roomId);

                    server.getRoomOperations(roomName).sendEvent("roomId",room);
                    System.out.println("user connect to room: " + roomName);
                }else {
                    room.setId(roomId);
                    roomName = roomService.getRoomNameById(room);
                    roomClient.joinRoom(roomName);

                    server.getRoomOperations(roomName).sendEvent("roomId", room);
                    System.out.println("user connect to room: " + roomName);
                }
            });
        });
    }

    private void onNewMessage(SocketIOServer server){
        server.addEventListener("new message", Message.class,
                (client, message, ackRequest) -> {
                    message.setRoomId(roomId);
                    message.setUserId(userId);

                    MessageService service = new MessageService();
                    service.addMessage(message);
                    server.getRoomOperations(roomName).sendEvent("message", message);
                });
    }
}
