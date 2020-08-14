package com.kwameasamoa.finanaceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button NetGross, ScheduleButon, ViewAppt, ViewS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openNetPay();
        openAppt();
        openViewAppt();
        openSurvey();
    }


    private void openSurvey() {
        ViewS = findViewById(R.id.SurveyButton);
        ViewS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Survey.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void openNetPay() {
        NetGross = findViewById(R.id.NetButton);
        NetGross.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NetPayApp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void openAppt() {
        ScheduleButon = findViewById(R.id.AppButton);
        ScheduleButon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Appointment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void openViewAppt() {
        ViewAppt = findViewById(R.id.ViewAppButton);
        ViewAppt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAppointment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void onExit(View v) {
        finish();
    }
}