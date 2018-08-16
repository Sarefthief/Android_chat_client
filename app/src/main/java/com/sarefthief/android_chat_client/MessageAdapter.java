package com.sarefthief.android_chat_client;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import client.Message;

public class MessageAdapter extends ArrayAdapter<Message> {

    private View chat;

    public MessageAdapter(Context context, ArrayList<Message> users) {
        super(context, 0, users);
        chat = View.inflate(context, R.layout.activity_chat, null);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        final Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }
        TextView tvMessage = convertView.findViewById(R.id.tvMessage);
        TextView tvUsername = convertView.findViewById(R.id.tvUserName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        final TextView messageText = chat.findViewById(R.id.messageText);

        try{
            SpannableString ss = new SpannableString(message.getUsername());
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    messageText.setText("/w " + message.getUsername());
                }
            };
            ss.setSpan(clickableSpan, 0, message.getUsername().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvMessage.setText(message.getMessage());
            tvUsername.setText(ss);
            tvUsername.setMovementMethod(LinkMovementMethod.getInstance());
            tvUsername.setHighlightColor(Color.TRANSPARENT);
            tvDate.setText(dateFormat.format(message.getDate()));
        } catch (NullPointerException ex){
            tvDate.setText("error");
        }

        return convertView;
    }
}
