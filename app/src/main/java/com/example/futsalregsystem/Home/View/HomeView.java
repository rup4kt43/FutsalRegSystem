package com.example.futsalregsystem.Home.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futsalregsystem.BecomeRegularForm.View.RegularView;
import com.example.futsalregsystem.Booking5Aside.View.BookingView;
import com.example.futsalregsystem.Home.Contracts.HomeContracts;
import com.example.futsalregsystem.Home.Presenter.HomePresenter;
import com.example.futsalregsystem.Login.View.LoginView;
import com.example.futsalregsystem.MyBooking.MyBooking;
import com.example.futsalregsystem.R;
import com.example.futsalregsystem.UTILITES.GlobalAccessUsername;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeContracts.view {

    private String selectedDate;
    private DrawerLayout drawer;
    private CalendarView calendarView;
    private String selected_day;

    private Spinner spinner;
    private String[] sides;
    private String selected_side;
    private TextView nav_email;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendarView = findViewById(R.id.cv_home);


        //Spinner Section
        spinner = findViewById(R.id.sp_home);
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
                selected_side = sides[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //================


        //Invoking the presenter and loading the default timetable
        final HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.saveSchedule();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        //setting text to header
        nav_email = headerView.findViewById(R.id.nav_tv_email);
        nav_email.setText(String.valueOf(GlobalAccessUsername.userEmail));


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //Testing
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar calendar = Calendar.getInstance();


        final String currentDay = sdf.format(calendar.getTime());
        final int date = calendar.get(Calendar.DAY_OF_MONTH);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentYear = calendar.get(Calendar.YEAR);
        final long milliTime = calendar.getTimeInMillis();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = (month + 1) + "-" + dayOfMonth + "-" + year;


                //--Logic behind 1 week booking//

                if (currentYear == year) {

                    if (currentMonth == month) {


                        switch (currentDay) {


                            case "Sunday":
                                if (dayOfMonth <= date + 6) {
                                    homePresenter.switchToBooking();
                                } else {
                                    view.setDate(milliTime);
                                    showSnackBar("Schedule has not been uploaded.");
                                }
                                break;
                            case "Monday":
                                if (dayOfMonth <= date + 5) {
                                    homePresenter.switchToBooking();
                                } else {
                                    view.setDate(milliTime);
                                    showSnackBar("Schedule has not been uploaded.");
                                }
                                break;
                            case "Tuesday":
                                if (dayOfMonth <= date + 4) {
                                    homePresenter.switchToBooking();
                                } else {
                                    view.setDate(milliTime);
                                    showSnackBar("Schedule has not been uploaded.");
                                }
                                break;
                            case "Wednesday":
                                if (dayOfMonth <= date + 3) {
                                    homePresenter.switchToBooking();
                                } else {
                                    view.setDate(milliTime);
                                    showSnackBar("Schedule has not been uploaded.");
                                }
                                break;
                            case "Thursday":
                                if (dayOfMonth <= date + 2) {
                                    homePresenter.switchToBooking();
                                } else {
                                    view.setDate(milliTime);
                                    showSnackBar("Schedule has not been uploaded.");
                                }
                                break;
                            case "Friday":
                                if (dayOfMonth <= date + 1) {
                                    homePresenter.switchToBooking();
                                } else {
                                    view.setDate(milliTime);
                                    showSnackBar("Schedule has not been uploaded.");
                                }
                                break;
                            case "Saturday":
                                if (dayOfMonth <= date + 7) {
                                    homePresenter.switchToBooking();
                                } else {
                                    view.setDate(milliTime);
                                    showSnackBar("Schedule has not been uploaded.");
                                }
                                break;
                            default:
                                return;
                        }
                    } else {
                        view.setDate(milliTime);
                        showSnackBar("Schedule has not been uploaded.");
                    }
                } else {
                    view.setDate(milliTime);
                    showSnackBar("Schedule has not been uploaded.");
                }


                //Test

            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {


            //For signing out
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeView.this, LoginView.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_regular) {
            startActivity(new Intent(HomeView.this, RegularView.class));
        } else if (id == R.id.nav_booking) {
            startActivity(new Intent(HomeView.this, MyBooking.class));

        } 

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Implemented Methods
    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchToBooking() {
        Intent i = new Intent(HomeView.this, BookingView.class);
        i.putExtra("selectedDate", selectedDate);
        i.putExtra("selectedSide", selected_side);

        startActivity(i);
    }

    @Override
    public void showSnackBar(String msg) {


        Snackbar snackbar = Snackbar.make(drawer, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
