package api;

import model.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageApi {
    @POST("messages")
    Call<Message> addMessage(@Body Message message);
}
