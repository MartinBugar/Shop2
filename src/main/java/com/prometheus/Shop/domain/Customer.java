package com.prometheus.Shop.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class Customer {
    @Nullable
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String surename;

    @NonNull
    private String email;

    @NonNull
    private String address;

    @Nullable
    private Integer age;

    @Nullable
    private String phoneNumber;

    public Customer(){}

    public Customer(@NonNull String name, @NonNull String surename, @NonNull String email, @NonNull String address, @Nullable Integer age, @Nullable String phoneNumber) {
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSurename() {
        return surename;
    }

    public void setSurename(@NonNull String surename) {
        this.surename = surename;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }

    public void setAge(@Nullable Integer age) {
        this.age = age;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                name.equals(customer.name) &&
                surename.equals(customer.surename) &&
                email.equals(customer.email) &&
                address.equals(customer.address) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surename, email, address, age, phoneNumber);
    }


}


