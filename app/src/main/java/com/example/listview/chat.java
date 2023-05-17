package com.example.listview;


import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class chat extends AppCompatActivity {


    private EditText messageEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.custom_drow);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#075E54"));
        actionBar.setBackgroundDrawable(colorDrawable);
// Get a reference to the RecyclerView in the layout
        RecyclerView chatRecyclerView = findViewById(R.id.chatrecyclerView);

// Create a new LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chatRecyclerView.setLayoutManager(layoutManager);

// Create a new adapter for the RecyclerView


        ChatAdapter adapter = new ChatAdapter(this);
        chatRecyclerView.setAdapter(adapter);

        setMessage(adapter);
        EditText messageEditText = findViewById(R.id.messageEditText);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageEditText.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    // Create a new ChatMessage object with the message text and a timestamp
                    ChatMessage message = new ChatMessage(messageText,"12:00 pm", true);
                    // Add the ChatMessage object to your chat message list
                   adapter.addMessage(message);

                    messageEditText.setText("");
                }
            }

        });
    }

    private void setMessage(ChatAdapter adapter) {
        try {
            Gson gson = new Gson();
            String json = loadJsonEvent("msg.json");
            ChatMessages chatMessages = gson.fromJson(json, ChatMessages.class);
            adapter.setMessages(chatMessages.getMessages());
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    public String loadJsonEvent(String messages) throws IOException {
        if (messages == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        AssetManager assetManager = getAssets();

        try (InputStream inputStream = assetManager.open(messages)) {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            Log.e(TAG, "Error loading JSON event", e);
            throw e;
        }
    }

    public static class ChatMessages {
        private List<ChatMessage> messages;

        public List<ChatMessage> getMessages() {
            return messages;
        }

        public void setMessages(List<ChatMessage> messages) {
            this.messages = messages;
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.chat_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        int id = item.getItemId();

        if(id==R.id.phone) {
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.videocall){


            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);


//            Toast.makeText(this, "camera Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.nav_account){
            Toast.makeText(this, "account Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.nav_settings){
            Toast.makeText(this, "settings Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.nav_logout){
            showLogoutConfirmationDialog();

        }
         if(id==android.R.id.home) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();
        // Add any other logout logic you need here
    }
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}