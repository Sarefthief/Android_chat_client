package com.sarefthief.android_chat_client;

import android.app.Application;

import java.net.Socket;

public class SocketApplication extends Application
{
    private Socket socket;

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }
}
