package com.kwameasamoa.finanaceapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class NetPayApp extends AppCompatActivity {

    EditText finance1, finance2, finance3, finance4, finance5, finance6, finance7;
    double gross, deduction, NetPay;
    Button button, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_pay_app);

        openMain();

        finance1 = findViewById(R.id.Hrs);
        finance2 = findViewById(R.id.Money);
        finance3 = findViewById(R.id.FedTax);
        finance4 = findViewById(R.id.StateTax);
        finance5 = findViewById(R.id.LocalTax);
        finance6 = findViewById(R.id.FicaTax);
        finance7 = findViewById(R.id.Health);

        finance1.addTextChangedListener(Watcher);
        finance2.addTextChangedListener(Watcher);
        finance3.addTextChangedListener(Watcher);
        finance4.addTextChangedListener(Watcher);
        finance5.addTextChangedListener(Watcher);
        finance6.addTextChangedListener(Watcher);
        finance7.addTextChangedListener(Watcher);

        //calculation for user input inserted in edittext
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(finance1.getText().toString());
                double num2 = Double.parseDouble(finance2.getText().toString());
                double num3 = Double.parseDouble(finance3.getText().toString());
                double num4 = Double.parseDouble(finance4.getText().toString());
                double num5 = Double.parseDouble(finance5.getText().toString());
                double num6 = Double.parseDouble(finance6.getText().toString());
                double num7 = Double.parseDouble(finance7.getText().toString());

                gross = num1 * num2;
                deduction = num3 + num4 + num5 + num6 + num7;
                NetPay = gross - deduction;

                showDialog();
            }
        });

        //clears text once rest button is clicked
        button2 = findViewById(R.id.ResetButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finance1.getText().clear();
                finance2.getText().clear();
                finance3.getText().clear();
                finance4.getText().clear();
                finance5.getText().clear();
                finance6.getText().clear();
                finance7.getText().clear();
            }
        });
    }

    private void showDialog() {
        Context context = this;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);

        //formatting of displaying numbers
        TextView GrossPay = dialog.findViewById(R.id.Gpay);
        //%.2f handles both floating and double. Display only 2 decimals after period.
        GrossPay.setText(String.format(Locale.getDefault(), "%.2f", gross));
        TextView Deductible = dialog.findViewById(R.id.Dpay);
        Deductible.setText(String.format(Locale.getDefault(), "%.2f", deduction));
        TextView Net = dialog.findViewById(R.id.Npay);
        Net.setText(String.format(Locale.getDefault(), "%.2f", NetPay));
        dialog.show();
    }

    private TextWatcher Watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String HrsInput = finance1.getText().toString().trim();
            String MoneyInput = finance2.getText().toString().trim();
            String FedInput = finance3.getText().toString().trim();
            String STInput = finance4.getText().toString().trim();
            String LTInput = finance5.getText().toString().trim();
            String FTInput = finance6.getText().toString().trim();
            String HIInput = finance7.getText().toString().trim();
            button.setEnabled(!HrsInput.isEmpty() && !MoneyInput.isEmpty() && !FedInput.isEmpty() &&
                    !STInput.isEmpty() && !LTInput.isEmpty() && !FTInput.isEmpty() &&
                    !HIInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void openMain() {
        button3 = findViewById(R.id.CancelButton);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NetPayApp.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}