package com.example.myapplication.tab2;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClickListener;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link second_tab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class second_tab extends Fragment {

    assignment_db myDB;
    viewAdapter myadapter;
    ArrayList<Object> arrPlans;
    RecyclerView recyclerView;
    ClickListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public second_tab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment second_tab.
     */
    // TODO: Rename and change types and number of parameters
    public static second_tab newInstance(String param1, String param2) {
        second_tab fragment = new second_tab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_tab, container, false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.button_add_assignment);

        myDB=new assignment_db(getActivity());
        arrPlans =myDB.getAll();

        recyclerView
                = view.findViewById(R.id.recyclerViewTab2);
        listener = new ClickListener() {
            @Override
            public void click(int index){
//                Toast.makeText(getActivity(),"clicked item index is " + Integer.toString(index),Toast.LENGTH_LONG).show();
                Log.d("recycleview", "clicked");
                Assignment_model obj=(Assignment_model) arrPlans.get(index);
                Intent intent =new Intent(getActivity(), descriptionActivity.class);
                intent.putExtra("index",index);
                intent.putExtra("Title",obj.Title);
                intent.putExtra("Desc",obj.Desc);
                intent.putExtra("id",obj.id);
                intent.putExtra("day",(String) DateFormat.format("MMM dd, yyyy",obj.cal));
                intent.putExtra("Time",(String)DateFormat.format("hh:mm aa",obj.cal));

                startActivity(intent);

            }
        };
        myadapter=new viewAdapter(arrPlans,getContext(),listener);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myadapter.notifyDataSetChanged();


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                changeActivity();
            }
        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        arrPlans = myDB.getAll();
        myadapter = new viewAdapter(arrPlans, getContext(),listener);
        recyclerView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
    }

    public void changeActivity(){
        Intent intent =new Intent(getActivity(), addActivity.class);
        startActivity(intent);
    }
}