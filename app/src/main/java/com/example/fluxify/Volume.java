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

public class Volume extends AppCompatActivity {

    EditText volumeNo;
    Button volumeBtn;
    TextView result;
    Spinner volumespinner1, volumespinner2;
    ArrayAdapter<CharSequence>adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume);

        volumeNo = findViewById(R.id.volumeNo);
        volumeBtn = findViewById(R.id.volumeBtn);
        result = findViewById(R.id.result);
        volumespinner1 = findViewById(R.id.volumeSpinner1);
        volumespinner2 = findViewById(R.id.volumeSpinner2);

        adapter = ArrayAdapter.createFromResource(this, R.array.volume_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        volumespinner1.setAdapter(adapter);
        volumespinner2.setAdapter(adapter);
        
        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertVolume();
            }
        });
    }

    private void convertVolume() {
        String inputValue = volumeNo.getText().toString();
        if (inputValue.isEmpty()) {
            volumeNo.setError("Enter Value");
            volumeNo.requestFocus();
            return;
        } else {
            double inputVolume = Double.parseDouble(inputValue);
            String unitFrom = volumespinner1.getSelectedItem().toString();
            String unitTo = volumespinner2.getSelectedItem().toString();
            double convertedVolume = convert(unitFrom, unitTo, inputVolume);

            String resultText = String.format("%.2f %s", convertedVolume, unitTo);
            result.setText(resultText);
        }
    }

    private double convert(String unitFrom, String unitTo, double inputVolume) {
        if (unitFrom.equals("m3") && unitTo.equals("cm3")) {
            return inputVolume * 1000000.0;
        } else if (unitFrom.equals("m3") && unitTo.equals("L")) {
            return inputVolume * 1000.0;
        } else if (unitFrom.equals("m3") && unitTo.equals("mL")) {
            return inputVolume * 1000000.0;
        } else if (unitFrom.equals("cm3") && unitTo.equals("m3")) {
            return inputVolume / 1000000.0;
        } else if (unitFrom.equals("cm3") && unitTo.equals("L")) {
            return inputVolume * 0.001;
        } else if (unitFrom.equals("cm3") && unitTo.equals("mL")) {
            return inputVolume;
        } else if (unitFrom.equals("L") && unitTo.equals("m3")) {
            return inputVolume / 1000.0;
        } else if (unitFrom.equals("L") && unitTo.equals("cm3")) {
            return inputVolume * 1000.0;
        } else if (unitFrom.equals("L") && unitTo.equals("mL")) {
            return inputVolume * 1000.0;
        } else if (unitFrom.equals("mL") && unitTo.equals("m3")) {
            return inputVolume / 1000000.0;
        } else if (unitFrom.equals("mL") && unitTo.equals("cm3")) {
            return inputVolume;
        } else if (unitFrom.equals("mL") && unitTo.equals("L")) {
            return inputVolume * 0.001;
        } else if (unitFrom.equals(unitTo)) {
            return inputVolume;
        } else {
            return 0.0;
        }
    }
}