package com.example.myapplication.tab4;

import java.util.Calendar;

public class lectures_model {
    public int id;
    public String Title,Desc;
    public Calendar cal;

    public lectures_model(int id,String Title, String Desc, Calendar cal){
        this.id=id;
        this.Title=Title;
        this.Desc=Desc;
        this.cal=cal;
    }
    public String getTitle(){
        return Title;
    }
}
