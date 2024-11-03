package com.example.homework03_masonmclaughlin;

public class Student
{
    private String uname;
    private String fname;
    private String lname;
    private String email;
    private String major;
    private int age;
    private double gpa;

    public Student()
    {

    }

    public Student(String u,String f, String l,String e,String m, int a, double g)
    {
        uname = u;
        fname = f;
        lname = l;
        email = e;
        major = m;
        age = a;
        gpa = g;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMajor()
    {
        return major;
    }

    public void setMajor(String major)
    {
        this.major = major;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public double getGpa()
    {
        return gpa;
    }

    public void setGpa(double gpa)
    {
        this.gpa = gpa;
    }
}
