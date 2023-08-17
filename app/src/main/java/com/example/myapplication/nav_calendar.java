package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.tab1.SQLite_db;
import com.example.myapplication.tab2.assignment_db;
import com.example.myapplication.tab3.exams_db;
import com.example.myapplication.tab4.lectures_db;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class nav_calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public RecyclerView calendarRecyclerView;
    public ClickListener listener;
    public ArrayList<String> days;
    public CalendarAdapter adapter;
    public TextView monthYearText;
    private LocalDate selectedDate;
    public LocalDate dateToday;
    public TextView dateCalendar; //Title of Table below calendar
    public TextView countLectures;
    public TextView countPlans;
    public TextView countExams;
    public TextView countAssignment;

    public SQLite_db plan_db;
    public lectures_db lectures_db;
    public assignment_db assignment_db;
    public exams_db exams_db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_calendar);

        drawerLayout = findViewById(R.id.cal_drawer_layout);
        navigationView=findViewById(R.id.cal_nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        calendarRecyclerView =findViewById(R.id.daysRecyclerview);
        monthYearText=findViewById(R.id.monthText);
        dateCalendar=findViewById(R.id.date_calendar);
        countLectures=findViewById(R.id.countLecture);
        countPlans=findViewById(R.id.countPlan);
        countExams=findViewById(R.id.countExam);
        countAssignment=findViewById(R.id.countAssignment);
        plan_db=new SQLite_db(this);
        lectures_db =new lectures_db(this);
        exams_db =new exams_db(this);
        assignment_db =new assignment_db(this);

        listener=new ClickListener() {
            @Override
            public void click(int index) {
                if(days.get(index)!=""){
//                    Toast.makeText(nav_calendar.this,"clicked item index is " + Integer.toString(index),Toast.LENGTH_SHORT).show();
                    selectedDate=selectedDate.minusDays( selectedDate.getDayOfMonth()-Integer.valueOf(days.get(index)));
                    setContentTable(selectedDate);
                    adapter=new CalendarAdapter(days,dateToday,selectedDate, listener);
                    calendarRecyclerView.setAdapter(adapter);
//                    Log.d("date clicked",selectedDate.toString());
                    adapter.notifyDataSetChanged();
                }
            }
        };
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        selectedDate=LocalDate.now();
        dateToday=LocalDate.now();

        setMonthview();
    }

    private void setContentTable(LocalDate selectedDate) {
        dateCalendar.setText(selectedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        countLectures.setText(String.valueOf(lectures_db.getCountbyDate(selectedDate)));
        countPlans.setText(String.valueOf(plan_db.getCountbyDate(selectedDate)));
        countExams.setText(String.valueOf(exams_db.getCountbyDate(selectedDate)));
        countAssignment.setText(String.valueOf(assignment_db.getCountbyDate(selectedDate)));
    }

    private void setMonthview() {
        monthYearText.setText(selectedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        days=daysInMonth(selectedDate);
        adapter=new CalendarAdapter(days,dateToday,selectedDate, listener);
        setContentTable(selectedDate);
        calendarRecyclerView.setAdapter(adapter);
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(this,7));
    }

    private ArrayList<String> daysInMonth(LocalDate date){
        ArrayList<String> temp=new ArrayList<>();
        YearMonth yearMonth=YearMonth.from(date);
        int totalDaysInMonth=yearMonth.lengthOfMonth();
        LocalDate firstOfMonth=selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for(int i=1;i<=42;i++){
            if(i<=dayOfWeek || i>totalDaysInMonth + dayOfWeek){
                temp.add("");
            }
            else{
                temp.add(String.valueOf(i-dayOfWeek));
            }
        }
        return temp;
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_home: {
                super.onBackPressed();
                break;
            }
            default:
                break;
        }
        //close navigation drawer
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    public void addMonth(View view) {
        selectedDate=selectedDate.plusMonths(1);
        setMonthview();
    }
    public void subtractMonth(View view) {
        selectedDate=selectedDate.minusMonths(1);
        setMonthview();
    }
}