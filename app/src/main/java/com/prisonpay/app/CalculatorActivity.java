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

    public void onCalculatorButtonClick(View v) {
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

        if (expression.equals(unlockCode)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        try {
            double result = simpleEval(expression);
            display.setText(String.valueOf(result));
            currentExpression.setLength(0);
            currentExpression.append(result);
        } catch (Exception e) {
            display.setText("Error");
            currentExpression.setLength(0);
        }
    }

    private double simpleEval(String expr) {
        String[] tokens = expr.split("(?<=[-+*/])|(?=[-+*/])");
        double result = Double.parseDouble(tokens[0].trim());

        for (int i = 1; i < tokens.length; i += 2) {
            String op = tokens[i].trim();
            double next = Double.parseDouble(tokens[i + 1].trim());

            switch (op) {
                case "+":
                    result += next;
                    break;
                case "-":
                    result -= next;
                    break;
                case "*":
                    result *= next;
                    break;
                case "/":
                    result /= next;
                    break;
                default:
                    throw new RuntimeException("Invalid operator");
            }
        }
        return result;
    }
}
