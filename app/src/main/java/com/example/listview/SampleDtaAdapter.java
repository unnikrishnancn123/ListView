package com.example.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SampleDtaAdapter extends RecyclerView.Adapter<SampleDtaAdapter.ViewHolder> {

    private List<SampleDta> sampleDtas;

    public SampleDtaAdapter(Context context) {
        sampleDtas = new ArrayList<>();
    }

    public void addSampleDtas(List<SampleDta> sampleDtas) {
        this.sampleDtas.addAll(sampleDtas);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SampleDtaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == 0) {
            // inflate sent message layout
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin, parent, false);
        } else {
            // inflate received message layout
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin, parent, false);
        }
        return new SampleDtaAdapter.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull SampleDtaAdapter.ViewHolder holder, int position) {
        SampleDta message =sampleDtas.get(position);
        holder.user_Id.setText(String.valueOf(message.getUserId()));
        holder.s_id.setText(String.valueOf(message.getId()));
        holder.s_title.setText(message.getTitle());
    }

    @Override
    public int getItemCount() {
        return sampleDtas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (sampleDtas.get(position).isCompleted()) {
            return 0; // sent message
        } else {
            return 1; // received message
        }
    }

    public void addMessage(SampleDta message) {
        sampleDtas.add(message);

    }

    public void clearMessages() {
        sampleDtas.clear();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_Id;
        TextView s_id;
        TextView s_title;

        ViewHolder(View itemView) {
            super(itemView);
            user_Id = itemView.findViewById(R.id.user_id);
            s_id = itemView.findViewById(R.id.s_id);
            s_title = itemView.findViewById(R.id.title);
        }

        void bind(SampleDta message) {
            user_Id.setText(String.valueOf(message.getUserId()));
            s_id.setText(String.valueOf(message.getId()));
            s_title.setText(message.getTitle());
            Log.d("ViewHolder", "message.getUserId(): " + message.getUserId());
            Log.d("ViewHolder", "message.getId(): " + message.getId());
            Log.d("ViewHolder", "message.getTitle(): " + message.getTitle());
        }
    }
}
