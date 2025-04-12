package com.mohyaseensidd.studynitp;

public class User {
    String RollNo, Name, Email, Branch, Semester, MobNo;

    public User(String rollNo, String name, String email, String branch, String semester, String mobNo) {
        RollNo = rollNo;
        Name = name;
        Email = email;
        Branch = branch;
        Semester = semester;
        MobNo = mobNo;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getMobNo() {
        return MobNo;
    }

    public void setMobNo(String mobNo) {
        MobNo = mobNo;
    }
}
