package com.example.futsalregsystem.SignUp.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futsalregsystem.Login.View.LoginView;
import com.example.futsalregsystem.R;
import com.example.futsalregsystem.SignUp.Contracts.SignupContracts;
import com.example.futsalregsystem.SignUp.Presenter.SignupPresenter;

public class SignupView extends AppCompatActivity implements SignupContracts.view {

    private EditText et_username, et_email, et_password;
    private Button btn_signup;
    private SignupPresenter presenter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Invoking Presenter
        presenter = new SignupPresenter(this);


        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_signup = findViewById(R.id.btn_signup);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(SignupView.this);
                progressDialog.setMessage("Wait a moment...");
                progressDialog.show();
                String uname = et_username.getText().toString();
                String upass = et_password.getText().toString();
                String uemail = et_email.getText().toString();

                presenter.validateSignup(uname, uemail, upass);
            }
        });


    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchToLogin() {
        progressDialog.dismiss();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SignupView.this, LoginView.class));
            }
        }, 100);

    }
}


