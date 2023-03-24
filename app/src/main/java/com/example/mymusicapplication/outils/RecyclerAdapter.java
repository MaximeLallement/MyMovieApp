package com.example.mymusicapplication.outils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusicapplication.R;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<String> filmNameList;

    public RecyclerAdapter(ArrayList<String> filmNameList){
        this.filmNameList = filmNameList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private Button btn;

        public MyViewHolder(final View view){
            super(view);

            btn = view.findViewById(R.id.btnList);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = filmNameList.get(position);
        holder.btn.setText(name);


    }

    @Override
    public int getItemCount() {
        return filmNameList.size();
    }

}
