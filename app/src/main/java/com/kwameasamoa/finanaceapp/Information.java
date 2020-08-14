package com.kwameasamoa.finanaceapp;

//Stores information into firebase database
//Data stored from user input(edittext - button)
public class Information {
    private String Name, Number, Date, Time, Description;

    //Default constructor
    public Information() {
    }

    public Information(String Name, String Number, String Date, String Time, String Description) {
        this.Name = Name;
        this.Number = Number;
        this.Date = Date;
        this.Time = Time;
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
