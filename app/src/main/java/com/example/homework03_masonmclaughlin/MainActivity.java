//================================
//Author: Mason McLaughlin
//Date: 10/8/24
//Desc: HW03


package com.example.homework03_masonmclaughlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    Button btn_j_addStudent;
    Button btn_j_searchStudent;
    ListView lv_j_students;
    StudentDatabase db;
    Intent intent_j_addStudent;
    Intent intent_j_searchStudent;
    Intent intent_j_student;

    StudentCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_j_addStudent    = findViewById(R.id.btn_v_main_addStudent);
        btn_j_searchStudent = findViewById(R.id.btn_v_main_studentSearch);
        lv_j_students       = findViewById(R.id.lv_v_students);

        intent_j_addStudent = new Intent(MainActivity.this, AddStudent.class);
        intent_j_searchStudent = new Intent(MainActivity.this, StudentSearch.class);

        db = new StudentDatabase(this);

        //create database
        db.initAllTables();

        fillListView();

        btnAddStudentClickListener();
        btnSearchStudentClickListener();

    }

    private void btnAddStudentClickListener()
    {
        btn_j_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_addStudent);
            }
        });
    }

    private void btnSearchStudentClickListener()
    {
        btn_j_searchStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_searchStudent);
            }
        });
    }

    private void fillListView()
    {
        ArrayList<Student> listOfStudents = db.getAllStudentsInDB();

        adapter = new StudentCustomAdapter(this, listOfStudents);

        lv_j_students.setAdapter(adapter);

        lvOnClickListener(listOfStudents);



    }

   private void lvOnClickListener(ArrayList<Student> ls)
   {
       lv_j_students.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
           {
                Student clickedOnStudent = ls.get(position);

                SessionData.setLoggedInStudent(clickedOnStudent);

                intent_j_student = new Intent(MainActivity.this, StudentDetails.class);
                startActivity(intent_j_student);
           }
       });

       lv_j_students.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l)
           {
                Student deleteStudent = ls.get(position);


                db.deleteStudent(deleteStudent.getUname());

                //adapter.notifyDataSetChanged();
               adapter.notifyDataSetChanged();
                //this has to be return true
               return true;
           }
       });

   }

}