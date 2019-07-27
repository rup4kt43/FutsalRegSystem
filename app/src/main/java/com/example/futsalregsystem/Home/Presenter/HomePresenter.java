package com.example.futsalregsystem.Home.Presenter;

import com.example.futsalregsystem.Home.Contracts.HomeContracts;
import com.example.futsalregsystem.Home.Model.HomeModel;
import com.example.futsalregsystem.Home.View.HomeView;

public class HomePresenter implements HomeContracts.presenter {
    private HomeContracts.view view;
    private HomeModel homeModel;

    public HomePresenter(HomeView homeView) {
        this.view = homeView;
        homeModel = new HomeModel();
    }


    //Presenter Implemented Methods
    @Override
    public void saveSchedule() {
        homeModel.saveSchedule();
    }

    @Override
    public void switchToBooking() {
        view.switchToBooking();
    }
}
