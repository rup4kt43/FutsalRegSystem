package com.example.futsalregsystem.Admin.Contracts;

import com.example.futsalregsystem.Admin.DTO.AdminDTO;

import java.util.ArrayList;

public interface AdminContracts {

    interface view{
        void showToast(String msg);
        void loadOnHoldDetails(ArrayList<AdminDTO> infoList);
        void onRecyclerItemConfirmed(int pos,String time,String username,String phone,String date);
    }
    interface  presenter{
        void loadonHoldBooking(String sides,String currentDate);

        void onRecyclerItemConfirmed(String side,String current_date,int pos,String time,String username,String phone);
    }

    interface modelFirebaseCallback{
        void infoDetailse(ArrayList<AdminDTO> infoList);
    }
    interface presenterModelCallback{
        void infoDetails(ArrayList<AdminDTO> infoList);
    }
}
