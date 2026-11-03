package org.lab5.client;

public class Client{
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private boolean active;

    public Client(){}

    public Client(String name, String surname, String email, String phone){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Client: \nid: " + getId() + "\nname: " + getName() + "\nsurname: " + getSurname() + "\nemail: " + getEmail() + "\nphone: " + getPhone() + "\nactive: " + isActive();
    }
}
