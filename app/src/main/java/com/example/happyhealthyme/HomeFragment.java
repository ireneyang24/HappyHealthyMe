package com.example.happyhealthyme;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.text.format.DateFormat;

public class HomeFragment extends Fragment {

    private ImageButton biometricsButton, addActivityButton;
    private CalendarView calendarView;
    private RecyclerView eventRecyclerView;
    private Button graphButton;
    private EventAdapter adapter;
    private List<Event> eventList = new ArrayList<>();

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
        eventRecyclerView = view.findViewById(R.id.eventRecyclerView);

        adapter = new EventAdapter(eventList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventRecyclerView.setAdapter(adapter);
        addTestData(); ////TEST////////

        graphButton = view.findViewById(R.id.graphButton);


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

        graphButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = viewGraphsActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

        return view;
    }

    private void addTestData()
    {


        Event event = new Event("test1", new Date());
        eventList.add(event);

        adapter.notifyDataSetChanged();
    }
}
