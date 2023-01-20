package com.example.fragments.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fragments.R;
import com.example.fragments.rooms.RoomDbClient;
import com.example.fragments.server.Search;

public class fragment2 extends Fragment {

    View view;
    private String detailId;
    public fragment2(String detail){
        this.detailId =detail;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        Search search = RoomDbClient.getInstance(getContext()).getDao().getSearchedDetail(detailId);

        TextView textView = view.findViewById(R.id.dataFrom1);
        ImageView imageView = view.findViewById(R.id.poster);
        textView.setText(search.toString());


        Glide.with(getContext()).load(search.getPoster()).placeholder(R.drawable.abc_vector_test)
                .into(imageView);

        return view;
    }
}