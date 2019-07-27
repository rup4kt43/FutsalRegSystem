package com.example.futsalregsystem.MembershipRequest.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.futsalregsystem.MembershipRequest.Adapter.MemberReqAdapter;
import com.example.futsalregsystem.MembershipRequest.Contracts.MemberReqContracts;
import com.example.futsalregsystem.MembershipRequest.DTO.MemberReqDTO;
import com.example.futsalregsystem.MembershipRequest.Presenter.MemberReqPresenter;
import com.example.futsalregsystem.R;

import java.util.ArrayList;

public class MemberReqView extends AppCompatActivity implements MemberReqContracts.view {

    RecyclerView recyclerView;
    private MemberReqPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        presenter = new MemberReqPresenter(this);

        presenter.loadRequest();

        recyclerView = findViewById(R.id.rv_memberReqView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void loadAdapter(ArrayList<MemberReqDTO> details) {
        MemberReqAdapter memberReqAdapter = new MemberReqAdapter(this, details);
        recyclerView.setAdapter(memberReqAdapter);
    }
}
