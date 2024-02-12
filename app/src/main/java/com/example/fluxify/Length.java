package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class Length extends AppCompatActivity {

    EditText lengthNo;
    Button lengthBtn;
    TextView result;
    Spinner lengthspinner1, lengthspinner2;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.length);

        lengthNo = findViewById(R.id.lengthNo);
        lengthBtn = findViewById(R.id.lengthbBtn);
        result = findViewById(R.id.result);
        lengthspinner1 = findViewById(R.id.lengthSpinner1);
        lengthspinner2 = findViewById(R.id.lengthSpinner2);

        adapter = ArrayAdapter.createFromResource(this, R.array.length_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        lengthspinner1.setAdapter(adapter);
        lengthspinner2.setAdapter(adapter);

        lengthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
            }
        });
    }

    private void convertLength() {
        String inputValue = lengthNo.getText().toString();
        if (inputValue.isEmpty()) {
            lengthNo.setError("Enter Value");
            lengthNo.requestFocus();
            return;
        } else {
            double inputLength = Double.parseDouble(inputValue);
            String unitFrom = lengthspinner1.getSelectedItem().toString();
            String unitTo = lengthspinner2.getSelectedItem().toString();
            double convertedLength = convert(unitFrom, unitTo, inputLength);

            String resultText = String.format("%.2f %s", convertedLength, unitTo);
            result.setText(resultText);
        }
    }

    private double convert(String unitFrom, String unitTo, double inputLength) {
        if (unitFrom.equals("km") && unitTo.equals("m")) {
            return inputLength * 1000;
        } else if (unitFrom.equals("km") && unitTo.equals("cm")) {
            return inputLength * 100000;
        } else if (unitFrom.equals("km") && unitTo.equals("mm")) {
            return inputLength * 1000000;
        } else if (unitFrom.equals("km") && unitTo.equals("ft")) {
            return inputLength * 3280.84;
        } else if (unitFrom.equals("km") && unitTo.equals("in")) {
            return inputLength * 39370.1;
        } else if (unitFrom.equals("m") && unitTo.equals("km")) {
            return inputLength / 1000;
        } else if (unitFrom.equals("m") && unitTo.equals("cm")) {
            return inputLength * 100;
        } else if (unitFrom.equals("m") && unitTo.equals("mm")) {
            return inputLength * 1000;
        } else if (unitFrom.equals("m") && unitTo.equals("ft")) {
            return inputLength * 3.28084;
        } else if (unitFrom.equals("m") && unitTo.equals("in")) {
            return inputLength * 39.3701;
        } else if (unitFrom.equals("cm") && unitTo.equals("km")) {
            return inputLength / 100000;
        } else if (unitFrom.equals("cm") && unitTo.equals("m")) {
            return inputLength / 100;
        } else if (unitFrom.equals("cm") && unitTo.equals("mm")) {
            return inputLength * 10;
        } else if (unitFrom.equals("cm") && unitTo.equals("ft")) {
            return inputLength * 0.0328084;
        } else if (unitFrom.equals("cm") && unitTo.equals("in")) {
            return inputLength * 0.393701;
        } else if (unitFrom.equals("mm") && unitTo.equals("km")) {
            return inputLength / 1e+6;
        } else if (unitFrom.equals("mm") && unitTo.equals("m")) {
            return inputLength / 1000;
        } else if (unitFrom.equals("mm") && unitTo.equals("cm")) {
            return inputLength / 10;
        } else if (unitFrom.equals("mm") && unitTo.equals("ft")) {
            return inputLength * 0.00328084;
        } else if (unitFrom.equals("mm") && unitTo.equals("in")) {
            return inputLength * 0.0393701;
        } else if (unitFrom.equals("ft") && unitTo.equals("km")) {
            return inputLength / 3280.84;
        } else if (unitFrom.equals("ft") && unitTo.equals("m")) {
            return inputLength / 3.28084;
        } else if (unitFrom.equals("ft") && unitTo.equals("cm")) {
            return inputLength / 0.0328084;
        } else if (unitFrom.equals("ft") && unitTo.equals("mm")) {
            return inputLength / 0.00328084;
        } else if (unitFrom.equals("ft") && unitTo.equals("in")) {
            return inputLength * 12;
        } else if (unitFrom.equals("in") && unitTo.equals("km")) {
            return inputLength / 39370.1;
        } else if (unitFrom.equals("in") && unitTo.equals("m")) {
            return inputLength / 39.3701;
        } else if (unitFrom.equals("in") && unitTo.equals("cm")) {
            return inputLength / 0.393701;
        } else if (unitFrom.equals("in") && unitTo.equals("mm")) {
            return inputLength / 0.0393701;
        } else if (unitFrom.equals("in") && unitTo.equals("ft")) {
            return inputLength / 12;
        } else if (unitFrom.equals(unitTo)) {
            return inputLength;
        } else {
            return 0.0;
        }
    }
}