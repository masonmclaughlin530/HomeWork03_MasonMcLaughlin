package com.example.homework03_masonmclaughlin;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter
{

    private Context context;
    private ArrayList<Student> listOfStudents;

    public SearchAdapter(Context c, ArrayList<Student> ls)
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
            view = mInflater.inflate(R.layout.activity_search_adapter, null);
        }

        TextView uname = view.findViewById(R.id.tv_v_searchAdapter_username);
        TextView fname = view.findViewById(R.id.tv_v_searchAdapter_fname);
        TextView lname = view.findViewById(R.id.tv_v_searchAdapter_lname);
        TextView age = view.findViewById(R.id.tv_v_searchAdapter_age);
        TextView gpa = view.findViewById(R.id.tv_v_searchAdapter_gpa);
        TextView major = view.findViewById(R.id.tv_v_searchAdapter_major);

        Student student = listOfStudents.get(i);

        uname.setText(student.getUname());
        fname.setText(student.getFname());
        lname.setText(student.getLname());
        age.setText(student.getAge());
        gpa.setText(String.format("%.2f", student.getGpa()));
        major.setText(student.getMajor());




        return view;
    }
}