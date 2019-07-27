package com.example.futsalregsystem.MembershipRequest.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.futsalregsystem.MembershipRequest.DTO.MemberReqDTO;
import com.example.futsalregsystem.MembershipRequest.View.MemberReqView;
import com.example.futsalregsystem.R;

import java.util.ArrayList;

public class MemberReqAdapter extends RecyclerView.Adapter {

    private TextView tv_name, tv_phone, tv_time;
    private Button btn_accept;


    private final MemberReqView context;
    private final ArrayList<MemberReqDTO> details;

    public MemberReqAdapter(MemberReqView memberReqView, ArrayList<MemberReqDTO> details) {
        this.context = memberReqView;
        this.details = details;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_view_rv_containeer, viewGroup, false);
        return new CustomDetailsAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        tv_time.setText(details.get(i).getTime());
        tv_name.setText(details.get(i).getName());
        tv_phone.setText(details.get(i).getContact());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    private class CustomDetailsAdapter extends RecyclerView.ViewHolder {
        public CustomDetailsAdapter(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
            tv_time = view.findViewById(R.id.tv_time);
            btn_accept = view.findViewById(R.id.btn_accept);

         /*   btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    context.onRecyclerItemConfirmed(pos, list.get(pos).getTime(), list.get(pos).getName(), list.get(pos).getNumber());
                }
            });*/


         btn_accept.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int pos = getAdapterPosition();
                 //TODO INTERFACE
             }
         });

        }
    }
}
