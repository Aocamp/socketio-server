package controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import api.MessageApi;
import api.RoomApi;
import api.UserApi;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.43.220:8080/com.api/rest/";

    private static RetrofitClient instance;

    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public MessageApi getMessageApi(){
        return retrofit.create(MessageApi.class);
    }

    public UserApi getUserApi(){
        return retrofit.create(UserApi.class);
    }

    public RoomApi getRoomApi(){
        return retrofit.create(RoomApi.class);
    }
}
