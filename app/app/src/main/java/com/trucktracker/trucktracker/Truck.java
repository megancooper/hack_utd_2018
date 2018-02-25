package com.trucktracker.trucktracker;

// implementation of a truck object, used to talk with firebase
public class Truck {

    public Truck(String name, String address, String schedule, String phone, String owner){
        this.name = name;
        this.address = address;
        this.schedule = schedule;
        this.phone = phone;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private String name, address, schedule, phone, owner;

    public Truck(){
        this.name = this.address = this.schedule = this.phone = this.owner = "default";
    }


}