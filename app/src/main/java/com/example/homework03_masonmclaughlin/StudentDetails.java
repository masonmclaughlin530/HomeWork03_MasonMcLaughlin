package com.example.homework03_masonmclaughlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDetails extends AppCompatActivity
{
    StudentDatabase db;

    TextView tv_j_username;
    TextView tv_j_fname;
    TextView tv_j_lname;
    TextView tv_j_age;
    TextView tv_j_email;
    TextView tv_j_major;
    TextView tv_j_gpa;

    Button btn_j_return;
    Button btn_j_update;

    Intent Intent_j_sd_mainActivity;
    Intent Intent_j_sd_studentUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_details);

        db = new StudentDatabase(this);

        tv_j_username             = findViewById(R.id.tv_v_sd_username);
        tv_j_fname                = findViewById(R.id.tv_v_sd_fname);
        tv_j_lname                = findViewById(R.id.tv_v_sd_lname);
        tv_j_age                  = findViewById(R.id.tv_v_sd_age);
        tv_j_email                = findViewById(R.id.tv_v_sd_email);
        tv_j_major                = findViewById(R.id.tv_v_sd_major);
        tv_j_gpa                  = findViewById(R.id.tv_v_sd_gpa);

        btn_j_return              = findViewById(R.id.btn_v_sd_back);
        btn_j_update              = findViewById(R.id.btn_v_sd_studentUpdate);

        Intent_j_sd_mainActivity  = new Intent(StudentDetails.this, MainActivity.class);
        Intent_j_sd_studentUpdate = new Intent(StudentDetails.this, StudentUpdate.class);

        studentInfo();

        //event listeners

        backBtnClickListener();
        studentUpdateBtnClickListener();

    }

    private void studentInfo()
    {
        Student curStudent = SessionData.getLoggedInStudent();

        tv_j_username.setText(curStudent.getUname());
        tv_j_fname.setText(curStudent.getFname());
        tv_j_lname.setText(curStudent.getLname());
        tv_j_age.setText(String.valueOf(curStudent.getAge()));
        tv_j_email.setText(curStudent.getEmail());
        tv_j_major.setText(curStudent.getMajor());
        tv_j_gpa.setText(String.valueOf(curStudent.getGpa()));
    }

    private void backBtnClickListener()
    {
        btn_j_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(Intent_j_sd_mainActivity);
            }
        });
    }

    private void studentUpdateBtnClickListener()
    {
        btn_j_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(Intent_j_sd_studentUpdate);
            }
        });
    }

}