package com.example.homework03_masonmclaughlin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentUpdate extends AppCompatActivity
{
        StudentDatabase db;

        EditText et_j_uname;
        EditText et_j_fname;
        EditText et_j_lname;
        EditText et_j_age;
        EditText et_j_email;
        EditText et_j_gpa;

        Spinner sp_j_majors;

        TextView tv_j_error;
        TextView tv_j_usernameError;

        Button btn_j_back;
        Button btn_j_update;

        Intent intent_j_StudentDetails;

        Boolean checkUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_update);

        db = new StudentDatabase(this);

        et_j_uname          = findViewById(R.id.et_v_studUp_username);
        et_j_fname          = findViewById(R.id.et_v_studUp_fname);
        et_j_lname          = findViewById(R.id.et_v_studUp_lname);
        et_j_age            = findViewById(R.id.et_v_studUp_age);
        et_j_email          = findViewById(R.id.et_v_studUp_email);
        et_j_gpa            = findViewById(R.id.et_v_studUp_gpa);

        sp_j_majors         = findViewById(R.id.sp_v_studUp_major);

        tv_j_error          = findViewById(R.id.tv_v_studUp_error);
        tv_j_usernameError  = findViewById(R.id.tv_v_studUp_usernameError);

        btn_j_back   = findViewById(R.id.btn_v_studUp_back);
        btn_j_update = findViewById(R.id.btn_v_studUp_update);

        tv_j_error.setVisibility(View.INVISIBLE);
        tv_j_usernameError.setVisibility(View.INVISIBLE);

        intent_j_StudentDetails = new Intent(StudentUpdate.this, StudentDetails.class);

        ArrayList<String> listOfMajors = db.getAllMajors();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfMajors);
        sp_j_majors.setAdapter(adapter);

        getStudent();
        backButtonClickListener();
        updateButtonClickListener();
        userNameKeyEventListener();



    }

    private void getStudent()
    {
        Student curStudent = SessionData.getLoggedInStudent();

        et_j_uname.setText(curStudent.getUname());
        et_j_fname.setText(curStudent.getFname());
        et_j_lname.setText(curStudent.getLname());
        et_j_email.setText(curStudent.getEmail());
        et_j_gpa.setText(String.valueOf(curStudent.getGpa()));
        et_j_age.setText(String.valueOf(curStudent.getAge()));




    }

    private void backButtonClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_StudentDetails);
            }
        });
    }

    private void updateButtonClickListener()
    {
        btn_j_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!et_j_uname.getText().toString().isEmpty() && !et_j_fname.getText().toString().isEmpty() && !et_j_lname.getText().toString().isEmpty() && !et_j_age.getText().toString().isEmpty() && !et_j_email.getText().toString().isEmpty() && !et_j_gpa.getText().toString().isEmpty() && sp_j_majors.getSelectedItem() != null)
                {
                    //had to add this extra line of code
                    double gpa = Double.parseDouble(et_j_gpa.getText().toString());
                    if(gpa >= 0.0 && gpa <= 4.0 )
                    {
                        Student newInfo = SessionData.getLoggedInStudent();

                        String username = SessionData.getLoggedInStudent().getUname();

                        newInfo.setUname(et_j_uname.getText().toString());
                        newInfo.setFname(et_j_fname.getText().toString());
                        newInfo.setLname(et_j_lname.getText().toString());
                        newInfo.setEmail(et_j_email.getText().toString());
                        newInfo.setAge(Integer.parseInt(et_j_age.getText().toString()));
                        newInfo.setMajor(sp_j_majors.getSelectedItem().toString());
                        newInfo.setGpa(Double.parseDouble(et_j_gpa.getText().toString()));

                        db.studentUpdate(newInfo, username);

                        startActivity(intent_j_StudentDetails);

                    }
                    else
                    {
                        tv_j_error.setVisibility(View.VISIBLE);
                        tv_j_error.setText("Error: Please fix gpa");
                    }
                }
                else
                {
                    //error
                    tv_j_error.setVisibility(View.VISIBLE);
                    tv_j_error.setText("Error: Please fill out all fields");
                }
            }
        });
    }

    private void userNameKeyEventListener()
    {
        et_j_uname.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                checkUsername = validName(et_j_uname.getText().toString());

                if(checkUsername)
                {
                    tv_j_usernameError.setVisibility(View.INVISIBLE);
                    btn_j_update.setEnabled(true);
                }
                else
                {
                    tv_j_usernameError.setVisibility(View.VISIBLE);
                    btn_j_update.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean validName(String u)
    {
        ArrayList<Student> listOfStudents = db.getAllStudentsInDB();

        for(int i = 0; i < listOfStudents.size(); i++)
        {
            if(listOfStudents.get(i).getUname().equals(u) && !u.equals(SessionData.getLoggedInStudent().getUname()))
            {
                return false;
            }
        }

        return true;
    }



}