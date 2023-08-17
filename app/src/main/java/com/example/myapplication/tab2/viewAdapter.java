package com.example.myapplication.tab2;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClickListener;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class viewAdapter extends RecyclerView.Adapter<secondtab_viewholder> {

    private ArrayList<Object> arrPlan;
    Context ctx;
    ClickListener listener;

    public viewAdapter(ArrayList<Object> arrPlan,Context ctx,ClickListener listener) {
        this.arrPlan=arrPlan;
        this.ctx = ctx;
        this.listener=listener;
    }
    //
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
    public secondtab_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(ctx);
        View view
                = inflater
                .inflate(R.layout.singleitem_tab1,
                        parent, false);

        secondtab_viewholder viewHolder
                = new secondtab_viewholder(view);
        return viewHolder;
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
    public void onBindViewHolder(@NonNull @NotNull secondtab_viewholder holder, int position) {
        int index = holder.getAdapterPosition();
        Assignment_model obj= (Assignment_model) arrPlan.get(position);
        holder.Title
                .setText(obj.Title);
        holder.Date
                .setText((String) DateFormat.format("dd MMM yyyy",obj.cal)+"  "+(String)DateFormat.format("hh:mm aa",obj.cal));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listener.click(index);
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
        return arrPlan.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}