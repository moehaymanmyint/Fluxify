package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Time extends AppCompatActivity {

    EditText timeNo;
    Button timeBtn;
    TextView result;
    Spinner timespinner1, timespinner2;
    ArrayAdapter<CharSequence> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);

        timeNo = findViewById(R.id.timeNo);
        timeBtn = findViewById(R.id.timeBtn);
        result = findViewById(R.id.result);
        timespinner1 = findViewById(R.id.timeSpinner1);
        timespinner2 = findViewById(R.id.timeSpinner2);

        adapter = ArrayAdapter.createFromResource(this, R.array.time_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        timespinner1.setAdapter(adapter);
        timespinner2.setAdapter(adapter);

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTime();
            }
        });
    }

    private void convertTime() {
        String inputValue = timeNo.getText().toString();
        if (inputValue.isEmpty()) {
            timeNo.setError("Enter Value");
            timeNo.requestFocus();
            return;
        } else {
            double inputTime = Double.parseDouble(inputValue);

            if (inputTime <= 0.0) {
                Toast.makeText(Time.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                String unitFrom = timespinner1.getSelectedItem().toString();
                String unitTo = timespinner2.getSelectedItem().toString();
                double convertedTime = convert(unitFrom, unitTo, inputTime);
                String resultText = String.format("%.2f %s", convertedTime, unitTo);
                result.setText(resultText);
            }
        }
    }

    private double convert(String unitFrom, String unitTo, double inputTime) {
        if (unitFrom.equals("hr") && unitTo.equals("min")) {
            return inputTime * 60.0;
        } else if (unitFrom.equals("hr") && unitTo.equals("sec")) {
            return inputTime * 3600.0;
        } else if (unitFrom.equals("hr") && unitTo.equals("ms")) {
            return inputTime * 3600000;
        } else if (unitFrom.equals("min") && unitTo.equals("hr")) {
            return inputTime / 60.0;
        } else if (unitFrom.equals("min") && unitTo.equals("sec")) {
            return inputTime * 60.0;
        } else if (unitFrom.equals("min") && unitTo.equals("ms")) {
            return inputTime * 60000.0;
        } else if (unitFrom.equals("sec") && unitTo.equals("hr")) {
            return inputTime / 3600.0;
        } else if (unitFrom.equals("sec") && unitTo.equals("min")) {
            return inputTime / 60.0;
        } else if (unitFrom.equals("sec") && unitTo.equals("ms")) {
            return inputTime * 1000.0;
        } else if (unitFrom.equals("ms") && unitTo.equals("hr")) {
            return inputTime / 3600000;
        } else if (unitFrom.equals("ms") && unitTo.equals("min")) {
            return inputTime / 60000.0;
        } else if (unitFrom.equals("ms") && unitTo.equals("sec")) {
            return inputTime / 1000.0;
        } else if (unitFrom.equals(unitTo)) {
            return inputTime;
        } else {
            return 0.0;
        }
    }
}