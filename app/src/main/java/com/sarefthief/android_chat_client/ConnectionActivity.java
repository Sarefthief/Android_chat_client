package com.sarefthief.android_chat_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;

public class ConnectionActivity extends AppCompatActivity {

    private TextView addressText;
    private TextView portText;
    private TextView nameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        addressText = findViewById(R.id.addressText);
        portText = findViewById(R.id.portText);
    }

    public void onClick(View view) {
        String address = addressText.getText().toString();
        int port = Integer.parseInt(portText.getText().toString());
        new ConnectionThread(address, port).start();
    }
}
