package com.kampen.riks.app.rikskampen.leader.activity.fragments.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kampen.riks.app.rikskampen.R;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {



    private ChatView mChatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mChatView=findViewById(R.id.chat_view);


        mChatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
            @Override
            public boolean sendMessage(ChatMessage chatMessage){
                // perform actual message sending
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
