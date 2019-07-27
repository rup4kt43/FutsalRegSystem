package com.example.futsalregsystem.Admin.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.futsalregsystem.Admin.Adapters.InfoListAdapter;
import com.example.futsalregsystem.Admin.Contracts.AdminContracts;
import com.example.futsalregsystem.Admin.DTO.AdminDTO;
import com.example.futsalregsystem.Admin.Presenter.AdminPresenter;
import com.example.futsalregsystem.Login.View.LoginView;
import com.example.futsalregsystem.MembershipRequest.View.MemberReqView;
import com.example.futsalregsystem.R;
import com.example.futsalregsystem.ShowBooking.View.ShowBookingView;
import com.example.futsalregsystem.ShowOpen.View.ShowOpenView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdminView extends AppCompatActivity implements AdminContracts.view {
    private Spinner spinner;
    private String[] sides;
    private static AdminPresenter presenter;
    public static RecyclerView recyclerView;
    static String side;
    static String current_date;
    public static InfoListAdapter infoListAdapter;
    private ArrayList<AdminDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        presenter = new AdminPresenter(this);
        recyclerView = findViewById(R.id.rv_holdDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adding a divider line
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        //Date Part
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar calendar = Calendar.getInstance();
        final String currentDay = sdf.format(calendar.getTime());
        final int date = calendar.get(Calendar.DAY_OF_MONTH);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentYear = calendar.get(Calendar.YEAR);
        final long milliTime = calendar.getTimeInMillis();


        //Spinner Part
        spinner = findViewById(R.id.sp_admin);
        sides = new String[]{"Select Side", "5A-Side : 1200 NPR", "7A-Side : 1500 NPR"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sides) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else
                    return true;

            }
        };
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                side = sides[position];
                current_date = ((currentMonth + 1) + "-" + date + "-" + currentYear);
                if (position != 0) {


                    loadOnHoldBooking();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //=========================


    }

    //action bar option menu action
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_refresh) {


            loadOnHoldBooking();
            return true;
        }

        if (id == R.id.member_request) {
            startActivity(new Intent(AdminView.this, MemberReqView.class));
        }

        if (id == R.id.show_booking) {
            startActivity(new Intent(AdminView.this, ShowBookingView.class));
        }
        if (id == R.id.show_open) {
            startActivity(new Intent(AdminView.this, ShowOpenView.class));
        }

        if (id == R.id.action_logut) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AdminView.this, LoginView.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //=========================


    public static void loadOnHoldBooking() {
        presenter.loadonHoldBooking(side, current_date);

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadOnHoldDetails(ArrayList<AdminDTO> infoList) {
        infoListAdapter = new InfoListAdapter(AdminView.this, infoList);
        recyclerView.setAdapter(infoListAdapter);


    }

    @Override
    public void onRecyclerItemConfirmed(int pos, String time, String username, String phone, String date) {
        presenter.onRecyclerItemConfirmed(side, date, pos, time, username, phone);
    }


}
