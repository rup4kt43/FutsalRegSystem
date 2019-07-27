package com.example.futsalregsystem.Home.Contracts;

public interface HomeContracts {

    interface view{
        void showToast(String msg);
        void switchToBooking();
        void showSnackBar(String msg);
    }
    interface presenter{
        void saveSchedule();
        void switchToBooking();
    }
}
