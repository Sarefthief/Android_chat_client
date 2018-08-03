package com.sarefthief.android_chat_client;

import android.content.Context;
import android.text.SpannableString;
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
        TextView messageText = findViewById(R.id.messageText);

        try{
            SpannableString ss = new SpannableString(message.getUsername());
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    startActivity(new Intent(MyActivity.this, NextActivity.class));
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };
            ss.setSpan(clickableSpan, 22, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            TextView textView = (TextView) findViewById(R.id.messageText);
            textView.setText(ss);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setHighlightColor(Color.TRANSPARENT);
            tvMessage.setText(message.getMessage());
            tvUsername.setText(message.getUsername());
            tvDate.setText(dateFormat.format(message.getDate()));
        } catch (NullPointerException ex){
            tvDate.setText("error");
        }

        return convertView;
    }
}
