package com.example.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        MessagesAdapter.setOnItemClickListener(new MessagesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent(getActivity(), chat.class);
                startActivity(i);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //message
        ArrayList<Message> messages = new ArrayList<Message>();
        messages= getData();
        View view= inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView msgView = (RecyclerView)view.findViewById(R.id.recyclerView);
        msgView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessagesAdapter msgAdapter = new MessagesAdapter(this.getLayoutInflater(), messages);
        msgView.setAdapter(msgAdapter);
        return view;
    }

    private ArrayList<Message> getData()
    {
        ArrayList<Message> list = new ArrayList<>();
        list.add(new Message("unni","hi unni","12/01/2023"));

        list.add(new Message("unni","hi unni","12/01/2023"));
        list.add(new Message("gjghj","gfdgdfhdfhh","12/01/2023"));
        list.add(new Message("jghg","ghfghfghfghf","12/01/2023"));
        list.add(new Message("unni","hgfghfghfghf","12/01/2023"));
        list.add(new Message("ghjghj","jhhhhhhh","12/01/2023"));
        list.add(new Message("unni","jhhghjjghj","12/01/2023"));
        list.add(new Message("unni","hi unni","12/01/2023"));
        list.add(new Message("unni","hi unni","12/01/2023"));
        list.add(new Message("unni","hi unni","12/01/2023"));
        list.add(new Message("unni","hi unni","12/01/2023"));
        list.add(new Message("unni","hi unni","12/01/2023"));
        list.add(new Message("unni","hi unni","12/01/2023"));
        return list;
    }


}