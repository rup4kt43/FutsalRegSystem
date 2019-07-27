package com.example.futsalregsystem.Login.Model;

import com.example.futsalregsystem.Login.Contracts.LoginContracts;
import com.example.futsalregsystem.Login.Firebase.LoginFirebaseDB;

public class LoginModel {
    public void login(String uEmail, String password, final LoginContracts.presenterModelCallback callback) {


        LoginFirebaseDB loginFirebaseDB = new LoginFirebaseDB();
        loginFirebaseDB.login(uEmail,password, new LoginContracts.modelFirebaseCallback() {
            @Override
            public void onSuccessfull() {
                callback.onSuccessfull();
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });

    }
}
