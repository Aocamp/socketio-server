package service;

import controller.RetrofitClient;
import model.Message;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class MessageService {
    public void addMessage(Message message){
        Call<Message> add = RetrofitClient
                .getInstance()
                .getMessageApi()
                .addMessage(message);

        try {
            Response<Message> messageResponse = add.execute();
            if (messageResponse.isSuccessful()){
                System.out.println("message: " + message.getMessageText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
