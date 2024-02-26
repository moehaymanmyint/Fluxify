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
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        if (TextUtils.isEmpty(birthDateStr)) {
            birthDate.setError("Select your Birth Date.");
            return;
        }

        try {
            Date selectDate = sdfInput.parse(birthDateStr);

            if (selectDate == null) {
                birthDate.setError("Invalid Date, Please enter dd/mm/yyyy");
                return;
            }

            if (selectDate.after(new Date())) {
                birthDate.setError("Invalid Date, Please enter a past date");
                return;
            }

            Calendar birthDateCalendar = Calendar.getInstance();
            birthDateCalendar.setTime(selectDate);

            Calendar currentDateCalendar = Calendar.getInstance();

            int year = currentDateCalendar.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR);
            int month = currentDateCalendar.get(Calendar.MONTH) - birthDateCalendar.get(Calendar.MONTH);
            int day = currentDateCalendar.get(Calendar.DAY_OF_MONTH) - birthDateCalendar.get(Calendar.DAY_OF_MONTH);

            if (day < 0) {
                month--;
                int daysInMonth = birthDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                day += daysInMonth;
            }
            if (month < 0) {
                year--;
                month += 12;
            }

            String age = year + " Years, " + month + " Months, " + day + " Days";
            result.setText(age);
        } catch (ParseException e) {
            e.printStackTrace();
            birthDate.setError("Invalid date format. Please enter mm/dd/yyyy");
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