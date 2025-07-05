package com.prisonpay.app;

import android.content.Intent; import android.os.Bundle; import android.view.View; import android.widget.Button; import android.widget.TextView; import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

private TextView inputText;
private StringBuilder currentInput = new StringBuilder();
private final String unlockCode = "187/69=";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);

    inputText = findViewById(R.id.calculator_display);
}

public void onCalculatorButtonClick(View view) {
    Button button = (Button) view;
    String value = button.getText().toString();

    if (value.equals("C")) {
        currentInput.setLength(0);
    } else {
        currentInput.append(value);
    }

    inputText.setText(currentInput.toString());

    if (currentInput.toString().equals(unlockCode)) {
        Toast.makeText(this, "Access Granted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

}



import android.content.Intent; import android.os.Bundle; import android.view.View; import android.widget.Button; import android.widget.TextView; import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

private TextView inputText;
private StringBuilder currentInput = new StringBuilder();
private final String unlockCode = "187/69=";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);

    inputText = findViewById(R.id.calculator_display);
}

public void onCalculatorButtonClick(View view) {
    Button button = (Button) view;
    String value = button.getText().toString();

    if (value.equals("C")) {
        currentInput.setLength(0);
    } else {
        currentInput.append(value);
    }

    inputText.setText(currentInput.toString());

    if (currentInput.toString().equals(unlockCode)) {
        Toast.makeText(this, "Access Granted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

}

