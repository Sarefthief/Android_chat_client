package com.sarefthief.android_chat_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    TextView chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //chat = findViewById(R.id.chatView);
        //chat.setMovementMethod(new ScrollingMovementMethod());

        ArrayList<Message> arrayOfUsers = new ArrayList<Message>();
        MessageAdapter adapter = new MessageAdapter(this, arrayOfUsers);
        ListView listView = findViewById(R.id.messages);
        listView.setAdapter(adapter);
    }

    public void onClick(View view)
    {

    }
}
