package com.example.futsalregsystem.Login.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futsalregsystem.Admin.View.AdminView;
import com.example.futsalregsystem.Home.View.HomeView;
import com.example.futsalregsystem.Login.Contracts.LoginContracts;
import com.example.futsalregsystem.Login.Presenter.LoginPresenter;
import com.example.futsalregsystem.R;
import com.example.futsalregsystem.SignUp.View.SignupView;
import com.example.futsalregsystem.UTILITES.GlobalAccessUsername;
import com.google.firebase.auth.FirebaseAuth;

public class LoginView extends AppCompatActivity implements LoginContracts.view {

    private EditText email, password;
    private Button login;
    private LoginPresenter presenter;
    private TextView signup;
    public static String uEmail;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Log.e("User", String.valueOf(firebaseAuth.getCurrentUser()));
                    startActivity(new Intent(LoginView.this, HomeView.class));
                    LoginView.this.finish();
                }
            }
        };


        presenter = new LoginPresenter(this);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.tv_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(LoginView.this);
                progressDialog.setMessage("Logging In...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                uEmail = email.getText().toString();
                String pass = password.getText().toString();

                if (uEmail.matches("admin") && pass.matches("admin")) {
                    startActivity(new Intent(LoginView.this, AdminView.class));
                }
                presenter.loginValidation(uEmail, pass);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switchActivity();
            }
        });


    }

    @Override
    public void showToast(String msg) {
        progressDialog.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchToSignup() {
        startActivity(new Intent(LoginView.this, SignupView.class));
        this.finish();
    }

    @Override
    public void switchToHome() {
        GlobalAccessUsername.userEmail = uEmail;
        email.setText("");
        password.setText("");
        progressDialog.dismiss();
        startActivity(new Intent(LoginView.this, HomeView.class));
        this.finish();
    }


}
