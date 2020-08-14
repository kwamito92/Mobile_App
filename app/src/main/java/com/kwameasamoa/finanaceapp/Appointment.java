package com.kwameasamoa.finanaceapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Appointment extends AppCompatActivity {

    EditText nameText, phoneText, quoteText;
    Button DatePicker, TimePicker, Save;
    TextView date, time;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        nameText = findViewById(R.id.editName);
        phoneText = findViewById(R.id.editPhone);
        quoteText = findViewById(R.id.editComment);
        date = findViewById(R.id.editDate);
        time = findViewById(R.id.editTime);


        DatePicker = findViewById(R.id.dateSwitch);
        DatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calender();
            }
        });

        TimePicker = findViewById(R.id.timeButton);
        TimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time();
            }
        });

        Save = findViewById(R.id.ButtonSave);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate(nameText, phoneText)) {
                    AddData();

                    //clear saved value once save button is clicked.
                    nameText.getText().clear();
                    phoneText.getText().clear();
                    quoteText.getText().clear();
                    date.setText("");
                    time.setText("");
                }else{
                    Toast.makeText(Appointment.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //returns back to main activity
        ApptFinish();
    }

    private void AddData() {
        /*push() creates unique keyID for each data inserted.
        child() is sub class of database being used. */
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Information").push();

        //Add data into class function object.
        String Name = nameText.getText().toString().trim();
        String Phone = phoneText.getText().toString().trim();
        String Date = date.getText().toString().trim();
        String Time = time.getText().toString().trim();
        String Comment = quoteText.getText().toString().trim();

        Information info = new Information(Name, Phone, Date, Time, Comment);
        databaseReference.setValue(info);

        //Showing Toast message after successfully data submit.
        Toast.makeText(Appointment.this, "Data Successfully saved into Firebase", Toast.LENGTH_SHORT).show();
    }

    private void Calender() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, Year, Month, Day);
        datePickerDialog.show();
    }

    private void Time() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int Hour = c.get(Calendar.HOUR_OF_DAY);
        int Minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Dialog,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour,
                                          int minute) {
                        String AmPm = "AM";
                        int currentHour;
                        if (hour > 11) {
                            currentHour = hour - 12;
                            AmPm = "PM";
                        } else {
                            currentHour = hour;
                        }
                        time.setText(currentHour + ":" + minute + " " + AmPm);
                    }
                }, Hour, Minute, false);
        timePickerDialog.show();
    }

    private boolean validate(EditText nameText, EditText phoneText){
        String Name = nameText.getText().toString().trim();
        if(TextUtils.isEmpty(Name)){
            nameText.setError("Empty field not allowed");
            return false;
        }

        String Phone = phoneText.getText().toString().trim();
        if(TextUtils.isEmpty(Phone)) {
            phoneText.setError("Empty field not allowed");
            return false;
        }
        return true;
    }

    private void ApptFinish() {
        //return to mainActivity
        Button button4 = findViewById(R.id.ButtonBack);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Appointment.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}

