package com.example.myapplication.tab3;

import java.util.Calendar;

public class exams_model {
    public int id;
    public String Title,Desc;
    public Calendar cal;

    public exams_model(int id,String Title, String Desc, Calendar cal){
        this.id=id;
        this.Title=Title;
        this.Desc=Desc;
        this.cal=cal;
    }
    public String getTitle(){
        return Title;
    }
}
