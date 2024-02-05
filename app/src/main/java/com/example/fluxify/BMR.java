package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BMR extends AppCompatActivity {

    Button btn;
    EditText weight, height, age;
    TextView result;
    LinearLayout maleLayout, femaleLayout;
    ImageView mimg,fimg;
    double h = 0, w = 0, a = 0, bmr = 0;
    String user = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmr);

        btn = findViewById(R.id.bmrBtn);
        weight = findViewById(R.id.bmrWeight);
        height = findViewById(R.id.bmrHeight);
        age = findViewById(R.id.bmrAge);
        result = findViewById(R.id.bmrResult);
        maleLayout = findViewById(R.id.male);
        femaleLayout = findViewById(R.id.female);
        mimg = findViewById(R.id.maleImg);
        fimg = findViewById(R.id.femaleImg);

        maleLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                mimg.setColorFilter(getResources().getColor(R.color.tab));
                fimg.setColorFilter(getResources().getColor(R.color.black));
                user = "Male";
            }
        });
        femaleLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                mimg.setColorFilter(getResources().getColor(R.color.black));
                fimg.setColorFilter(getResources().getColor(R.color.tab));
                user = "Female";
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = weight.getText().toString();
                String str2 = height.getText().toString();
                String str3 = age.getText().toString();

                if (user.equals("0")) {
                    Toast.makeText(BMR.this, "Select your Gender", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(str1)) {
                    weight.setError("Select Weight");
                    weight.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(str2)) {
                    height.setError("Select Height");
                    height.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(str3)) {
                    age.setError("Select Age");
                    age.requestFocus();
                    return;
                }
                else {
                    calculate();
                }
            }
        });
    }

    private void calculate(){
        h = Double.parseDouble(height.getText().toString());
        w = Double.parseDouble(weight.getText().toString());
        a = Double.parseDouble(age.getText().toString());

        if (user.equals("Male")){
            bmr = ((10 * w) + (6.25 * h) - (5 * a) + 5);
            result.setText(Double.toString(bmr));
        }
        if (user.equals("Female")){
            bmr = ((10 * w) + (6.25 * h) - (5 * a) - 161);
            result.setText(Double.toString(bmr));
        }
    }
}