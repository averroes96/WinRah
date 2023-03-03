package com.example.winrah.models;


public class Deposit extends Model {
    private String name;

    public Deposit(String name) {
        this.name = name;
    }

    public Deposit(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "name='" + name + '\'' +
                '}';
    }
}
