package com.sarefthief.android_chat_client;

import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread
{
    private String address;
    private int port;

    ConnectionThread(String address, int port)
    {
        this.address = address;
        this.port = port;
    }

    public void run()
    {
        try{
            Socket socket = new Socket(address, port);
        } catch (IOException ex){

        }
    }
}
