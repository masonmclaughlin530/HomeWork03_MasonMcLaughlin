package com.example.homework03_masonmclaughlin;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StudentDatabase extends SQLiteOpenHelper
{
    private static final String database_name = "Students.db";
    private static final String students_table_name = "Students";
    private static final String major_table_name = "Majors";
    public StudentDatabase(Context c)
    {
        super(c,database_name,null,0);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(" CREATE TABLE " + students_table_name + " (username varchar(50) primary key, fname varchar(50), lname varchar (50), email varchar(50), age integer, gpa float, major varchar(50))");
        db.execSQL(" CREATE TABLE " + major_table_name + " (majorId integer primary key autoincrement not null, majorPrefix varchar(10), major varChar(50) ,foreign key (major) references " + students_table_name + "(major))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL(" DROP TABLE IF EXISTS " + students_table_name + ";");
        db.execSQL(" DROP TABLE IF EXISTS " + major_table_name + ";");

        onCreate(db);
    }

    public void initAllTables()
    {
        initStudents();
        initMajors();
    }

    public String getStudentsDbName()
    {
        return students_table_name;
    }

    public String getMajorsDbName()
    {
        return major_table_name;
    }

    private void initStudents()
    {
        //create dummy data in database
        if(countDb(students_table_name) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('masonmclaughlin530', 'Mason', 'McLaughlin', 'masonmclaughlin530@gmail.com', 18, 3.7, 'Computer Science' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('CharlieG', 'Charles', 'Guiteau', 'CharlesGuiteau@gmail.com', 22, 2.0, 'Political Science' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('LeoCZ', 'Leon', 'Czolgosz', 'CZL@UB.edu', 21, 2.5, 'Business' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('Wilkie', 'John', 'Boothe', 'WilkieJ@gmail.com', 22, 2.8, 'Art' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('Harvey411', 'Lee', 'Oswald', 'grassyknoll@yahoo.com', 20, 3.0, 'History' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('Harrison09', 'William', 'Harrison', 'WillHH09@hotmail.com', 19, 3.2, 'Political Science' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('TheGeneral', 'Ulyesses', 'Grant', 'HiramUGrant@gmail.com', 18, 2.8, 'History' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('EdSnowJGL', 'Edward', 'Snowden', 'SnowE0@gmail.com', 18, 4.0, 'Computer Science' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('Pete4256', 'Pete', 'Rose', 'CharlieHustle@gmail.com', 20, 3.4, 'Business' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('GekkoG', 'Gordon', 'Gekko', 'Greed1sGood@gmail.com', 18, 3.3, 'Business' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('PabloP', 'Pablo', 'Picasso', 'Pico@gmail.com', 21, 3.8, 'Art' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('Emch', 'Ethan', 'Emch', 'emch17@Liberty.edu', 19, 2.9, 'History' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('HonestAbe', 'Abraham', 'Lincoln', 'HonestAbe@gmail.com', 20, 4.0 , 'Political Science' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('JP40', 'Jackson', 'Pollock', 'JPSplat@gmail.com', 21, 3.5, 'Art' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('JSmith01', 'John', 'Smith', 'JSmith01@gmail.com', 19, 3.2, 'History' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('JHet', 'James', 'Hetfield', 'Hetfield1981@gmail.com', 21, 3.4, 'Business' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('Dali04', 'Salvador', 'Dali', 'SalDal@gmail.com', 21, 3.9, 'Art' )");
            db.execSQL(" INSERT INTO " + students_table_name + " (username,fname,lname,email, age, gpa, major) VALUES ('Julian09', 'Juilan', 'Assange', 'JulianAssange@wikileaks.com', 19, 3.1, 'Computer Science' )");

        db.close();
        }
    }

    private void initMajors()
    {
        if(countDb(major_table_name) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + major_table_name + " (majorPrefix, major) VALUES ('comp', 'Computer Science');");
            db.execSQL(" INSERT INTO " + major_table_name + " (majorPrefix, major) VALUES ('Polt', 'Political Science');");
            db.execSQL(" INSERT INTO " + major_table_name + " (majorPrefix, major) VALUES ('Bus', 'Business');");
            db.execSQL(" INSERT INTO " + major_table_name + " (majorPrefix, major) VALUES ('Art', 'Art');");
            db.execSQL(" INSERT INTO " + major_table_name + " (majorPrefix, major) VALUES ('Hist', 'History');");

            db.close();

        }
    }



    public int countDb(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        db.close();

        return numRows;
    }


    public ArrayList<Student> getAllStudentsInDB()
    {
        ArrayList<Student> allStudents = new ArrayList<>();

        String selectString = "SELECT username, fname, lname, email, age, gpa, major FROM " + students_table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);

        while(cursor.moveToNext());
        {
            String  uname   = cursor.getString(0);
            String  fname   = cursor.getString(1);
            String  lname   = cursor.getString(2);
            String  email   = cursor.getString(3);
            int     age     = cursor.getInt(4);
            float   gpa     = cursor.getFloat(5);
            String  major   = cursor.getString(6);

            Student newStudent = new Student(uname,fname,lname,email,major,age,gpa);
            allStudents.add(newStudent);
        }
        db.close();

        return allStudents;
    }
}
