package com.example.winrah.models;


import java.util.List;

public class Deposit extends Model {

    private String name;

    public Deposit() {}

    public Deposit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean validate() {
        if (this.getName().trim().isEmpty()) {
            return false;
        }

        return true;
    }

    public static CharSequence[] getAllDepositNames() {
        List<Deposit> allDeposits = Deposit.listAll(Deposit.class);
        CharSequence[] depositNames = new CharSequence[allDeposits.size()];
        for (int i = 0; i < allDeposits.size(); i++) {
            depositNames[i] = allDeposits.get(i).getName();
        }
        return depositNames;
    }

}
