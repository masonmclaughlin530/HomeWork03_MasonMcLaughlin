package com.example.homework03_masonmclaughlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentSearch extends AppCompatActivity
{

    EditText et_j_name;
    EditText et_j_age;
    EditText et_j_gpaMin;
    EditText et_j_gpaMax;

    Spinner sp_j_majors;
    Spinner sp_j_type;

    Button btn_j_back;
    Button btn_j_search;

    StudentCustomAdapter adapter;

    Intent intent_j_mainActivity;

    StudentDatabase db;

    ListView lv_j_listOfStudents;

    ArrayAdapter<String> spAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_search);

        et_j_name           = findViewById(R.id.et_v_studentSearch_name);
        et_j_age            = findViewById(R.id.et_v_studentSearch_age);
        et_j_gpaMax         = findViewById(R.id.et_v_studentSearch_gpaMax);
        et_j_gpaMin         = findViewById(R.id.et_v_studentSearch_gpaMin);

        sp_j_majors         = findViewById(R.id.sp_v_studentSearch_majors);
        sp_j_type           = findViewById(R.id.sp_v_searchStudent_type);

        btn_j_back          = findViewById(R.id.btn_v_studentSearch_back);
        btn_j_search        = findViewById(R.id.btn_v_studentSearch_search);

        lv_j_listOfStudents = findViewById(R.id.lv_v_searchStudent_listOfStudents);

        intent_j_mainActivity = new Intent(StudentSearch.this, MainActivity.class);

        db = new StudentDatabase(this);

        String[] type = {"Name", "Age", "Major", "GPA"};

        spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, type);
        sp_j_type.setAdapter(spAdapter);


        ArrayList<String> listOfMajors = db.getAllMajors();

        ArrayAdapter<String> majorNames = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfMajors);
        sp_j_majors.setAdapter(majorNames);


        backBtnClickListener();
        searchBtnClickListener();
        fillListView();




    }
    private void backBtnClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_mainActivity);
            }
        });
    }

    private void searchBtnClickListener()
    {
        btn_j_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String searchType = sp_j_type.getSelectedItem().toString();
                if(searchType.equals("Name"))
                {
                    //wanted to do full name, but could not get it working so it is just the first name
                    String name = et_j_name.getText().toString().trim();

                    ArrayList<Student> filteredList = db.nameSearch(name);

                    StudentCustomAdapter newAdapter = new StudentCustomAdapter(StudentSearch.this, filteredList);

                    lv_j_listOfStudents.setAdapter(newAdapter);
                }
                else if(searchType.equals("Age"))
                {
                    int age = Integer.parseInt(et_j_age.getText().toString());

                    ArrayList<Student> filteredList = db.ageSearch(age);

                    StudentCustomAdapter newAdapter = new StudentCustomAdapter(StudentSearch.this, filteredList);

                    lv_j_listOfStudents.setAdapter(newAdapter);
                }
                else if(searchType.equals("Major"))
                {
                    String major = sp_j_majors.getSelectedItem().toString();


                    ArrayList<Student> filteredList = db.majorSearch(major);

                    StudentCustomAdapter newAdapter = new StudentCustomAdapter(StudentSearch.this, filteredList);

                    lv_j_listOfStudents.setAdapter(newAdapter);
                }
                else if(searchType.equals("GPA"))
                {
                    double gpaMin = Double.parseDouble(et_j_gpaMin.getText().toString());
                    double gpaMax = Double.parseDouble(et_j_gpaMax.getText().toString());

                    ArrayList<Student> filteredList = db.gpaSearch(gpaMin,gpaMax);

                    StudentCustomAdapter newAdapter = new StudentCustomAdapter(StudentSearch.this, filteredList);

                    lv_j_listOfStudents.setAdapter(newAdapter);
                }
            }
        });
    }

    private void fillListView()
    {
        ArrayList<Student> listOfStudents = db.getAllStudentsInDB();

        adapter = new StudentCustomAdapter(this, listOfStudents);
        lv_j_listOfStudents.setAdapter(adapter);
    }
}