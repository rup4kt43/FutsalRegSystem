package com.example.futsalregsystem.ShowBooking.Contracts;

import com.example.futsalregsystem.ShowBooking.DTO.ShowBookingDTO;

import java.util.ArrayList;

public interface ShowBookingContracts {


    interface view {
        void loadBookingList(ArrayList<ShowBookingDTO> bookingList);
    }

    interface presenter {

        void loadBookingList();
    }

    interface modelFirebaseCallBack {
        void bookingList(ArrayList<ShowBookingDTO> bookingList);
    }

    interface presenterModelCallback {
        void bookingList(ArrayList<ShowBookingDTO> bookingList);
    }
}
