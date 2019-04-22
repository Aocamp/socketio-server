package api;

import model.Room;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RoomApi {
    @GET("rooms/{id}")
    Call<Room> getRoomById(@Path("id") Long id);

    @GET("rooms/user/{id}")
    Call<Room> getRoomByUserId(@Path("id") Long id);

    @POST("rooms")
    Call<Room> addRoom(@Body Room room);

}
