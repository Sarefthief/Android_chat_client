package com.sarefthief.android_chat_client;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import server.Message;

public class MessageAdapter extends ArrayAdapter<Message> {

    private EditText messageText;
    private String nickname;

    public MessageAdapter(Context context, ArrayList<Message> messages, EditText messageText, String nickname) {
        super(context, 0, messages);
        this.messageText = messageText;
        this.nickname = nickname;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }
        TextView tvMessage = convertView.findViewById(R.id.tvMessage);
        final TextView tvUsername = convertView.findViewById(R.id.tvUserName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvPrivate = convertView.findViewById(R.id.tvPrivate);

        try{
            tvUsername.setText(message.getUsername());
            if((message.getMessage().charAt(0) == '/')&&(message.getMessage().charAt(1) == 'w')){
                String[] words = message.getMessage().split(" ");
                StringBuilder builder = new StringBuilder(message.getMessage());
                tvMessage.setText(builder.substring(4 + words[1].length()));
                if(words[1].equals(nickname)){
                    tvPrivate.setText(R.string.messageAdapterPrivate);
                } else {
                    tvPrivate.setText("(To " + words[1] + ")");
                }
                tvUsername.setTextColor(Color.parseColor("#C400AB"));
                tvUsername.getPaint().setUnderlineText(true);
            } else {
                tvMessage.setText(message.getMessage());
                tvPrivate.setText("");
                if (tvUsername.getText().equals("Server")){
                    tvUsername.setTextColor(Color.RED);
                    tvUsername.getPaint().setUnderlineText(false);
                } else {
                    tvUsername.setTextColor(Color.BLUE);
                    tvUsername.getPaint().setUnderlineText(true);
                }
            }
            tvDate.setText(dateFormat.format(message.getDate()));
            tvUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if((!tvUsername.getText().equals(nickname))&&(!tvUsername.getText().equals("Server"))){
                        messageText.setText("/w " + tvUsername.getText() + " ");
                        messageText.setSelection(messageText.getText().length());
                    }
                }
            });
        } catch (NullPointerException ex){
            tvDate.setText("error");
        }

        return convertView;
    }
}
