package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class CalendarViewholder extends RecyclerView.ViewHolder{
    public final TextView dayOfMonth;
    public final ImageView redDot;
    public CalendarViewholder(@NonNull @NotNull View itemView) {
        super(itemView);
        dayOfMonth=itemView.findViewById(R.id.cell);
        redDot=itemView.findViewById(R.id.red_dot);

    }
}
