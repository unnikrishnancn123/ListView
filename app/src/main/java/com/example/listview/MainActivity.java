package com.example.listview;


import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity   {

    // Declare variables
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    ViewPagerFragmentAdapter adapter;



    // array for tab labels
    private String[] labels = new String[]{"", "Chats", "Status","Call"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // call function to initialize views
        init();

        // bind and set tabLayout to viewPager2 and set labels for every tab
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(labels[position]);


        }).attach();
        tabLayout.getTabAt(0).setIcon(R.drawable.people_foreground);
        // set default position to 1 instead of default 0
        viewPager2.setCurrentItem(1, false);



        //actionbar


        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Whatsapp");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#075E54"));
        actionBar.setBackgroundDrawable(colorDrawable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




    }

    private void init() {
        // initialize tabLayout
        tabLayout = findViewById(R.id.tab_layout);

        // initialize viewPager2
        viewPager2 = findViewById(R.id.view_pager2);
        // create adapter instance
        adapter = new ViewPagerFragmentAdapter(this);
        // set adapter to viewPager2
        viewPager2.setAdapter(adapter);

        // remove default elevation of actionbar
        getSupportActionBar().setElevation(0);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }



    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        // return fragments at every position
        @NonNull
        @Override
        public Fragment createFragment(int position) {

            switch (position) {
                case 0:
                    return new Community();// calls fragment
                case 1:
                    return new ChatFragment(); // chats fragment
                case 2:
                    return new StatusFragment(); // status fragment
                case 3:
                    return new CallFragment();
            }
            return new ChatFragment(); //chats fragment
        }

        // return total number of tabs
        @Override
        public int getItemCount() {
            return labels.length;
        }
    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        int id = item.getItemId();
            if(id==R.id.search) {

                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
            }
            else if(id==R.id.camera){


                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);


//            Toast.makeText(this, "camera Clicked", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_account) {
                Intent i = new Intent(getBaseContext(), Admin.class);
                startActivity(i);
            }
            else if(id==R.id.nav_settings){
                Toast.makeText(this, "settings Clicked", Toast.LENGTH_SHORT).show();
            }
            else if(id==R.id.nav_logout){
                showLogoutConfirmationDialog();
            }

        return super.onOptionsItemSelected(item);
    }
    private void logout() {

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


