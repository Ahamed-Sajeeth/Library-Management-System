package com.tableModel;


public class MemberTableModel {

    int memberId;
    String name;
    String doa;
    String gender;
    String email;
    String phoneNo;
    String type;
    String grade;
    String address;

    public MemberTableModel(){}

    public MemberTableModel(int memberId, String name, String doa, String gender, String email, String phoneNo, String type, String grade, String address) {
        this.memberId = memberId;
        this.name = name;
        this.doa = doa;
        this.gender = gender;
        this.email = email;
        this.phoneNo = phoneNo;
        this.type = type;
        this.grade = grade;
        this.address = address;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
