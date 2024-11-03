package com.example.homework03_masonmclaughlin;

public class SessionData
{

    private static Student loggedInStudent;

    public static Student getLoggedInStudent()
    {
        return loggedInStudent;
    }

    public static void setLoggedInStudent(Student s)
    {
        loggedInStudent = s;
    }


}
