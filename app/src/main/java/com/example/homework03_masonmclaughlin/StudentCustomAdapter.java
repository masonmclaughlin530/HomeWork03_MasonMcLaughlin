package com.example.homework03_masonmclaughlin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentCustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Student> listOfStudents;

    public StudentCustomAdapter(Context c, ArrayList<Student> ls)
    {
        context = c;
        listOfStudents = ls;
    }

    @Override
    public int getCount()
    {
        return listOfStudents.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfStudents.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.activity_custom_adapter, null);
        }

        TextView uname = view.findViewById(R.id.tv_v_cc_uname);
        TextView fname = view.findViewById(R.id.tv_v_cc_fname);
        TextView lname = view.findViewById(R.id.tv_v_cc_lname);

        Student student = listOfStudents.get(i);

        uname.setText(student.getUname());
        fname.setText(student.getFname());
        lname.setText(student.getLname());

        return view;
    }
}