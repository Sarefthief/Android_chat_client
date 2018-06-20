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
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        addressText = findViewById(R.id.addressText);
        portText = findViewById(R.id.portText);
        text = findViewById(R.id.nameText);
    }

    public void onClick(View view) {
        int port = 0;
        String address;
        if ((!addressText.getText().toString().equals(""))&&(!portText.getText().toString().equals(""))){
            address = addressText.getText().toString();
            port = Integer.parseInt(portText.getText().toString());
            new ConnectionThread(this, address, port).start();
        } else {
            portText.setError("Specify port number");
            addressText.setError("Specify server address");
        }
    }

    public TextView getAddressText() {
        return addressText;
    }

    public TextView getPortText() {
        return portText;
    }
}
