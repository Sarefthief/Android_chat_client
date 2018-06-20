package com.sarefthief.android_chat_client;

import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread
{
    private String address;
    private int port;
    private Socket socket;
    private ConnectionActivity conAct;

    ConnectionThread(ConnectionActivity conAct,String address, int port)
    {
        this.conAct = conAct;
        this.address = address;
        this.port = port;
    }

    public void run()
    {
        try{
            socket = new Socket(address, port);
        } catch (IOException ex){

        } catch (IllegalStateException ex){
            conAct.getAddressText().setError("сас");
            conAct.getPortText().setError("сас");
        }
    }
}
