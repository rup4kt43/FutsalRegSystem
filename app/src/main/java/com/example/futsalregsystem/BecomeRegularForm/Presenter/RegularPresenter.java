package com.example.futsalregsystem.BecomeRegularForm.Presenter;

import com.example.futsalregsystem.BecomeRegularForm.Contracts.RegularContracts;
import com.example.futsalregsystem.BecomeRegularForm.Model.RegularModel;
import com.example.futsalregsystem.BecomeRegularForm.View.RegularView;

import java.util.ArrayList;

public class RegularPresenter implements RegularContracts.presenter {
    private RegularContracts.view view;
    private RegularModel model;


    public RegularPresenter(RegularView regularView) {
        this.view = regularView;
        model = new RegularModel();


    }

    @Override
    public void onSubmitClick(String usename, String userEmail, String userPhone, String ground, String time) {
        if (!usename.isEmpty() || !userEmail.isEmpty() || userPhone.isEmpty() || !ground.isEmpty()|| !time.isEmpty()){
            model.saveRegularDetails(usename,userEmail,userPhone,ground,time);
        }
    }

    @Override
    public void loadSpinnerTimeItem(String selected_side) {
        model.loadSpinnerItem(selected_side, new RegularContracts.presenterModelCallback() {
            @Override
            public void timeList(ArrayList<String> list) {
                view.setSpinnerList(list);
            }
        });
    }
}
