package com.example.a2pack.helperClasses;

public class UserHelperClass {


    public String fullname,email,password,phone,city,address;

    public UserHelperClass(){


    }
    public UserHelperClass(String fullname, String email, String password, String phone,String city,String address) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.address = address;
    }
}
