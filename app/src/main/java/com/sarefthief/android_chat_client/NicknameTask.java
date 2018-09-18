package com.sarefthief.android_chat_client;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.commons.lang3.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class NicknameTask extends AsyncTask<Void, Void, Void>
{
    private TextView nameText;
    private NicknameActivity nickAct;
    private boolean check = false;
    private String nickname;
    private PrintWriter writer;
    private BufferedReader reader;
    private SocketApplication socketApp;

    NicknameTask(NicknameActivity nickAct,SocketApplication socketApp,String nickname)
    {
        this.socketApp = socketApp;
        this.nickAct = nickAct;
        this.nickname = nickname;
    }

    protected Void doInBackground(Void... params)
    {
        try{
            socketApp.setObjectOut(new ObjectOutputStream(socketApp.getSocket().getOutputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socketApp.getSocket().getOutputStream()),true);
            reader = new BufferedReader(new InputStreamReader(socketApp.getSocket().getInputStream()));

            writer.println(nickname);
            String checkStr = reader.readLine();
            if (!checkStr.equals("nickname is occupied")){
                check = true;
            }

            socketApp.setObjectIn(new ObjectInputStream(socketApp.getSocket().getInputStream()));

            String nameOfUserInChat;

            while(true){
                nameOfUserInChat = reader.readLine();
                if(!nameOfUserInChat.equals("Server")){
                    socketApp.addUserInAList(nameOfUserInChat);
                } else {
                    break;
                }
            }



        } catch (IOException ex){

        }

        return null;
    }

    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        if(!check){
            nameText.setError("Nickname is occupied");
        } else {
            socketApp.setNickname(nickname);
            Intent intent = new Intent(nickAct, ChatActivity.class);
            nickAct.startActivity(intent);
        }
    }
}
