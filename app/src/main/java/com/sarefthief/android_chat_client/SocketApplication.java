package com.sarefthief.android_chat_client;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketApplication extends Application
{
    private Socket socket;
    private ObjectInputStream ObjectIn;
    private ObjectOutputStream ObjectOut;

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
}
