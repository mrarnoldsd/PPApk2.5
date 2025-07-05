package com.prisonpay.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentExpression = new StringBuilder();
    private final String unlockCode = "187/69";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.calculator_display);
    }

    // ✅ Method name now matches XML onClick
    public void onCalculatorButtonClick(View v) {
        Button button = (Button) v;
        String input = button.getText().toString();

        switch (input) {
            case "C":
                currentExpression.setLength(0
