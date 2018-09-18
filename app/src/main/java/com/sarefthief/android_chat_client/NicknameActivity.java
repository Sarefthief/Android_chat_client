package com.sarefthief.android_chat_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class NicknameActivity extends AppCompatActivity
{

    private TextView nameText;
    private SocketApplication socketApp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        nameText = findViewById(R.id.nameText);
        socketApp = ((SocketApplication) getApplication());
    }

    public void onClick(View view)
    {
        if(!nameText.getText().toString().equals("")){
            NicknameTask nicknameTask = new NicknameTask(this, socketApp, nameText.getText().toString());
            nicknameTask.execute();
        } else {
            nameText.setError("Specify the nickname");
        }
    }
}
