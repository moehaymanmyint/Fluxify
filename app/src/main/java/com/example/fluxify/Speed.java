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

public class Speed extends AppCompatActivity {

    EditText speedNo;
    Button speedBtn;
    TextView result;
    Spinner speedspinner1, speedspinner2;
    ArrayAdapter<CharSequence> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed);

        speedNo = findViewById(R.id.speedNo);
        speedBtn = findViewById(R.id.speedBtn);
        result = findViewById(R.id.result);
        speedspinner1 = findViewById(R.id.speedSpinner1);
        speedspinner2 = findViewById(R.id.speedSpinner2);

        adapter = ArrayAdapter.createFromResource(this, R.array.speed_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        speedspinner1.setAdapter(adapter);
        speedspinner2.setAdapter(adapter);

        speedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertSpeed();
            }
        });
    }

    private void convertSpeed() {
        String inputValue = speedNo.getText().toString();
        if (inputValue.isEmpty()) {
            speedNo.setError("Enter Value");
            speedNo.requestFocus();
            return;
        } else {
            double inputSpeed = Double.parseDouble(inputValue);

            if (inputSpeed <= 0.0) {
                Toast.makeText(Speed.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                String unitFrom = speedspinner1.getSelectedItem().toString();
                String unitTo = speedspinner2.getSelectedItem().toString();
                double convertedSpeed = convert(unitFrom, unitTo, inputSpeed);

                String resultText = String.format("%.2f %s", convertedSpeed, unitTo);
                result.setText(resultText);
            }
        }
    }

    private double convert(String unitFrom, String unitTo, double inputSpeed) {
        if (unitFrom.equals("m/s") && unitTo.equals("km/s")) {
            return inputSpeed * 0.001;
        } else if (unitFrom.equals("m/s") && unitTo.equals("miles/hr")) {
            return inputSpeed * 2.237;
        } else if (unitFrom.equals("km/s") && unitTo.equals("m/s")) {
            return inputSpeed * 1000.0;
        } else if (unitFrom.equals("km/s") && unitTo.equals("miles/hr")) {
            return inputSpeed * 2237;
        } else if (unitFrom.equals("miles/hr") && unitTo.equals("m/s")) {
            return inputSpeed / 2.237;
        } else if (unitFrom.equals("miles/hr") && unitTo.equals("km/s")) {
            return inputSpeed / 2237;
        } else if (unitFrom.equals(unitTo)) {
            return inputSpeed;
        } else {
            return 0.0;
        }
    }
}