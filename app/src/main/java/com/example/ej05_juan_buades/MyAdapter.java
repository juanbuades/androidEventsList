package com.example.ej05_juan_buades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolderEvents> {

    ArrayList <String> listEvents;

    public MyAdapter(ArrayList<String> listEvents) {
        this.listEvents = listEvents;
    }

    @NonNull
    @Override
    public ViewHolderEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list, null,false);
        return new ViewHolderEvents(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEvents holder, int position) {
        holder.eventAsign(listEvents.get(position));
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public class ViewHolderEvents extends RecyclerView.ViewHolder {
        TextView event;
        public ViewHolderEvents(@NonNull View itemView) {
            super(itemView);
            event=itemView.findViewById(R.id.idEvento);
        }

        public void eventAsign(String eventt) {
            event.setText(eventt);
        }
    }
}
