package com.example.happyhealthyme;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class updateBiometricsActivity extends AppCompatActivity {
    private static final String EXTRA_YEAR = "com.happyhealthyme.year";
    private static final String EXTRA_MONTH = "com.happyhealthyme.month";
    private static final String EXTRA_DAY = "com.happyhealthyme.day";

    private int year;
    private int month;
    private int day;

    private EditText weightEditText;
    private EditText heightEditText;
    private EditText sleepEditText;
    private DatePicker datePicker;
    private Button recordBiometricsButton;

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

        year = getIntent().getIntExtra(EXTRA_YEAR, 0);
        month = getIntent().getIntExtra(EXTRA_MONTH, 0);
        day = getIntent().getIntExtra(EXTRA_DAY, 0);

        weightEditText = findViewById(R.id.weight_field);
        heightEditText = findViewById(R.id.height_field);
        sleepEditText = findViewById(R.id.sleep_field);
        datePicker = findViewById(R.id.biometrics_date_picker);
        recordBiometricsButton = findViewById(R.id.record_biometrics_button);

        if (!(year ==0 && month == 0 && day == 0)) //by default, the date on the picker is set to the current date, so unless we pass all 0's to this function, which means the previous screen didnt change the date, we update the datepicker
            datePicker.updateDate(year, month, day);

        //probably want to add something here that reads the database and fills the biometric fields if data is present for this date

        recordBiometricsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String dateOfEntry = datePicker.getMonth() + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear();
                String weight = weightEditText.getText().toString();
                String height = heightEditText.getText().toString();
                String sleep = sleepEditText.getText().toString();

                System.out.println("COLLECTED BIOMETRICS:");
                System.out.println("DATE: " + dateOfEntry);
                System.out.println("WEIGHT: " + weight);
                System.out.println("HEIGHT: " + height);
                System.out.println("SLEEP: " + sleep);

                if(TextUtils.isEmpty(weight) && TextUtils.isEmpty(height) && TextUtils.isEmpty(sleep)){
                    sleepEditText.setError("You must fill at least one field to record biometrics");
                } else {
                    String biometricUUID = java.util.UUID.randomUUID().toString();

                    DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(HappyHealthyMeDbSchema.BiometricsTable.Cols.UUID, biometricUUID);
                    values.put(HappyHealthyMeDbSchema.BiometricsTable.Cols.DATE, dateOfEntry);
                    values.put(HappyHealthyMeDbSchema.BiometricsTable.Cols.WEIGHT, weight);
                    values.put(HappyHealthyMeDbSchema.BiometricsTable.Cols.HEIGHT, height);
                    values.put(HappyHealthyMeDbSchema.BiometricsTable.Cols.SLEEP, sleep);
                    // Insert the new row
                    db.insert(HappyHealthyMeDbSchema.BiometricsTable.NAME, null, values);
                }
            }
        });
    }
}
