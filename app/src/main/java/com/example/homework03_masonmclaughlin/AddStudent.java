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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity
{

    TextView tv_j_errorMsg;
    TextView tv_j_usernameError;

    EditText et_j_username;
    EditText et_j_fname;
    EditText et_j_lname;
    EditText et_j_email;
    EditText et_j_age;
    EditText et_j_gpa;

    Spinner sp_j_majors;

    Button btn_j_back;
    Button btn_j_addStudent;
    Button btn_j_addMajor;

    Intent intent_j_mainActivity;
    Intent intent_j_addMajor;

    StudentDatabase db;

    boolean checkUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        tv_j_errorMsg       = findViewById(R.id.tv_v_addStud_errorMsg);
        tv_j_usernameError  = findViewById(R.id.tv_v_addStud_usernameError);

        et_j_username       = findViewById(R.id.et_v_addStud_username);
        et_j_fname          = findViewById(R.id.et_v_addStud_firstname);
        et_j_lname          = findViewById(R.id.et_v_addStud_lastname);
        et_j_email          = findViewById(R.id.et_v_addStud_email);
        et_j_age            = findViewById(R.id.et_v_addStud_age);
        et_j_gpa            = findViewById(R.id.et_v_addStud_gpa);

        sp_j_majors         = findViewById(R.id.sp_v_addStud_majors);

        btn_j_addMajor      = findViewById(R.id.btn_v_addStud_addMajor);
        btn_j_back          = findViewById(R.id.btn_v_addStud_back);
        btn_j_addStudent    = findViewById(R.id.btn_v_addStud_addStudent);

        intent_j_mainActivity = new Intent(AddStudent.this, MainActivity.class);
        intent_j_addMajor = new Intent(AddStudent.this, AddMajor.class);

        tv_j_usernameError.setVisibility(View.INVISIBLE);
        tv_j_errorMsg.setVisibility(View.INVISIBLE);

        db = new StudentDatabase(this);

        ArrayList<String> listOfMajors = db.getAllMajors();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfMajors);
        sp_j_majors.setAdapter(adapter);

        addMajorClickListener();
        addStudentClickListener();
        backButtonClickListener();
        userNameKeyEventListener();


    }

    private void addStudentClickListener()
    {
        btn_j_addStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!et_j_username.getText().toString().isEmpty() && !et_j_fname.getText().toString().isEmpty() && !et_j_lname.getText().toString().isEmpty() && !et_j_age.getText().toString().isEmpty() && !et_j_email.getText().toString().isEmpty() && !et_j_gpa.getText().toString().isEmpty() && sp_j_majors.getSelectedItem() != null)
                {
                    //had to add this extra line of code
                    double gpa = Double.parseDouble(et_j_gpa.getText().toString());
                    if(gpa >= 0.0 && gpa <= 4.0 )
                    {
                        Student student = new Student();
                        student.setFname(et_j_fname.getText().toString());
                        student.setUname(et_j_username.getText().toString());
                        student.setLname(et_j_lname.getText().toString());
                        student.setAge(Integer.parseInt(et_j_age.getText().toString()));
                        student.setMajor(sp_j_majors.getSelectedItem().toString());
                        student.setGpa(Double.parseDouble(et_j_gpa.getText().toString()));
                        student.setEmail(et_j_email.getText().toString());

                        db.addStudent(student);

                        startActivity(intent_j_mainActivity);

                    }
                    else
                    {
                        tv_j_errorMsg.setVisibility(View.VISIBLE);
                        tv_j_errorMsg.setText("Error: Please fix gpa");
                    }
                }
                else
                {
                    //error
                    tv_j_errorMsg.setVisibility(View.VISIBLE);
                    tv_j_errorMsg.setText("Error: Please fill out all fields");
                }
            }
        });
    }

    private void backButtonClickListener()
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
    private void addMajorClickListener()
    {
        btn_j_addMajor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_addMajor);
            }
        });
    }

    private void userNameKeyEventListener()
    {
        et_j_username.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                checkUsername = validName(et_j_username.getText().toString());

                if(checkUsername)
                {
                    tv_j_usernameError.setVisibility(View.INVISIBLE);
                    btn_j_addStudent.setEnabled(true);
                }
                else
                {
                    tv_j_usernameError.setVisibility(View.VISIBLE);
                    btn_j_addStudent.setEnabled(false);
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
            if(listOfStudents.get(i).getUname().equals(u))
            {
                return false;
            }
        }

        return true;
    }


}