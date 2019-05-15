package com.example.happyhealthyme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView date, title;

        public EventViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.event_date);
            title = (TextView) view.findViewById(R.id.event_title);
        }
    }

    public EventAdapter(List<Event> otherEventList)
    {
        this.eventList = otherEventList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_event, parent, false);

        return new EventViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position)
    {
        Event event = eventList.get(position);
        holder.date.setText(event.getDate());
        holder.title.setText(event.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return eventList.size();
    }
}
