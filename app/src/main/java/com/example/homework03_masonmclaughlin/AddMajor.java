package com.example.homework03_masonmclaughlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddMajor extends AppCompatActivity
{

    EditText et_j_newMajor;
    EditText et_j_majorPrefix;

    Button btn_j_back;
    Button btn_j_addMajor;

    TextView tv_j_errorMsg;

    StudentDatabase db;

    Intent intent_j_addStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_major);

        et_j_newMajor      = findViewById(R.id.et_v_addMajor_major);
        et_j_majorPrefix   = findViewById(R.id.et_v_addMajor_majorPrefix);

        btn_j_addMajor     = findViewById(R.id.btn_v_addMajor_addMajor);
        btn_j_back         = findViewById(R.id.btn_v_addMajor_back);

        tv_j_errorMsg      = findViewById(R.id.tv_v_addMajor_errorMsg);
        tv_j_errorMsg.setVisibility(View.INVISIBLE);

        intent_j_addStudent = new Intent(AddMajor.this, AddStudent.class);

        db = new StudentDatabase(this);

        backBtnClickListener();
        addMajorBtnClickListener();

    }

    private void backBtnClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_addStudent);
            }
        });
    }

    private void addMajorBtnClickListener()
    {
        btn_j_addMajor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!et_j_newMajor.getText().toString().isEmpty() && !et_j_majorPrefix.getText().toString().isEmpty())
                {
                    if(majorCheck(et_j_newMajor.getText().toString()))
                    {
                        if (et_j_majorPrefix.getText().toString().length() == 3 || et_j_majorPrefix.getText().toString().length() == 4)
                        {
                            tv_j_errorMsg.setVisibility(View.INVISIBLE);
                            Major newMajor = new Major();

                            newMajor.setMajorName(et_j_newMajor.getText().toString());
                            newMajor.setMajorPrefix(et_j_majorPrefix.getText().toString());

                            db.addMajor(newMajor);

                            startActivity(intent_j_addStudent);

                        }
                        else
                        {
                            tv_j_errorMsg.setVisibility(View.VISIBLE);
                            tv_j_errorMsg.setText("Please Make to prefix three or four charcters");
                        }
                    }
                    else
                    {
                        tv_j_errorMsg.setVisibility(View.VISIBLE);
                        tv_j_errorMsg.setText("This major already exists");
                    }
                }
                else
                {
                    tv_j_errorMsg.setVisibility(View.VISIBLE);
                    tv_j_errorMsg.setText("Please Fill Out Both Fields");
                }
            }
        });
    }

    private Boolean majorCheck(String major)
    {
        ArrayList<String> listOfMajors = db.getAllMajors();

        for (int i = 0; i < listOfMajors.size(); i++)
        {
            if(listOfMajors.get(i).equals(major))
            {
                return false;
            }
        }

        return true;
    }
}