package com.example.myapplication.tab2;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class secondtab_viewholder extends RecyclerView.ViewHolder {
    TextView Title;
    TextView Date;
    View view;

    public secondtab_viewholder(View itemView) {
        super(itemView);
        Title
                = (TextView) itemView
                .findViewById(R.id.textView8);
        Date
                = (TextView) itemView
                .findViewById(R.id.textView10);
        view = itemView;
    }
}