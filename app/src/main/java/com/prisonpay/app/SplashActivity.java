package com.prisonpay.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentExpression = new StringBuilder();
    private final String unlockCode = "187/69";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.calculator_display);

        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide,
                R.id.btn_dot, R.id.btn_clear, R.id.btn_equals
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View v) {
        Button button = (Button) v;
        String input = button.getText().toString();

        switch (input) {
            case "C":
                currentExpression.setLength(0);
                display.setText("");
                break;
            case "=":
                evaluateExpression();
                break;
            default:
                currentExpression.append(input);
                display.setText(currentExpression.toString());
        }
    }

    private void evaluateExpression() {
        String expression = currentExpression.toString().replace("×", "*").replace("÷", "/");

        // Check unlock code
        if (expression.equals(unlockCode)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        // Evaluate normally
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            Object result = engine.eval(expression);
            display.setText(result.toString());
            currentExpression.setLength(0);
            currentExpression.append(result.toString());
        } catch (ScriptException e) {
            display.setText("Error");
            currentExpression.setLength(0);
        }
    }
}
