package com.example.futsalregsystem.SignUp.Firebase;

import android.support.annotation.NonNull;

import com.example.futsalregsystem.SignUp.Contracts.SignupContracts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpFirebase {



    public void signUp(final String username, String email, String password, final SignupContracts.modelFirebaseCallback callback) {
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        //Saving user name while signing up


        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                     FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    callback.onSuccessfull("1");
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                    user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //TODO: NOTHING
                        }
                    });


                }else{
                   callback.onError(task.getException().toString());
                }
            }
        });
    }
}
