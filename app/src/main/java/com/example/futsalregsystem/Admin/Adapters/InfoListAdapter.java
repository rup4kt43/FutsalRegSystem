package com.example.futsalregsystem.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.futsalregsystem.Admin.DTO.AdminDTO;
import com.example.futsalregsystem.Admin.View.AdminView;
import com.example.futsalregsystem.R;

import java.util.ArrayList;

public class InfoListAdapter extends RecyclerView.Adapter {
    private final AdminView context;
    private final ArrayList<AdminDTO> list;
    private TextView tv_name, tv_phone, tv_time,tv_date;
    private Button btn_confirm;


    public InfoListAdapter(AdminView adminView, ArrayList<AdminDTO> infoList) {
        this.context = adminView;
        this.list = infoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_view_rv_containeer, viewGroup, false);
        return new CustomInfoListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        tv_name.setText(list.get(i).getName());
        tv_phone.setText(list.get(i).getNumber());
        tv_time.setText(list.get(i).getTime());
        tv_date.setText(list.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class CustomInfoListHolder extends RecyclerView.ViewHolder {
        public CustomInfoListHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
            tv_date = view.findViewById(R.id.tv_date);
            tv_time = view.findViewById(R.id.tv_time);
            btn_confirm = view.findViewById(R.id.btn_confirm);

            btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    context.onRecyclerItemConfirmed(pos,list.get(pos).getTime(),list.get(pos).getName(),list.get(pos).getNumber(),list.get(pos).getDate());
                }
            });

        }
    }
}
