package com.example.futsalregsystem.SignUp.Presenter;

import com.example.futsalregsystem.SignUp.Contracts.SignupContracts;
import com.example.futsalregsystem.SignUp.Model.SignupModel;
import com.example.futsalregsystem.SignUp.View.SignupView;

public class SignupPresenter implements SignupContracts.presenter {
    private SignupContracts.view view;
    private SignupModel model;

    public SignupPresenter(SignupView signupView) {
        this.view = signupView;
        model = new SignupModel();

    }

    @Override
    public void validateSignup(String username, String email, String password) {
        if (!username.isEmpty() || !email.isEmpty() || !password.isEmpty()) {
            model.signUp(username, email, password, new SignupContracts.presenterModelCallback() {
                @Override
                public void onSuccessfull(String msg) {
                    if (msg.matches("1")) {
                        view.showToast("Successfully SignedUp");
                        view.switchToLogin();
                    }

                }

                @Override
                public void onError(String msg) {
                    view.showToast(msg);
                }
            });
        } else {
            view.showToast("The Fields cannot be empty!!");
        }

    }
}
