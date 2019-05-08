package com.example.happyhealthyme;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

public class HomeFragment extends Fragment {

    ImageButton biometricsButton;
    ImageButton addActivityButton;
    CalendarView calendarView;
    private int currentYear;
    private int currentMonth;
    private int currentDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        biometricsButton = view.findViewById(R.id.biometrics_button);
        addActivityButton = view.findViewById(R.id.add_activity_button);
        calendarView = view.findViewById(R.id.calendarView);

        biometricsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = updateBiometricsActivity.newIntent(getActivity(), currentYear, currentMonth, currentDay);
                startActivity(intent);
            }
        });

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = addActivityActivity.newIntent(getActivity(), currentYear, currentMonth, currentDay);
                startActivity(intent);//, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle()); uncomment if api is high enough
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                currentYear = year;
                currentMonth = month;
                currentDay = dayOfMonth;
            }
        });

        return view;
    }
}
