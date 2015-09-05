package com.example.abhishektiwari.akaar;

/**
 * Created by Abhishek Tiwari on 4/3/2015.
 */public class StudentDetail {
    private String EntryNumber;
    private String name;
    private String Attendance;

    public StudentDetail(){

    }

    public StudentDetail(String name, String EntryNumber, String Attendance){
        this.EntryNumber = EntryNumber;
        this.name = name;
        this.Attendance = Attendance;
    }

    public String getDetails() {
        return EntryNumber;
    }

    public void setDetails(String details) {
        this.EntryNumber = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Attendance;
    }

    public void setPrice(String Percentage)  {
        this.Attendance = Percentage;
    }

}
