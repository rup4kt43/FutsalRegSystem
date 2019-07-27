package com.example.futsalregsystem.Login.Firebase;

import android.support.annotation.NonNull;

import com.example.futsalregsystem.Login.Contracts.LoginContracts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFirebaseDB {


    public void login(String uEmail, String password, final LoginContracts.modelFirebaseCallback callback) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(uEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccessfull();
                } else {
                    callback.onError(task.getException().toString());
                }
            }
        });
    }
}
