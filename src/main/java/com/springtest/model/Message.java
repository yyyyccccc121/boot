package com.springtest.model;

import java.util.Date;

public class Message {
    private int age;
    private Date theDate;

    public Message(Date theDate,int age) {
        this.theDate = theDate;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getTheDate() {
        return theDate;
    }

    public void setTheDate(Date theDate) {
        this.theDate = theDate;
    }
}
