package edu.lambton.roomify.common;

public enum UserType {
    ADMIN(1), COLLEGE(2), LANDLORD(3), STUDENT(4);

    UserType(int value) {
        this.value = value;
    }
    private final int value;

    public int getValue() {
        return value;
    }
}
