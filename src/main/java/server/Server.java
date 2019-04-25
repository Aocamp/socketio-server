package server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import model.Message;
import model.Room;
import model.User;

import service.MessageService;
import service.RoomService;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable{
    private String roomName;

    private Long userId;
    private Long roomId;

    private Map<Long, String> userMap = new HashMap<>();

    private MessageService messageService = new MessageService();
    private UserService userService = new UserService();
    private RoomService roomService = new RoomService();

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

        sendMessage(server);

        server.start();
    }

    private void onConnection(SocketIOServer server){

        server.addEventListener("connection", User.class, (userClient, user, ackRequest) -> {
            System.out.println("username: " + user.getUserLogin());

            userId = userService.getUserIdByLogin(user.getUserLogin());
            System.out.println("id " + userId);
            if (userId.equals(0L)){
                User newUser = userService.addUser(user);
                userId = newUser.getId();
            }
        });

        server.addEventListener("room", Room.class, (roomClient, room, ackRequest2) -> {
            System.out.println(userId);
            Room roomInfo = roomService.getRoomByUserId(userId);
            roomId = roomInfo.getId();

            if (roomId.equals(0L)){
                room.setUserId(userId);
                Room newRoom = roomService.addRoom(room);
                roomName = newRoom.getRoomName();
                roomId = newRoom.getId();
            }else {
                roomName = roomService.getRoomNameById(roomId);
            }

            roomClient.joinRoom(roomName);
            room.setId(roomId);
            System.out.println("user connect to room: " + roomName);

            User user = new User();
            user.setId(userId);
            server.getRoomOperations(roomName).sendEvent("roomAndUserId", room, user);

            userMap.put(userId, roomName);
        });
    }

    private void sendMessage(SocketIOServer server){
        server.addEventListener("new message", Message.class,
                (client, message, ackRequest) -> {
            messageService.addMessage(message);
            server.getRoomOperations(userMap.get(message.getUserId())).sendEvent("message", message);
        });
    }
}
