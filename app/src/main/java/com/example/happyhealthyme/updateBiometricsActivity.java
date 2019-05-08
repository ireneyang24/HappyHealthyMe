package com.example.happyhealthyme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class updateBiometricsActivity extends AppCompatActivity {
    private static final String EXTRA_YEAR = "com.happyhealthyme.year";
    private static final String EXTRA_MONTH = "com.happyhealthyme.month";
    private static final String EXTRA_DAY = "com.happyhealthyme.day";

    public static Intent newIntent(Context packageContext, int year, int month, int day) {
        Intent intent = new Intent(packageContext, updateBiometricsActivity.class);
        intent.putExtra(EXTRA_YEAR, year);
        intent.putExtra(EXTRA_MONTH, month);
        intent.putExtra(EXTRA_DAY, day);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biometrics);
    }
}
