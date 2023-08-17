package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.tab1.SQLite_db;
import com.example.myapplication.tab2.assignment_db;
import com.example.myapplication.tab3.exams_db;
import com.example.myapplication.tab4.lectures_db;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewholder> {
        private final ArrayList<String> days;
        private final ClickListener mListener;
        private final LocalDate dateToday;
        private final LocalDate dateSelected;

    public SQLite_db plan_db;
    public com.example.myapplication.tab4.lectures_db lectures_db;
    public com.example.myapplication.tab2.assignment_db assignment_db;
    public com.example.myapplication.tab3.exams_db exams_db;

    public CalendarAdapter(ArrayList<String> days, LocalDate dateToday, LocalDate dateSelected , ClickListener mListener) {
        this.days = days;
        this.mListener = mListener;
        this.dateToday=dateToday;
        this.dateSelected=dateSelected;
    }

    //    /**
//     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
//     * an item.
//     * <p>
//     * This new ViewHolder should be constructed with a new View that can represent the items
//     * of the given type. You can either create a new View manually or inflate it from an XML
//     * layout file.
//     * <p>
//     * The new ViewHolder will be used to display items of the adapter using
//     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
//     * different items in the data set, it is a good idea to cache references to sub views of
//     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
//     *
//     * @param parent   The ViewGroup into which the new View will be added after it is bound to
//     *                 an adapter position.
//     * @param viewType The view type of the new View.
//     * @return A new ViewHolder that holds a View of the given view type.
//     * @see #getItemViewType(int)
//     * @see #onBindViewHolder(ViewHolder, int)
//     */
    @NonNull
    @NotNull
    @Override
    public CalendarViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.calendar_cell,parent,false);
        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        layoutParams.height=(int)(parent.getHeight()*0.166666);

        plan_db=new SQLite_db(view.getContext());
        lectures_db =new lectures_db(view.getContext());
        exams_db =new exams_db(view.getContext());
        assignment_db =new assignment_db(view.getContext());

        return new CalendarViewholder(view);
    }

//    /**
//     * Called by RecyclerView to display the data at the specified position. This method should
//     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
//     * position.
//     * <p>
//     * Note that unlike {@link ListView}, RecyclerView will not call this method
//     * again if the position of the item changes in the data set unless the item itself is
//     * invalidated or the new position cannot be determined. For this reason, you should only
//     * use the <code>position</code> parameter while acquiring the related data item inside
//     * this method and should not keep a copy of it. If you need the position of an item later
//     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
//     * have the updated adapter position.
//     * <p>
//     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
//     * handle efficient partial bind.
//     *
//     * @param holder   The ViewHolder which should be updated to represent the contents of the
//     *                 item at the given position in the data set.
//     * @param position The position of the item within the adapter's data set.
//     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull CalendarViewholder holder, int position) {
        holder.dayOfMonth.setText(days.get(position));

        if(!days.get(position).equals("")) {
            LocalDate temp_date=LocalDate.of(Integer.valueOf(dateSelected.format(DateTimeFormatter.ofPattern("yyyy"))),Integer.valueOf(dateSelected.format(DateTimeFormatter.ofPattern("MM"))) ,Integer.valueOf(days.get(position)) );
            if(plan_db.getCountbyDate(temp_date)!=0 || lectures_db.getCountbyDate(temp_date)!=0 || assignment_db.getCountbyDate(temp_date)!=0 || exams_db.getCountbyDate(temp_date)!=0 ){
                holder.redDot.setVisibility(View.VISIBLE);
            }
            if (Integer.valueOf(days.get(position)) == Integer.valueOf(dateSelected.format(DateTimeFormatter.ofPattern("dd")))) {
                holder.dayOfMonth.setTextColor(Color.WHITE);
                holder.dayOfMonth.setBackgroundResource(R.drawable.calendar_cell_selecteddate_bg);
            }
            else if (Integer.valueOf(days.get(position)) == Integer.valueOf(dateToday.format(DateTimeFormatter.ofPattern("dd"))) && Integer.valueOf(dateSelected.format(DateTimeFormatter.ofPattern("MM")))==Integer.valueOf(dateToday.format(DateTimeFormatter.ofPattern("MM")))) {
                holder.dayOfMonth.setTextColor(Color.BLACK);
                holder.dayOfMonth.setBackgroundResource(R.drawable.calendar_cell_todaydate_bg);
            }
            else {
                holder.dayOfMonth.setTextColor(Color.BLACK);
            }
        }
            int index = holder.getAdapterPosition();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.click(index);
                }
            });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return days.size();
    }

}


//selectedDate.format(DateTimeFormatter.ofPattern("dd"))