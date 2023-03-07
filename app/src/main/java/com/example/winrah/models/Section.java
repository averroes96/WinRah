package com.example.winrah.models;


public class Section extends Model {
    private String name;

    private Deposit deposit;

    public Section() {

    }


    public Section(String name, Deposit deposit) {
        this.name = name;
        this.deposit = deposit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public boolean validate() {
        return !this.getName().trim().isEmpty();
    }
}
