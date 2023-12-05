package edu.lambton.utils;

public enum UserType {
    LANDLORD("Landlord"), STUDENT("Student");

    UserType(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return this.name;
    }
}
