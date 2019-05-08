package com.example.happyhealthyme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Event> eventList = new ArrayList<>();
    private RecyclerView eventRecyclerView;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new HomeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        eventRecyclerView = (RecyclerView) findViewById(R.id.eventRecyclerView);

        adapter = new EventAdapter(eventList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventRecyclerView.setAdapter(adapter);

        addTestData();
    }

    private void addTestData()
    {
        Event event = new Event("test1", new Date());
        eventList.add(event);

        adapter.notifyDataSetChanged();
    }
}

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
////        View view = inflater.inflate(R.layout.activity_main, container, false);
//
//        eventRecyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
//        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        updateUI();
//
//        return view;
//    }
//
//    private void updateUI() {
//        EventLab eventLab = EventLab.get(this);
//        List<Event> crimes = eventLab.getEvents();
//
//        if (adapter == null) {
//            adapter = new EventAdapter(crimes);
//            eventRecyclerView.setAdapter(adapter);
//        } else {
//            adapter.notifyDataSetChanged();
//        }
//    }

//    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView mTitleTextView;
//        private TextView mDateTextView;
//        private Event mEvent;
//
//        public EventHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.list_item_event, parent, false));
//            itemView.setOnClickListener(this);
//            mTitleTextView = (TextView) itemView.findViewById(R.id.event_title);
//            mDateTextView = (TextView) itemView.findViewById(R.id.event_date);
//        }
//        public void bind(Event event) {
//            mEvent = event;
//            mTitleTextView.setText(mEvent.getTitle());
////            mDateTextView.setText(mEvent.getDate().toString());
//        }
//        @Override
//        public void onClick(View view) {
//            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
//            startActivity(intent);
//        }
//
//    }

//    public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>
//    {
//        private List<Event> events;
//        private String[] dataset;

//        public class ViewHolder extends RecyclerView.ViewHolder
//        {
//            public TextView textView;
//
//            public ViewHolder(TextView tv)
//            {
//                super(tv);
//                textView = tv;
//            }
//        }

//        public EventAdapter(String[] otherDataset)
//        {
//            dataset = otherDataset;
//        }
//
//        @Override
//        public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
//                                                         int viewType) {
//            // create a new view
//            TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.my_text_view, parent, false);
//        ...
//            MyViewHolder vh = new MyViewHolder(v);
//            return vh;
//        }

        // Replace the contents of a view (invoked by the layout manager)
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position) {
//            // - get element from your dataset at this position
//            // - replace the contents of the view with that element
//            holder.textView.setText(mDataset[position]);
//
//        }
//
//        // Return the size of your dataset (invoked by the layout manager)
//        @Override
//        public int getItemCount() {
//            return mDataset.length;
//        }
//
//    }
//}
