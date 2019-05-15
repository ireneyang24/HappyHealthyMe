package com.example.happyhealthyme;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.UUID;

public class addActivityActivity extends AppCompatActivity {
    private static final String EXTRA_YEAR = "com.happyhealthyme.year";
    private static final String EXTRA_MONTH = "com.happyhealthyme.month";
    private static final String EXTRA_DAY = "com.happyhealthyme.day";

    private int year;
    private int month;
    private int day;

    private EditText activityName;
    private DatePicker datePicker;
    private CheckBox mondayCheckbox;
    private CheckBox tuesdayCheckbox;
    private CheckBox wednesdayCheckbox;
    private CheckBox thursdayCheckbox;
    private CheckBox fridayCheckbox;
    private CheckBox saturdayCheckbox;
    private CheckBox sundayCheckbox;
    private EditText notes;
    private Button cancelActivityButton;
    private Button addActivityButton;

    public static Intent newIntent(Context packageContext, int year, int month, int day) {
        Intent intent = new Intent(packageContext, addActivityActivity.class);
        intent.putExtra(EXTRA_YEAR, year);
        intent.putExtra(EXTRA_MONTH, month);
        intent.putExtra(EXTRA_DAY, day);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        //getWindow().setExitTransition(new Fade()); uncomment if minimum api is high enough

        year = getIntent().getIntExtra(EXTRA_YEAR, 0);
        month = getIntent().getIntExtra(EXTRA_MONTH, 0);
        day = getIntent().getIntExtra(EXTRA_DAY, 0);

        activityName = findViewById(R.id.activity_name_field);
        datePicker = findViewById(R.id.activity_date_picker);
        mondayCheckbox = findViewById(R.id.monday_checkbox);
        tuesdayCheckbox = findViewById(R.id.tuesday_checkbox);
        wednesdayCheckbox = findViewById(R.id.wednesday_checkbox);
        thursdayCheckbox = findViewById(R.id.thursday_checkbox);
        fridayCheckbox = findViewById(R.id.friday_checkbox);
        saturdayCheckbox = findViewById(R.id.saturday_checkbox);
        sundayCheckbox = findViewById(R.id.sunday_checkbox);
        notes = findViewById(R.id.activity_notes);
        cancelActivityButton = findViewById(R.id.cancel_activity_button);
        addActivityButton = findViewById(R.id.add_activity_button);


        if (!(year ==0 && month == 0 && day == 0)) //by default, the date on the picker is set to the current date, so unless we pass all 0's to this function, which means the previous screen didnt change the date, we update the datepicker
            datePicker.updateDate(year, month, day);

        cancelActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newActivityName = activityName.getText().toString();
                String newActivityDate = datePicker.getMonth() + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear();
                String repeatSettings = mondayCheckbox.isChecked() + ","
                        + tuesdayCheckbox.isChecked() + ","
                        + wednesdayCheckbox.isChecked() + ","
                        + thursdayCheckbox.isChecked() + ","
                        + fridayCheckbox.isChecked() + ","
                        + saturdayCheckbox.isChecked() + ","
                        + sundayCheckbox.isChecked(); //comma delimited weekday repetition settings
                String newActivityNotes = notes.getText().toString();

                System.out.println("COLLECTED DATA:");
                System.out.println("Name: " + newActivityName);
                System.out.println("Date: " + newActivityDate);
                System.out.println("repeatSettings: " + repeatSettings);
                System.out.println("Notes: " + newActivityNotes);

                if (TextUtils.isEmpty(newActivityName)){
                    activityName.setError("You must enter a title for this activity");
                }else {
                    String activityUUID = java.util.UUID.randomUUID().toString();
                    System.out.println("Generated UUID for valid activity: " + activityUUID);
                    DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(HappyHealthyMeDbSchema.ActivitiesTable.Cols.UUID, activityUUID);
                    values.put(HappyHealthyMeDbSchema.ActivitiesTable.Cols.NAME, newActivityName);
                    values.put(HappyHealthyMeDbSchema.ActivitiesTable.Cols.DATE, newActivityDate);
                    values.put(HappyHealthyMeDbSchema.ActivitiesTable.Cols.REPEAT_DAYS, repeatSettings);
                    values.put(HappyHealthyMeDbSchema.ActivitiesTable.Cols.NOTES, newActivityNotes);
                    // Insert the new row
                    db.insert(HappyHealthyMeDbSchema.ActivitiesTable.NAME, null, values);
                    finish();
                }

            }
        });

    }
}
