package com.example.futsalregsystem.Login.Contracts;

public interface LoginContracts {

    interface  view{
        void showToast(String msg);
        void switchToSignup();
        void switchToHome();
    }


    interface presenter{
        void loginValidation(String uname,String password);
        void switchActivity();
    }

    interface modelFirebaseCallback{
        void onSuccessfull();
        void onError(String msg);
    }

    interface presenterModelCallback{
        void onSuccessfull();
        void onError(String msg);
    }
}
