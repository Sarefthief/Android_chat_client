package com.sarefthief.android_chat_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import client.Message;

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

        arrayOfUsers = new ArrayList<>();
        adapter = new MessageAdapter(this, arrayOfUsers);
        ListView listView = findViewById(R.id.messages);
        listView.setAdapter(adapter);

        ReadTask readTask = new ReadTask(socketApp, this);
        readTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    public void onClick(View view)
    {
        if(!messageText.getText().toString().equals("")){
            Message message = new Message(socketApp.getNickname(), messageText.getText().toString(),new Date());
            WriterTask writerTask = new WriterTask(socketApp, message);
            writerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            if((message.getMessage().charAt(0) == '/')&&(message.getMessage().charAt(1) == 'w')){
                String[] words = message.getMessage().split(" ");
                StringBuilder builder = new StringBuilder(message.getMessage());
                message.setMessage(builder.substring(4 + words[1].length()));
                message.setUsername(message.getUsername() + "(To " + words[1] + ")");
            }
            populateMessage(message);
            messageText.setText("");
        }
    }

    public void populateMessage(Message message)
    {
        adapter.add(message);
    }
}
