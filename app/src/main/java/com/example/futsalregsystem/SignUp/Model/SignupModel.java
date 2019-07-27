package com.example.futsalregsystem.SignUp.Model;

import com.example.futsalregsystem.SignUp.Contracts.SignupContracts;
import com.example.futsalregsystem.SignUp.Firebase.SignUpFirebase;

public class SignupModel {
    public void signUp(String username, String email, String password, final SignupContracts.presenterModelCallback callback) {
        SignUpFirebase signUpFirebase = new SignUpFirebase();
        signUpFirebase.signUp(username,email,password, new SignupContracts.modelFirebaseCallback() {
            @Override
            public void onSuccessfull(String msg) {
                callback.onSuccessfull(msg);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
