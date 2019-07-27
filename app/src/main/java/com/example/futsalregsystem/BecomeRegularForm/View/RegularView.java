package com.example.futsalregsystem.BecomeRegularForm.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.futsalregsystem.BecomeRegularForm.Contracts.RegularContracts;
import com.example.futsalregsystem.BecomeRegularForm.Presenter.RegularPresenter;
import com.example.futsalregsystem.R;
import com.example.futsalregsystem.UTILITES.GlobalAccessUsername;

import java.util.ArrayList;
import java.util.Calendar;

public class RegularView extends AppCompatActivity implements RegularContracts.view {

    EditText userName, userEmail, userPhone;
    Spinner select_ground, select_time;
    Button btn_submit;
    private RegularPresenter presenter;
    private String[] sides;
    private String selected_side;
    private TextInputLayout til;
    private ArrayList<String> mainList;
    private String choosedTime;
    private ProgressDialog progressDialog;
    public static String total_date;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_forms);
        presenter = new RegularPresenter(this);
        sides = new String[]{"Select Side", "5A-Side", "7A-Side"};


        //Gettting the current date
        Calendar calendar = Calendar.getInstance();
        final int date = calendar.get(Calendar.DAY_OF_MONTH);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentYear = calendar.get(Calendar.YEAR);

        total_date = (currentMonth+1)+"-"+date+"-"+currentYear;

        progressDialog = new ProgressDialog(this);

        //progress Dialog part
        progressDialog.setMessage("Saving Information.Wait....");


        userName = findViewById(R.id.et_name);
        userEmail = findViewById(R.id.et_email);
        userPhone = findViewById(R.id.et_phone);
        select_time = findViewById(R.id.spinner_time);
        select_ground = findViewById(R.id.sp_select_sides);
        btn_submit = findViewById(R.id.btn_submit);
        userEmail.setText(GlobalAccessUsername.userEmail);
        userEmail.setEnabled(false);


        //On interaction with button click
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                //running progress dialog for 3 sec
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        RegularView.this.finish();
                    }
                }, 3000);


                String uName = userName.getText().toString();
                String uEmail = userEmail.getText().toString();
                String uPhone = userPhone.getText().toString();
                presenter.onSubmitClick(uName, uEmail, uPhone, selected_side, choosedTime);


            }
        });

        //On interaction with select ground=================================================
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sides) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    select_time.setEnabled(false);
                    return false;
                } else {
                    select_time.setEnabled(true);

                    return true;
                }

            }
        };
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        select_ground.setAdapter(spinnerAdapter);

        select_ground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_side = sides[position];
                presenter.loadSpinnerTimeItem(selected_side);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        select_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosedTime = mainList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //=======================================================================================


    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void setSpinnerList(ArrayList<String> list) {

        this.mainList = list;
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_time, list) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {

                    return false;
                } else {
                    return true;
                }

            }
        };
        select_time.setAdapter(spnAdapter);
    }
}
