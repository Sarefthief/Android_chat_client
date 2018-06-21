package com.sarefthief.android_chat_client;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class NicknameTask extends AsyncTask<Void, Void, Void>
{
    private TextView nameText;
    private NicknameActivity nickAct;
    private boolean check = false;
    private String nickname;
    private String error;
    private PrintWriter writer;
    private BufferedReader reader;

    NicknameTask(NicknameActivity nickAct,SocketApplication socketApp,String nickname)
    {
        this.nickAct = nickAct;
        this.nickname = nickname;
        try{
            writer = new PrintWriter(new OutputStreamWriter(socketApp.getSocket().getOutputStream()),true);
            reader = new BufferedReader(new InputStreamReader(socketApp.getSocket().getInputStream()));
        } catch (IOException ex){
            error = "Connection error";
        }
    }

    protected Void doInBackground(Void... params)
    {
        writer.println(nickname);
        try{
            String checkStr = reader.readLine();
            if (checkStr.equals("0")){
                error = "Nickname is occupied";
            } else {
                check = true;
            }
        } catch (IOException ex){
            error = "Connection error";
        }

        return null;
    }

    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        if(!check){
            nameText.setError(error);
        } else {
            Intent intent = new Intent(nickAct, ChatActivity.class);
            nickAct.startActivity(intent);
        }
    }
}
