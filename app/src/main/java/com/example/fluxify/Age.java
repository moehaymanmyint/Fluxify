package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Age extends AppCompatActivity {

    EditText birthDate;
    TextView result;
    Button ageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.age);

        birthDate = findViewById(R.id.birthDate);
        result = findViewById(R.id.result);
        ageBtn = findViewById(R.id.ageBtn);

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });
    }

    private void calculateAge() {
        String birthDateStr = birthDate.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        if (TextUtils.isEmpty(birthDateStr)) {
            birthDate.setError("Please select a valid birth date.");
            return;
        }

        try {
            Date birthDate = sdf.parse(birthDateStr);
            Date currentDate = new Date();

            long diffInMillis = currentDate.getTime() - birthDate.getTime();
            long ageInMillis = diffInMillis;

            int day = (int) (diffInMillis / (1000 * 60 * 60 * 24));
            int year = day / 365;
            int month = (int) ((day % 365) / 30.44);

            day = (int) (day % 30.44);
            String age = year + " Years, " + month + " Months, " + day + " Days";
            result.setText(age);
        } catch (Exception e) {
            e.printStackTrace();
            birthDate.setError("Invalid date format. Please select a valid date.");
        }
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                calendar.set(year, month, dayOfMonth);
                birthDate.setText(sdf.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}