package com.prisonpay.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 4000; // 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView jailBars = findViewById(R.id.jailBars);
        ImageView logo = findViewById(R.id.logoImage);

        Animation barsAnim = AnimationUtils.loadAnimation(this, R.anim.jail_slide_close);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.logo_fade_in);

        jailBars.startAnimation(barsAnim);

        new Handler().postDelayed(() -> {
            logo.startAnimation(fadeIn);
            logo.setVisibility(ImageView.VISIBLE);
        }, 2000); // Delay before fade

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, CalculatorActivity.class));
            finish();
        }, SPLASH_TIME);
    }
}
