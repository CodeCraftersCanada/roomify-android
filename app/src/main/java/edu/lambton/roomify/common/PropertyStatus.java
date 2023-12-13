package edu.lambton.roomify.common;

public enum PropertyStatus {
    PENDING(1), APPROVED(2), REJECTED(3);

    PropertyStatus(int statusId) {
        this.statusId = statusId;
    }

    private final int statusId;

    public int getStatusId() {
        return this.statusId;
    }

}
