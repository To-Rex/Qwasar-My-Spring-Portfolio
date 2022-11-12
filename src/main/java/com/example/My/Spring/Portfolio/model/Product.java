package com.example.My.Spring.Portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("products")
public class Product {

    @Id
    private String id;

    private String name;

    private String surname;

    private int age;

    private String email;

    private String phone;

    private String password;

    private String address;

    private String role;

    private Boolean active;

    private Boolean locked;

    private int balance;

    private String worksCcv;
    //constructor

    public Product() {
    }

    public Product(String name, String surname, int age, String email, String phone, String password, String address, String role, Boolean active, Boolean locked, int balance, String worksCcv) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.role = role;
        this.active = active;
        this.locked = locked;
        this.balance = balance;
        this.worksCcv = worksCcv;
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getWorksCcv() {
        return worksCcv;
    }

    public void setWorksCcv(String worksCcv) {
        this.worksCcv = worksCcv;
    }

    //toString

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", email=" + email + ", phone=" + phone + ", password=" + password + ", address=" + address + '}';
    }
}