package com.sarefthief.android_chat_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    ArrayList<Message> arrayOfUsers;
    MessageAdapter adapter;
    private SocketApplication socketApp;
    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        socketApp = ((SocketApplication) getApplication());
        messageText = findViewById(R.id.messageText);

        arrayOfUsers = new ArrayList<Message>();
        adapter = new MessageAdapter(this, arrayOfUsers);
        ListView listView = findViewById(R.id.messages);
        listView.setAdapter(adapter);

        ReadTask readTask = new ReadTask(socketApp, this);
        readTask.execute();
    }

    public void onClick(View view)
    {
        if(!messageText.getText().toString().equals("")){
            Message message = new Message(socketApp.getNickname(), messageText.getText().toString());
            WriterTask writerTask = new WriterTask(socketApp, message);
            writerTask.execute();
            populateMessage(message);
        } else {
            messageText.setError("Specify the nickname");
        }
    }

    public void populateMessage(Message message)
    {
        adapter.add(message);
    }
}
