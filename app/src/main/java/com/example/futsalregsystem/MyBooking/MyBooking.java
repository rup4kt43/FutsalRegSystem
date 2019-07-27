package com.example.futsalregsystem.MyBooking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futsalregsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyBooking extends AppCompatActivity {

    RecyclerView recyclerView;
    private EditText number;
    private Button btn_load;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mybooking);

        final ArrayList<BookingDTO> myBooking = new ArrayList<>();

        getSupportActionBar().setTitle("MyBooking");

        recyclerView = findViewById(R.id.rv_myBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        number = findViewById(R.id.et_mb_number);
        btn_load = findViewById(R.id.btn_mb_load);

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String num = number.getText().toString();

                if (!num.isEmpty()) {


                    DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference().child("Booking");
                    bookRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            myBooking.clear();

                            for (DataSnapshot dateSnap : dataSnapshot.getChildren()) {
                                String date = dateSnap.getKey();
                                BookingDTO bookingDTO = new BookingDTO();

                                for (DataSnapshot timeSnap : dateSnap.getChildren()) {

                                    String time = timeSnap.getKey();
                                    String phone = String.valueOf(timeSnap.child("Phone").getValue());


                                    if (num.matches(phone)) {


                                        String side = String.valueOf(timeSnap.child("Side").getValue());

                                        bookingDTO.setDate(date);
                                        bookingDTO.setTime(time);
                                        bookingDTO.setSide(side);

                                        myBooking.add(bookingDTO);


                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    BookingAdapter bookingAdapter = new BookingAdapter(MyBooking.this, myBooking);
                    recyclerView.setAdapter(bookingAdapter);
                    number.setText("");

                } else {
                    Toast.makeText(MyBooking.this, "Please enter the number used in booking!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
