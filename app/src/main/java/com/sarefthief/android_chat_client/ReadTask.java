package com.sarefthief.android_chat_client;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.ObjectInputStream;

import client.Message;

public class ReadTask extends AsyncTask<Void, Void, Void>
{
    private SocketApplication socketApp;
    private ChatActivity chatAct;
    private ObjectInputStream objectIn;
    private Message message;

    ReadTask(SocketApplication socketApp, ChatActivity chatAct)
    {
        this.chatAct = chatAct;
        this.socketApp = socketApp;
        objectIn = socketApp.getObjectIn();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            message = (Message) objectIn.readObject();
        } catch (IOException ex){

        } catch (ClassNotFoundException ex){

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ReadTask readTask = new ReadTask(socketApp, chatAct);
        readTask.execute();
        chatAct.populateMessage(message);
    }
}
