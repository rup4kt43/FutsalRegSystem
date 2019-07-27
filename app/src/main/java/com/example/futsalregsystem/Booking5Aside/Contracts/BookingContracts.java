package com.example.futsalregsystem.Booking5Aside.Contracts;

import com.example.futsalregsystem.Booking5Aside.DTO.BookingDTO;

import java.util.ArrayList;

public interface BookingContracts {

    interface view {
        void showToast(String msg);
        void loadSchedule(ArrayList<BookingDTO> scheduleList);

    }

    interface presenter {
        void loadSchedule(String date);
    }

    interface presenterModel{

        void schduleList(ArrayList<BookingDTO> scheduleList);
    }


    interface modelFirebase {

        void schdeuleList(ArrayList<BookingDTO> scheduleList);
    }
}
