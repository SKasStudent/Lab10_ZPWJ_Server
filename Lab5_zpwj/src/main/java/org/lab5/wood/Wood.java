package org.lab5.wood;

public class Wood{
    private int id;
    private String species;
    private double price;
    private int quantity;
    private boolean available;

    public Wood(){}

    public Wood(String species, double price, int quantity){
        this.species = species;
        this.price = price;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isAvailable() {
        return available;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }



    @Override
    public String toString() {
        return "Wood: \nid: " + getId() + "\nspecies: " + getSpecies() + "\nprice: " + getPrice() + "\nquantity: " + getQuantity() + "\navailable: " + isAvailable();
    }
}