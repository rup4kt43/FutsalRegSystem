package com.example.futsalregsystem.Login.Presenter;

import com.example.futsalregsystem.Login.Contracts.LoginContracts;
import com.example.futsalregsystem.Login.Model.LoginModel;
import com.example.futsalregsystem.Login.View.LoginView;

public class LoginPresenter implements LoginContracts.presenter {
    private LoginContracts.view view;
    private LoginModel model;

    public LoginPresenter(LoginView loginView) {
        this.view = loginView;
        model = new LoginModel();
    }

    @Override
    public void loginValidation(String uEmail, String password) {
        if (uEmail.isEmpty() || password.isEmpty()) {
            view.showToast("Please Check your Email or Password!!");
        } else {
            model.login(uEmail, password, new LoginContracts.presenterModelCallback() {
                @Override
                public void onSuccessfull() {
                    view.switchToHome();
                }

                @Override
                public void onError(String msg) {
                    view.showToast(msg);
                }
            });
        }
    }

    @Override
    public void switchActivity() {
        view.switchToSignup();
    }
}
