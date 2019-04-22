package api;

import model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("users/username/{login}")
    Call<User> getByLogin(@Path("login") String login);

    @POST("users")
    Call<User> addUser(@Body User user);
}
