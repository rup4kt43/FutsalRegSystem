package com.example.futsalregsystem.Booking5Aside.Presenter;

import com.example.futsalregsystem.Booking5Aside.Contracts.BookingContracts;
import com.example.futsalregsystem.Booking5Aside.DTO.BookingDTO;
import com.example.futsalregsystem.Booking5Aside.Model.BookingModel;
import com.example.futsalregsystem.Booking5Aside.View.BookingView;

import java.util.ArrayList;

public class BookingPresenter implements BookingContracts.presenter {


    private final BookingView view;
    private BookingModel bookingModel;

    public BookingPresenter(BookingView bookingView) {
            this.view = bookingView;
            bookingModel = new BookingModel();

    }



    //Implemented BookingPresenter Interface methods
    @Override
    public void loadSchedule(String date) {
        bookingModel.loadSchedule(date, new BookingContracts.presenterModel() {
            @Override
            public void schduleList(ArrayList<BookingDTO> scheduleList) {
                view.loadSchedule(scheduleList);
            }
        });
    }
}
