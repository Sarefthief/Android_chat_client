package com.sarefthief.android_chat_client;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

class ConnectionTask extends AsyncTask<Void, Void, Boolean>
{
    private String address;
    private int port;
    private boolean connection = false;
    private TextView error;
    private String errorText;
    private ConnectionActivity conAct;

    ConnectionTask(ConnectionActivity conAct,String address, int port, TextView error)
    {
        this.conAct = conAct;
        this.error = error;
        this.address = address;
        this.port = port;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params)
    {
        try {
            Socket socket = new Socket(address, port);
            connection = true;
        } catch (UnknownHostException ex){
            errorText = "This server is unavailable";
        } catch (IOException ex) {
            errorText = "Connection error";
        }
        return connection;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if(!connection){
            error.setText(errorText);
        } else {
            Intent intent = new Intent(conAct, NicknameActivity.class);
            conAct.startActivity(intent);
        }
    }
}
