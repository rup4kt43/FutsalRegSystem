package com.example.futsalregsystem.BecomeRegularForm.Contracts;

import java.util.ArrayList;

public interface RegularContracts {

    interface view {
        void showToast(String msg);
        void setSpinnerList(ArrayList<String> list);


    }

    interface presenter {
        void onSubmitClick(String usename, String userEmail, String userPhone, String ground, String time);

        void loadSpinnerTimeItem(String selected_side);

    }


    interface modelFirebaseCallback {
        void timeList(ArrayList<String> list);
    }

    interface  presenterModelCallback{
        void timeList(ArrayList<String> list);
    }
}
