package com.example.futsalregsystem.Booking5Aside.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.futsalregsystem.Booking5Aside.DTO.BookingDTO;
import com.example.futsalregsystem.Booking5Aside.Firebase.BookingFirebaseDB;
import com.example.futsalregsystem.Booking5Aside.View.BookingView;
import com.example.futsalregsystem.R;
import com.example.futsalregsystem.UTILITES.GlobalAccessUsername;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter {
    private final BookingView context;
    private final ArrayList<BookingDTO> list;
    private final String date;
    private final String userEmail;
    private final String side;
    private LinearLayout mainLayout;
    private TextView tv_schedule;
    private Button btn_book;
    private BookingFirebaseDB bookingFirebaseDB;
    private ColorStateList oldColor;

    public BookingAdapter(BookingView bookingView, ArrayList<BookingDTO> scheduleList, String date, String s) {
        this.context = bookingView;
        this.side=  s;
        this.list = scheduleList;
        this.date = date;
        this.userEmail = GlobalAccessUsername.userEmail;
        bookingFirebaseDB = new BookingFirebaseDB();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_booking_containeer, viewGroup, false);
        return (new CustomHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        tv_schedule.setText(list.get(i).getTime());

        oldColor = tv_schedule.getTextColors();
        if (list.get(i).getStatus().matches("On Hold")) {
            btn_book.setText("On Hold");
            alterLayout();
        } else if (list.get(i).getStatus().matches("Book")) {
            btn_book.setText("Book");
            deAlterLayout();
        } else if(list.get(i).getStatus().matches("Booked")){
            btn_book.setText("Booked");
            alterLayoutBooked();
        }

    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    private class CustomHolder extends RecyclerView.ViewHolder {
        public CustomHolder(View view) {
            super(view);
            mainLayout = view.findViewById(R.id.ll_rv_booking_cont);
            tv_schedule = view.findViewById(R.id.tv_rv_booking_cont_schedule);
            btn_book = view.findViewById(R.id.btn_rv_booking_cont_book);


            //for cancellation
            mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = getAdapterPosition();
                    if (list.get(position).getStatus().matches("Booked")) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Are you Sure You want to cancel the Booking?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                bookingFirebaseDB.cancelBooking(date, list.get(position).getTime());
                                context.loadPresnterSchedule(date);
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        return true;
                    } else return false;

                }
            });


            //When book button is clicked
            btn_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*int pos = getAdapterPosition();
                    bookingFirebaseDB = new BookingFirebaseDB(); //Changing the status from open to booked!!
                    int res = bookingFirebaseDB.changeStatus(date, list.get(pos).getTime());
                    context.loadPresnterSchedule(date);*/  //Resetting the adapter for changes !!!


                    /**
                     * CustomDialog
                     * Saving the user details and booked session in Booking Node!!!!
                     */
                    final int pos = getAdapterPosition();
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.booking_dialog_box);


                    Window dialogWindow = dialog.getWindow();
                    WindowManager m = context.getWindowManager();
                    Display d = m.getDefaultDisplay();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    lp.width = (int) (d.getWidth() * 0.8);

                    final EditText username, phone;
                    TextView bookingTime;
                    Button submit;


                    //interacting with dialogbox inputs
                    username = dialog.findViewById(R.id.et_db_booking_username);
                    phone = dialog.findViewById(R.id.et_db_booking_phone);
                    submit = dialog.findViewById(R.id.et_db_btn_submit);
                    bookingTime = dialog.findViewById(R.id.tv_booking_time);
                    bookingTime.setText(list.get(pos).getTime());

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String uname = username.getText().toString();
                            String pn = phone.getText().toString();
                            if (uname.isEmpty() || pn.isEmpty()) {
                                context.showToast("Username and phone is mandatory! Pleas Fill the fields");
                            } else {
                                bookingFirebaseDB.changeStatus(date, list.get(pos).getTime(),side);
                                bookingFirebaseDB.onHold(side,date,list.get(pos).getTime(),uname,pn,side,userEmail);
                                context.loadPresnterSchedule(date);

                                dialog.dismiss();
                            }

                        }
                    });

                    dialog.show();


                }
            });

        }
    }


    //Customized Function
    private void alterLayout() {
        mainLayout.setBackgroundColor(Color.parseColor("#ffc046"));
        tv_schedule.setTextColor(Color.WHITE);
        btn_book.setBackgroundResource(R.drawable.button_stytle_clicked);
        btn_book.setTextColor(Color.WHITE);
        btn_book.setClickable(false);
    }

    @SuppressLint("ResourceAsColor")
    private void deAlterLayout() {
        mainLayout.setBackgroundColor(0x00000000);
        tv_schedule.setTextColor(oldColor);
        btn_book.setClickable(true);
    }

    @SuppressLint("ResourceType")
    private void alterLayoutBooked() {
        mainLayout.setBackgroundColor(Color.parseColor("#AA0001"));
        tv_schedule.setTextColor(Color.WHITE);
        btn_book.setBackgroundResource(R.drawable.button_stytle_clicked);
        tv_schedule.setTextColor(Color.WHITE);
        btn_book.setTextColor(Color.WHITE);
        btn_book.setClickable(false);
    }

}
