package com.example.winrah.models;


import com.orm.SugarRecord;

public class Product extends SugarRecord {

    private String reference, price, boxColor;
    private Section section;

    public Product(String reference, Section section) {
        this.reference = reference;
        this.section = section;
    }

    public Product(String reference, String price, String boxColor, Section section) {
        this.reference = reference;
        this.price = price;
        this.boxColor = boxColor;

        this.section = section;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBoxColor() {
        return boxColor;
    }

    public void setBoxColor(String boxColor) {
        this.boxColor = boxColor;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
