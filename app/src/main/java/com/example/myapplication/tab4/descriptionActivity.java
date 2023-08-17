package com.example.myapplication.tab4;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class descriptionActivity extends AppCompatActivity {
    TextView Title, Time,Day, Desc;
    Button delete;
    int id;
    lectures_db myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_item);
        Title = findViewById(R.id.textView6);
        Desc = findViewById(R.id.textView11);
        Day=findViewById(R.id.textView12);
        Time = findViewById(R.id.textView7);
        delete = findViewById(R.id.delete);
        myDB = new lectures_db(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            Title.setText(extras.getString("Title"));
            Desc.setText(extras.getString("Desc"));
            Day.setText(extras.getString("day"));
            Time.setText(extras.getString("Time"));
            //The key argument here must match that used in the other activity
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.delete(id);
                onBackPressed();
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
}