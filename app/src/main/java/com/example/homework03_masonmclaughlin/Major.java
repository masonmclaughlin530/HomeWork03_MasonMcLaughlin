package com.example.homework03_masonmclaughlin;

public class Major
{
    private int majorId;
    private String majorName;
    private String majorPrefix;


    public Major()
    {

    }

    //not sure if this is needed
    public Major(int m, String mn, String mp)
    {
        majorId = m;
        majorName = mn;
        majorPrefix = mp;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorPrefix() {
        return majorPrefix;
    }

    public void setMajorPrefix(String majorPrefix) {
        this.majorPrefix = majorPrefix;
    }
}
