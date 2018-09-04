package com.sarefthief.android_chat_client;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.ObjectOutputStream;

import server.Message;

public class WriterTask extends AsyncTask<Void, Void, Void>
{
    private SocketApplication socketApp;
    private Message message;
    private ObjectOutputStream objectOut;

    WriterTask(SocketApplication socketApp, Message message)
    {
        this.message = message;
        this.socketApp = socketApp;
        objectOut = socketApp.getObjectOut();
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try{
            objectOut.writeObject(message);
        } catch (IOException ex){

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
    }
}
