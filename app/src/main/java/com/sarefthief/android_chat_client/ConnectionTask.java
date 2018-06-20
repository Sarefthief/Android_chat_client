package com.sarefthief.android_chat_client;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

class ConnectionTask extends AsyncTask<Void, Void, Void>
{
    private String address;
    private int port;
    private boolean connection = false;
    private TextView error;
    private String errorText;

    ConnectionTask(String address, int port, TextView error)
    {
        this.error = error;
        this.address = address;
        this.port = port;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void...args)
    {
        try {
            Socket socket = new Socket(address, port);
            connection = true;
        } catch (UnknownHostException ex){
            errorText = "This server is unavailable";
        } catch (IOException ex) {
            errorText = "Connection error";
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(!connection){
            error.setText(errorText);
        }
    }
}
