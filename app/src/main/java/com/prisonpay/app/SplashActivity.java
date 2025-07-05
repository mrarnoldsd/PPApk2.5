package com.prisonpay.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Transition to CalculatorActivity after delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, CalculatorActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}
