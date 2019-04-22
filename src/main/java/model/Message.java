package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class Message {
    protected long id;
    protected long roomId;
    protected long userId;

    protected String messageDate;
    protected String messageText;
    protected String userLogin;

    public Message(){

    }

    public Message(long id, long roomId, long userId, String messageDate, String messageText, String userLogin) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.messageDate = messageDate;
        this.messageText = messageText;
        this.userLogin = userLogin;
    }

    public Message(String json){
        JSONObject parseObject = new JSONObject(json);
        setId(parseObject.getLong("id"));
        setRoomId(parseObject.getLong("roomId"));
        setUserId(parseObject.getLong("userId"));
        setMessageDate(parseObject.getString("messageDate"));
        setMessageText(parseObject.getString("messageText"));
        setUserLogin(parseObject.getString("userLogin"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
