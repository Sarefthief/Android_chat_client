package com.sarefthief.android_chat_client;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketApplication extends Application
{
    private Socket socket;
    private ObjectInputStream ObjectIn;
    private ObjectOutputStream ObjectOut;
    private String nickname;
    private ArrayList<String> usersInChat = new ArrayList<>();

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }

    public ObjectInputStream getObjectIn()
    {
        return ObjectIn;
    }

    public ObjectOutputStream getObjectOut()
    {
        return ObjectOut;
    }

    public void setObjectIn(ObjectInputStream objectIn)
    {
        ObjectIn = objectIn;
    }

    public void setObjectOut(ObjectOutputStream objectOut)
    {
        ObjectOut = objectOut;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<String> getUsersInChat() {
        return usersInChat;
    }

    public void addUserInAList(String user) {
        usersInChat.add(user);
    }
}
