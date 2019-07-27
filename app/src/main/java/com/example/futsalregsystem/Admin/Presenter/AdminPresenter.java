package com.example.futsalregsystem.Admin.Presenter;

import com.example.futsalregsystem.Admin.Contracts.AdminContracts;
import com.example.futsalregsystem.Admin.DTO.AdminDTO;
import com.example.futsalregsystem.Admin.Model.AdminModel;
import com.example.futsalregsystem.Admin.View.AdminView;

import java.util.ArrayList;

public class AdminPresenter implements AdminContracts.presenter{
    private AdminContracts.view view;
    private AdminModel model;

    public AdminPresenter(AdminView adminView) {
        this.view = adminView;
        model = new AdminModel();
    }


    @Override
    public void loadonHoldBooking(String sides, String currentDate) {
        model.loadOnHoldBooking(sides,currentDate, new AdminContracts.presenterModelCallback() {
            @Override
            public void infoDetails(ArrayList<AdminDTO> infoList) {
                view.loadOnHoldDetails(infoList);
            }
        });
    }

    @Override
    public void onRecyclerItemConfirmed(String side,String current_date,int pos, String time, String username, String phone) {
            model.onConfirmHoldingBooking(side,current_date,pos,time,username,phone);
    }
}
