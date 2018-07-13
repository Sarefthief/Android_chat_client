package com.sarefthief.android_chat_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, ArrayList<Message> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }
        TextView tvMessage = convertView.findViewById(R.id.tvMessage);
        TextView tvUsername = convertView.findViewById(R.id.tvUserName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);

        try{
            tvMessage.setText(message.getMessage());
            tvUsername.setText(message.getUsername());
            tvDate.setText(dateFormat.format(message.getDate()));
        } catch (NullPointerException ex){
            tvDate.setText("error");
        }

        return convertView;
    }
}
