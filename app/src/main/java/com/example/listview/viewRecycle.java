package com.example.listview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class viewRecycle   extends RecyclerView.ViewHolder {
    TextView from;

    TextView date;
    TextView message;
    View view;

    viewRecycle(View itemView)
    {
        super(itemView);

       from = (TextView) itemView
                .findViewById(R.id.tvFrom);
        date = (TextView) itemView
                .findViewById(R.id.tvDate);
      message = (TextView) itemView
                .findViewById(R.id.tvMessage);

        view  = itemView;
    }
}
