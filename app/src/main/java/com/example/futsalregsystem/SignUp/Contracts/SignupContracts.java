package com.example.futsalregsystem.SignUp.Contracts;

public interface SignupContracts {

    interface view {
        void showToast(String msg);
        void switchToLogin();
    }

    interface presenter {
        void validateSignup(String username, String email, String password);
    }

    interface modelFirebaseCallback{
        void onSuccessfull(String msg);
        void onError(String msg);
    }

    interface presenterModelCallback{
        void onSuccessfull(String msg);
        void onError(String msg);
    }
}
