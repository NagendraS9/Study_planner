package com.example.myapplication.tab2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Calendar;

public class addActivity extends AppCompatActivity {
    TextView dateView,textTime;
    EditText e1,e2;
    Button save;
    assignment_db myDB;
    DatePickerDialog.OnDateSetListener mDateListener;
    int year,month,day,hour,minutes,amOrPm;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        e1=findViewById(R.id.editTextPersonName);
        e2=findViewById(R.id.description);
        dateView=findViewById(R.id.dateview);
        textTime=findViewById(R.id.textTime);
        save=findViewById(R.id.save);

        myDB=new assignment_db(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH);
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour=cal.get(Calendar.HOUR_OF_DAY);
        minutes=cal.get(Calendar.MINUTE);
        amOrPm=cal.get(Calendar.AM_PM);

        dateView.setText(DateFormat.format("dd/MM/yyyy",cal));
        textTime.setText(DateFormat.format("hh:mm aa",cal));

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog(addActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.updateDate(year,month,day);
                dialog.show();
            }
        });
        textTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(addActivity.this,
                        new TimePickerDialog.OnTimeSetListener(){
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hour=i;
                                Log.d("setTimeHour", Integer.toString(hour));
                                minutes=i1;
                                Calendar cal2=Calendar.getInstance();
                                cal2.set(0,0,0,i,i1);
                                amOrPm=cal2.get(Calendar.AM_PM);
                                textTime.setText(DateFormat.format("hh:mm aa",cal2));
                            }
                        },
                        hour,minutes,false);
                timePickerDialog.updateTime(hour,minutes);
                timePickerDialog.show();
            }
        });

        mDateListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {//i=year;i1=month(january=0);i2=day
                day=i2;
                month=i1;
                year=i;
                cal.set(i,i1,i2);
                dateView.setText(DateFormat.format("dd/MM/yyyy",cal));
            }
        };
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title=e1.getText().toString();
                String Desc=e2.getText().toString();
                if(!Title.equals("") && !Desc.equals("")){
                    myDB.insert(Title, Desc, day, month, year, hour, minutes, amOrPm);
                    addActivity.super.onBackPressed();
                }
                else{
                    Toast.makeText(addActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

//    public void onSave(View view){
//        myDB.insert(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
//    }

}