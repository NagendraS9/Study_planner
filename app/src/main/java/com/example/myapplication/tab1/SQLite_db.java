package com.example.myapplication.tab1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class SQLite_db extends SQLiteOpenHelper {
    static final private String DB_NAME="STUDY_PLANS";
    static final private String DB_TABLE="PLANS";
    static final private int DB_Version=4;

    Context ctx;
    SQLiteDatabase my_DB;
    public SQLite_db(@Nullable Context context) {
        super(context, DB_NAME, null, DB_Version);
        ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DB_TABLE +" (_id integer primary key AUTOINCREMENT,title text,description text,day int,month int,year int,hour int, min int,amOrPm);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ DB_TABLE);
            onCreate(sqLiteDatabase);
    }

    public void insert(String title, String description, int day,int month,int year,int hour,int min,int amOrPm){
        my_DB=getWritableDatabase();
        my_DB.execSQL("insert into "+DB_TABLE+" (title,description,day,month,year,hour,min,amOrPm) values ('"+title+"','"+description+"',"+day+","+month+","+year+","+hour+","+min+","+amOrPm+");");
        Toast.makeText(ctx,"Data saved",Toast.LENGTH_SHORT).show();
        my_DB.close();
    }
    public void delete(int Id){
        my_DB=getWritableDatabase();
        my_DB.delete(DB_TABLE, "_id = ?",new String[]{Long.toString(Id)} );
        Toast.makeText(ctx,"Entry deleted",Toast.LENGTH_SHORT).show();
        my_DB.close();
    }
    public ArrayList<Object> getAll(){
        my_DB=getReadableDatabase();
        Cursor cr= my_DB.rawQuery("SELECT * from "+DB_TABLE,null);
        ArrayList<Object> arrPlans=new ArrayList<>();
        StringBuilder str=new StringBuilder();
        while(cr.moveToNext()) {
            int id=cr.getInt(0);
            String title = cr.getString(1);
            String desc = cr.getString(2);
            int day = cr.getInt(3);
            int month = cr.getInt(4);
            int year = cr.getInt(5);
            int hour = cr.getInt(6);
            int min = cr.getInt(7);
            int amOrPm=cr.getInt(8);

            Calendar cal=Calendar.getInstance();
            cal.set(year,month,day,hour,min);
//            String s3= (String) DateFormat.format("hh:mm aa",cal2);
            arrPlans.add(new Plan_model(id,title,desc,cal));
        }
        my_DB.close();
        return arrPlans;
    }
    public int getCountbyDate(LocalDate date) {
        my_DB=getReadableDatabase();
        int month_val =date.getMonthValue()-1;
        Cursor cur=my_DB.rawQuery("SELECT * from "+DB_TABLE+" where day="+date.getDayOfMonth()+" and month="+month_val+" and year="+date.getYear(),null);
        int count= cur.getCount();
        cur.close();
        return count;
    }
}
