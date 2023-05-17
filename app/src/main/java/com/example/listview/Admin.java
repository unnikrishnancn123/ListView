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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Admin extends AppCompatActivity {
    private static final String TAG = Admin.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.custom_drow);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#075E54"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Get a reference to the RecyclerView in the layout
        RecyclerView adminRecyclerView = findViewById(R.id.adminrecyclerView);

        // Create a new LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adminRecyclerView.setLayoutManager(layoutManager);

        // Create a new adapter for the RecyclerView
        SampleDtaAdapter adapter = new SampleDtaAdapter(this);
        adminRecyclerView.setAdapter(adapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



    SampleDtaService service = retrofit.create(SampleDtaService.class);

    Call<List<SampleDta>> call = service.getSampleData();
call.enqueue(new Callback<List<SampleDta>>() {
        @Override
        public void onResponse(Call<List<SampleDta>> call, Response<List<SampleDta>> response) {
            if (response.isSuccessful()) {
                List<SampleDta> sampleDtas = response.body();
                adapter.addSampleDtas(sampleDtas);
            } else if (response.code() == 404) {
                // Handle 404 error
                Log.e(TAG, "Resource not found: " + response.raw().request().url());
                // Display error message to the user or take other appropriate action
            } else {
                // Handle other error codes
                int errorCode = response.code();
                String errorMessage = response.message();
                Log.e(TAG, "Error " + errorCode + ": " + errorMessage);
                // Display error message to the user or take other appropriate action based on error code and message
            }
        }


        @Override
        public void onFailure (Call < List < SampleDta >> call, Throwable t){
        Log.e(TAG, "Error getting JSON data", t);
        Toast.makeText(Admin.this, "Error getting JSON data", Toast.LENGTH_SHORT).show();
    }
    });

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