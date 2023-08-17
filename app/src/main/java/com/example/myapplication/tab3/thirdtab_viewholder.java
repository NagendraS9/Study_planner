package com.example.myapplication.tab3;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class thirdtab_viewholder extends RecyclerView.ViewHolder {
    TextView Title;
    TextView Date;
    View view;

    public thirdtab_viewholder(View itemView) {
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