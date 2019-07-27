package com.example.futsalregsystem.ShowOpen.Presenter;

import com.example.futsalregsystem.ShowOpen.Contracts.ShowOpenContracts;
import com.example.futsalregsystem.ShowOpen.DTO.ShowOpenDTO;
import com.example.futsalregsystem.ShowOpen.Model.ShowOpenModel;
import com.example.futsalregsystem.ShowOpen.View.ShowOpenView;

import java.util.ArrayList;

public class ShowOpenPresenter implements ShowOpenContracts.presenter {

    private ShowOpenContracts.view view;
    private ShowOpenModel model;

    public ShowOpenPresenter(ShowOpenView showOpenView) {
        this.view = showOpenView;
        model = new ShowOpenModel();

    }

    @Override
    public void loadOpenSchedule() {
        model.loadOpenSchedule(new ShowOpenContracts.presenterModelCallback() {
            @Override
            public void openList(ArrayList<ShowOpenDTO> openList) {
                view.loadOpenList(openList);
            }
        });
    }
}
