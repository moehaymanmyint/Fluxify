package com.example.fluxify;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Temperature extends AppCompatActivity {

    private EditText tempNo;
    private Spinner tempSpinner1, tempSpinner2;
    private Button tempBtn;
    private TextView result;

    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);

        tempNo = findViewById(R.id.tempNo);
        tempSpinner1 = findViewById(R.id.tempSpinner1);
        tempSpinner2 = findViewById(R.id.tempSpinner2);
        tempBtn = findViewById(R.id.tempBtn);
        result = findViewById(R.id.result);

        tempSpinner1 = (Spinner) findViewById(R.id.tempSpinner1);
        adapter = ArrayAdapter.createFromResource(this, R.array.temp_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        tempSpinner1.setAdapter(adapter);

        tempSpinner2 = (Spinner) findViewById(R.id.tempSpinner2);
        adapter = ArrayAdapter.createFromResource(this, R.array.temp_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        tempSpinner2.setAdapter(adapter);

        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String inputValue = tempNo.getText().toString();
        if (inputValue.isEmpty()) {
            tempNo.setError("Enter Value");
            tempNo.requestFocus();
            return;
        }

        double inputTemperature = Double.parseDouble(inputValue);
        String unitFrom = tempSpinner1.getSelectedItem().toString();
        String unitTo = tempSpinner2.getSelectedItem().toString();
        double convertedTemperature = convert(unitFrom, unitTo, inputTemperature);

        result.setText(String.format("%.2f %s", convertedTemperature, unitTo));
    }

    private double convert(String unitFrom, String unitTo, double inputTemperature) {
        if (unitFrom.equals("°F") && unitTo.equals("°C")) {
            return (inputTemperature - 32) * 5.0 / 9.0;
        }
        else if (unitFrom.equals("°C") && unitTo.equals("°F")) {
            return (inputTemperature * 9.0 / 5.0) + 32;
        }
        else if (unitFrom.equals(unitTo)) {
            return inputTemperature;
        }
        else {
            return 0.0;
        }
    }
}
