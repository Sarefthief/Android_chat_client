package com.sarefthief.android_chat_client;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

class ConnectionTask extends AsyncTask<Void, Void, Void>
{
    private String address;
    private int port;
    private boolean connection = false;
    private TextView error;
    private String errorText;
    private ConnectionActivity conAct;
    private SocketApplication socketApp;

    ConnectionTask(SocketApplication socketApp, ConnectionActivity conAct, String address, int port, TextView error)
    {
        this.conAct = conAct;
        this.error = error;
        this.address = address;
        this.port = port;
        this.socketApp = socketApp;
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        try {
            socketApp.setSocket(new Socket(address, port));
            connection = true;
        } catch (UnknownHostException ex){
            errorText = "This server is unavailable";
        } catch (IOException ex) {
            errorText = "Connection error";
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        if(!connection){
            error.setText(errorText);
        } else {
            Intent intent = new Intent(conAct, NicknameActivity.class);
            conAct.startActivity(intent);
        }
    }
}
