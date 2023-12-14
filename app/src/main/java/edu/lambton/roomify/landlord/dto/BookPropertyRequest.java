package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.SerializedName;

public class BookPropertyRequest {

    @SerializedName("property_id")
    private String propertyId;

    @SerializedName("booked_by")
    private String bookedBy;

    @SerializedName("amount")
    private String amount;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("end_date")
    private String endDate;

    // Constructors, getters, and setters

    public BookPropertyRequest(String propertyId, String bookedBy, String amount, String startDate, String endDate) {
        this.propertyId = propertyId;
        this.bookedBy = bookedBy;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
