package com.example.fragments.helper;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fragments.R;
import com.example.fragments.server.Search;

import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder>{
    private ArrayList<Search> searches= new ArrayList();
    private Operations operations;
    // RecyclerView recyclerView;
    public DisplayAdapter(  Operations operations) {

        this.operations =operations;
    }
    public void setupData(ArrayList<Search> searches){
        this.searches = searches;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.searched_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Search myListData = searches.get(position);
        holder.title.setText(myListData.getTitle());
        holder.detail.setText(myListData.getYear());
        Glide.with(holder.title.getContext()).load(myListData.getPoster()).placeholder(R.drawable.abc_vector_test)
                .into(holder.imageView);
//        holder.image.setImageResource(myListData.getPoster());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operations.detailBtn(myListData);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operations.deleteBtn(myListData);
            }
        });
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operations.updateBtn(myListData, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return searches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title,detail;
        public Button deleteBtn, updateBtn;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.poster);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.detail = (TextView) itemView.findViewById(R.id.detail);
            this.deleteBtn = (Button) itemView.findViewById(R.id.deleteBtn);
            this.updateBtn = (Button) itemView.findViewById(R.id.updateBtn);
            relativeLayout= (RelativeLayout) itemView.findViewById(R.id.detailBtn);
        }
    }
}
