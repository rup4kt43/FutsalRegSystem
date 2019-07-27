package com.example.futsalregsystem.ShowOpen.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.futsalregsystem.R;
import com.example.futsalregsystem.ShowOpen.DTO.ShowOpenDTO;
import com.example.futsalregsystem.ShowOpen.View.ShowOpenView;

import java.util.ArrayList;

public class ShowOpenAdapter extends RecyclerView.Adapter {
    private final ShowOpenView context;
    private final ArrayList<ShowOpenDTO> openList;
    private TextView date, side, time;

    public ShowOpenAdapter(ShowOpenView showOpenView, ArrayList<ShowOpenDTO> openList) {
        this.context = showOpenView;
        this.openList = openList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.showopen_containeer, viewGroup, false);
        return new CustomShowOpenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        date.setText(openList.get(i).getDate());
        time.setText(openList.get(i).getTime());

        String fullSide = openList.get(i).getSide();
        String cropSide = fullSide.length() < 2 ? fullSide : fullSide.substring(0, 2);

        side.setText(cropSide);
    }

    @Override
    public int getItemCount() {
        return openList.size();
    }

    private class CustomShowOpenHolder extends RecyclerView.ViewHolder {
        public CustomShowOpenHolder(View view) {
            super(view);
            date = view.findViewById(R.id.tv_so_date);
            time = view.findViewById(R.id.tv_so_time);
            side = view.findViewById(R.id.tv_so_side);
        }
    }
}
