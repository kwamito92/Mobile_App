package com.kwameasamoa.finanaceapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Survey extends AppCompatActivity {

    Context context = this;
    EditText quest1, quest2, quest3, quest4, quest5, user;
    DatabaseReference reference;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        quest1 = findViewById(R.id.question1);
        quest2 = findViewById(R.id.question2);
        quest3 = findViewById(R.id.question3);
        quest4 = findViewById(R.id.question4);
        quest5 = findViewById(R.id.question5);

        reference = FirebaseDatabase.getInstance().getReference().child("Survey").push();
        done = findViewById(R.id.btnfinish);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder input = new AlertDialog.Builder(context);
                input.setTitle("Survey");
                input.setMessage("Enter your name to complete: ");
                user = new EditText(context);
                input.setView(user);
                input.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value = user.getText().toString();
                        if(validate(quest1, quest2, quest3, quest4, quest5)){
                            AddResult();
                            quest1.getText().clear();
                            quest2.getText().clear();
                            quest3.getText().clear();
                            quest4.getText().clear();
                            quest5.getText().clear();
                        }else{
                            Toast.makeText(Survey.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                input.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog();
                    }
                });
                AlertDialog alertDialog = input.create();
                alertDialog.show();
            }
        });
        Return();
    }

    private void Return() {
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Survey.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void AddResult() {
        String q1 = quest1.getText().toString().trim();
        String q2 = quest2.getText().toString().trim();
        String q3 = quest3.getText().toString().trim();
        String q4 = quest4.getText().toString().trim();
        String q5 = quest5.getText().toString().trim();

        SurveyData result = new SurveyData(q1, q2, q3, q4, q5);
        reference.setValue(result);
    }

    private boolean validate(EditText quest1, EditText quest2, EditText quest3, EditText quest4, EditText quest5){
        String q1 = quest1.getText().toString().trim();
        if(TextUtils.isEmpty(q1)){
            quest1.setError("Empty field not allowed");
            return false;
        }
        String q2 = quest2.getText().toString().trim();
        if(TextUtils.isEmpty(q2)){
            quest2.setError("Empty field not allowed");
            return false;
        }
        String q3 = quest3.getText().toString().trim();
        if(TextUtils.isEmpty(q3)){
            quest2.setError("Empty field not allowed");
            return false;
        }
        String q4 = quest4.getText().toString().trim();
        if(TextUtils.isEmpty(q4)){
            quest4.setError("Empty field not allowed");
            return false;
        }
        String q5 = quest5.getText().toString().trim();
        if(TextUtils.isEmpty(q5)){
            quest4.setError("Empty field not allowed");
            return false;
        }
        return true;
    }

    private void dialog(){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Survey Warning!!");
        builder.setMessage("Please fill out the following field to proceed: \n" + "\n-Name");        // add a button
        builder.setPositiveButton("OK", null);        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}