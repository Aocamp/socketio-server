package service;

import controller.RetrofitClient;
import model.User;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class UserService {
    public Long getUserIdByLogin(User user){
        Call<User> userInfo = RetrofitClient
                .getInstance()
                .getUserApi()
                .getByLogin(user.getUserLogin());

        Long id = null;

        try {
            User userExecute = userInfo.execute().body();
            assert userExecute != null;
            id = userExecute.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return id;
    }

    public void addUser(User user){
        Call<User> add = RetrofitClient
                .getInstance()
                .getUserApi()
                .addUser(user);

        try {
            Response<User> userResponse = add.execute();
            if (userResponse.isSuccessful()){
                System.out.println("new user " + user.getUserLogin() + "add");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
