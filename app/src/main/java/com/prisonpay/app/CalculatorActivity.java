package com.prisonpay.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.calculatorDisplay);

        // Digits and operators
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnDivide, R.id.btnClear
        };

        for (int id : buttonIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> {
                String value = btn.getText().toString();
                if (value.equals("C")) {
                    input = "";
                } else {
                    input += value;
                }
                display.setText(input);
            });
        }

        Button equals = findViewById(R.id.btnEquals);
        equals.setOnClickListener(v -> {
            if (input.equals("187÷69")) {
                Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Nah, that ain't it.", Toast.LENGTH_SHORT).show();
                input = "";
                display.setText("");
            }
        });
    }
}
