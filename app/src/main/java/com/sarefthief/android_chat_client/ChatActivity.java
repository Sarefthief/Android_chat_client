package com.sarefthief.android_chat_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Date;
import client.Message;

public class ChatActivity extends AppCompatActivity {

    ArrayList<Message> messages;
    MessageAdapter adapter;
    private SocketApplication socketApp;
    private EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        socketApp = ((SocketApplication) getApplication());
        messageText = findViewById(R.id.messageText);

        messages = new ArrayList<>();
        adapter = new MessageAdapter(this, messages, messageText, socketApp.getNickname());
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
            populateMessage(message);
            messageText.setText("");
        }
    }

    public void populateMessage(Message message)
    {
        adapter.add(message);
    }
}
