package service;

import controller.RetrofitClient;
import model.Room;
import model.User;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class RoomService {
    public String getRoomNameById(Room room){
        Call<Room> get = RetrofitClient
                .getInstance()
                .getRoomApi()
                .getRoomById(room.getId());

        String name = null;

        try {
            Room roomExecute = get.execute().body();
            assert roomExecute != null;
            name = roomExecute.getRoomName();
            System.out.println("room name: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public Room getRoomByUserId(User user){
        Call<Room> roomInfo = RetrofitClient
                .getInstance()
                .getRoomApi()
                .getRoomByUserId(user.getId());

        Room room = null;

        try {
            room = roomInfo.execute().body();
            assert room != null;
            System.out.println("room id: " + room.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return room;
    }

    public void addRoom(Room room){
        Call<Room> add = RetrofitClient
                .getInstance()
                .getRoomApi()
                .addRoom(room);

        try {
            Response<Room> roomResponse = add.execute();
            if (roomResponse.isSuccessful()){
                System.out.println("new room " + room.getRoomName() + "add");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
