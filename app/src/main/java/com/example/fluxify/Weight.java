package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Weight extends AppCompatActivity {

    private EditText weightNo;
    private Button weightBtn;
    private TextView result;
    Spinner weightspinner1, weightspinner2;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight);

        weightNo = findViewById(R.id.weightNo);
        weightBtn = findViewById(R.id.weightBtn);
        result = findViewById(R.id.result);

        weightspinner1 = (Spinner) findViewById(R.id.weightSpinner1);
        adapter = ArrayAdapter.createFromResource(this, R.array.weight_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        weightspinner1.setAdapter(adapter);

        weightspinner2 = (Spinner) findViewById(R.id.weightSpinner2);
        adapter = ArrayAdapter.createFromResource(this, R.array.weight_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        weightspinner2.setAdapter(adapter);

        weightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeight();
            }
        });
    }

    private void convertWeight() {
        String inputValue = weightNo.getText().toString();
        if (inputValue.isEmpty()) {
            weightNo.setError("Enter Value");
            weightNo.requestFocus();
            return;
        }

        double inputLength = Double.parseDouble(inputValue);
        String unitFrom = weightspinner1.getSelectedItem().toString();
        String unitTo = weightspinner2.getSelectedItem().toString();
        double convertedWeight = convert(unitFrom, unitTo, inputLength);

        String resultText = String.format("%.2f %s", convertedWeight, unitTo);
        result.setText(resultText);
    }

    private double convert(String unitFrom, String unitTo, double inputWeight) {
        if (unitFrom.equals("kg") && unitTo.equals("g")) {
            return inputWeight * 1000;
        } else if (unitFrom.equals("kg") && unitTo.equals("lb")) {
            return inputWeight * 2.20462;
        } else if (unitFrom.equals("g") && unitTo.equals("kg")) {
            return inputWeight / 1000;
        } else if (unitFrom.equals("g") && unitTo.equals("lb")) {
            return inputWeight * 0.00220462;
        } else if (unitFrom.equals("lb") && unitTo.equals("kg")) {
            return inputWeight / 2.20462;
        } else if (unitFrom.equals("lb") && unitTo.equals("g")) {
            return inputWeight / 0.00220462;
        } else if (unitFrom.equals(unitTo)) {
            return inputWeight;
        } else {
            return 0.0;
        }
    }
}