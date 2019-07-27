package com.example.futsalregsystem.Home.Utilites;

import java.util.ArrayList;

public class ScheduleList {
    public static ArrayList<String> createDefaultSchedule() {
        ArrayList<String> timeList = new ArrayList<>();

        timeList.add("09:00 - 10:00");
        timeList.add("10:00 - 11:00");
        timeList.add("11:00 - 12:00");
        timeList.add("12:00 - 13:00");
        timeList.add("13:00 - 14:00");
        timeList.add("14:00 - 15:00");
        timeList.add("15:00 - 16:00");
        timeList.add("16:00 - 17:00");
        timeList.add("17:00 - 18:00");

        return timeList;
    }

    public static ArrayList<String> createDefaultScheduleFor7s(){
        ArrayList<String> timeList = new ArrayList<>();
        timeList.add("06:00 - 07:00");
        timeList.add("07:00 - 08:00");
        timeList.add("08:00 - 09:00");
        timeList.add("10:00 - 11:00");
        timeList.add("11:00 - 12:00");
        timeList.add("12:00 - 13:00");
        timeList.add("13:00 - 14:00");
        timeList.add("14:00 - 15:00");
        timeList.add("15:00 - 16:00");

        return timeList;
    }
}
