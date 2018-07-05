package com.sarefthief.android_chat_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    TextView chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chat = findViewById(R.id.chatView);
        chat.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onClick(View view)
    {

    }
}
