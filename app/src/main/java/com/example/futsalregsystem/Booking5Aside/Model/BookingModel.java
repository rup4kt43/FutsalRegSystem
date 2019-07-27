package com.example.futsalregsystem.Booking5Aside.Model;

import com.example.futsalregsystem.Booking5Aside.Contracts.BookingContracts;
import com.example.futsalregsystem.Booking5Aside.DTO.BookingDTO;
import com.example.futsalregsystem.Booking5Aside.Firebase.BookingFirebaseDB;

import java.util.ArrayList;

public class BookingModel {
    public void loadSchedule(String date, final BookingContracts.presenterModel presenterModel) {
        BookingFirebaseDB bookingFirebaseDB = new BookingFirebaseDB();
        bookingFirebaseDB.loadSchedule(date, new BookingContracts.modelFirebase(){
            @Override
            public void schdeuleList(ArrayList<BookingDTO> scheduleList) {
                presenterModel.schduleList(scheduleList);
            }
        });
    }
}
