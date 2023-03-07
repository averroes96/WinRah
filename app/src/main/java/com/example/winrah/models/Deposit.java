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
        return !this.getName().trim().isEmpty();
    }

    public CharSequence[] sectionNames() {
        List<Section> sections = Section.find(Section.class, "deposit = ?", String.valueOf(this.getId()));
        CharSequence[] names = new CharSequence[sections.size()];

        for (int i = 0; i < sections.size(); i++) {
            names[i] = sections.get(i).getName();
        }
        return names;
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
