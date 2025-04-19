package com.example.bmi_calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText weight;
        EditText heightFt;
        EditText heightIn;
        Button calButton;
        TextView result;
        LinearLayout mainLayout;


        weight = findViewById(R.id.weightEditText);
        heightFt = findViewById(R.id.heightEditTextFt);
        heightIn = findViewById(R.id.heightEditTextIn);
        calButton = findViewById(R.id.cal_btn);
        result = findViewById(R.id.result);
        mainLayout = findViewById(R.id.mainLL);

        calButton.setOnClickListener(view -> {
            String weightStr = weight.getText().toString();
            String heightFtStr = heightFt.getText().toString();
            String heightInStr = heightIn.getText().toString();

            if (weightStr.isEmpty() || heightFtStr.isEmpty() || heightInStr.isEmpty()) {
                result.setText(getString(R.string.empty_fields));
                return;
            }

            double weightValue = Double.parseDouble(weightStr);
            double heightFtValue = Double.parseDouble(heightFtStr);
            double heightInValue = Double.parseDouble(heightInStr);

            double totalHeightInInches = (heightFtValue * 12) + heightInValue;
            double totalHeightInMeters = totalHeightInInches * 0.0254;

            double bmi = weightValue / (totalHeightInMeters * totalHeightInMeters);

            if(bmi < 18.5){
                result.setText(String.format("%s\nYour BMI is: %.2f", getString(R.string.underweight), bmi));
                mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.underweight));
            } else if(bmi >= 18.5 && bmi < 24.9){
                result.setText(String.format("%s\nYour BMI is: %.2f", getString(R.string.normal), bmi));
                mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.normal));
            } else if(bmi >= 25 && bmi < 29.9){
                result.setText(String.format("%s\nYour BMI is: %.2f", getString(R.string.overweight), bmi));
                mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.overweight));
            } else {
                result.setText(String.format("%s\nYour BMI is: %.2f", getString(R.string.obese), bmi));
                mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.obese));
            }
        });

    }
}


