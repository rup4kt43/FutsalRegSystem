package com.example.futsalregsystem.ShowOpen.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.futsalregsystem.R;
import com.example.futsalregsystem.ShowOpen.Adapter.ShowOpenAdapter;
import com.example.futsalregsystem.ShowOpen.Contracts.ShowOpenContracts;
import com.example.futsalregsystem.ShowOpen.DTO.ShowOpenDTO;
import com.example.futsalregsystem.ShowOpen.Presenter.ShowOpenPresenter;

import java.util.ArrayList;

public class ShowOpenView extends AppCompatActivity implements ShowOpenContracts.view {
    private ShowOpenPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ShowOpenPresenter(this);
        presenter.loadOpenSchedule();

        setContentView(R.layout.activity_admin_show_open);


        recyclerView = findViewById(R.id.rv_admin_showOpen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void loadOpenList(ArrayList<ShowOpenDTO> openList) {
        ShowOpenAdapter showOpenAdapter = new ShowOpenAdapter(this, openList);
        recyclerView.setAdapter(showOpenAdapter );
    }
}
