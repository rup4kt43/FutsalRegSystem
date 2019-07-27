package com.example.futsalregsystem.ShowBooking.Presenter;

import com.example.futsalregsystem.ShowBooking.Contracts.ShowBookingContracts;
import com.example.futsalregsystem.ShowBooking.DTO.ShowBookingDTO;
import com.example.futsalregsystem.ShowBooking.Model.ShowBookingModel;
import com.example.futsalregsystem.ShowBooking.View.ShowBookingView;

import java.util.ArrayList;

public class ShowBookingPresenter implements ShowBookingContracts.presenter {
    private ShowBookingContracts.view view;
    private ShowBookingModel model;

    public ShowBookingPresenter(ShowBookingView showBookingView) {
        this.view = showBookingView;
        model = new ShowBookingModel();
    }

    @Override
    public void loadBookingList() {
        model.loadBookingList(new ShowBookingContracts.presenterModelCallback() {
            @Override
            public void bookingList(ArrayList<ShowBookingDTO> bookingList) {
                view.loadBookingList(bookingList);
            }
        });
    }
}
