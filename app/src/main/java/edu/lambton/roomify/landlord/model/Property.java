package edu.lambton.roomify.landlord.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public final class Property implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    @PrimaryKey
    @ColumnInfo(name = "PROPERTY_ID")
    private final Long propertyId;
    private final String description;
    @ColumnInfo(name = "PROPERTY_TYPE")
    private final int propertyType;
    @ColumnInfo(name = "PROPERTY_NAME")
    private final String propertyName;
    @ColumnInfo(name = "SHARED_TYPE")
    private final int sharedType;
    @ColumnInfo(name = "SHARED_NAME")
    private final String sharedName;
    @ColumnInfo(name = "GUEST_NUMBER")
    private final int guestNumber;
    @ColumnInfo(name = "BEDROOM_NUMBER")
    private final int bedroomNumber;
    @ColumnInfo(name = "BEDS_NUMBER")
    private final int bedsNumber;
    @ColumnInfo(name = "BEDROOM_LOCKED")
    private final boolean bedroomLocked;
    private final double price;
    private final String address1;
    private final String address2;
    private final String city;
    private final String province;
    private final String country;
    private final String postal_code;
    private final double latitude;
    private final double longitude;

    public Property(Long propertyId,
                    String description, int propertyType,
                    String propertyName,
                    int sharedType,
                    String sharedName,
                    int guestNumber,
                    int bedroomNumber,
                    int bedsNumber,
                    //boolean
                    boolean bedroomLocked, double price, String address1, String address2,
                    String city, String province, String country, String postal_code,
                    double latitude, double longitude
    ) {
        this.propertyId = propertyId;
        this.description = description;
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.sharedType = sharedType;
        this.sharedName = sharedName;
        this.guestNumber = guestNumber;
        this.bedroomNumber = bedroomNumber;
        this.bedsNumber = bedsNumber;
        this.bedroomLocked = bedroomLocked;
        this.price = price;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postal_code = postal_code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long propertyId() {
        return propertyId;
    }

    public String description() {
        return description;
    }

    public int propertyType() {
        return propertyType;
    }

    public String propertyName() {
        return propertyName;
    }

    public int sharedType() {
        return sharedType;
    }

    public String sharedName() {
        return sharedName;
    }

    public int guestNumber() {
        return guestNumber;
    }

    public int bedroomNumber() {
        return bedroomNumber;
    }

    public int bedsNumber() {
        return bedsNumber;
    }

    public boolean bedroomLocked() {
        return bedroomLocked;
    }

    public double price() {
        return price;
    }

    public String address1() {
        return address1;
    }

    public String address2() {
        return address2;
    }

    public String city() {
        return city;
    }

    public String province() {
        return province;
    }

    public String country() {
        return country;
    }

    public String postal_code() {
        return postal_code;
    }

    public double latitude() {
        return latitude;
    }

    public double longitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Property) obj;
        return Objects.equals(this.propertyId, that.propertyId) &&
                Objects.equals(this.description, that.description) &&
                this.propertyType == that.propertyType &&
                Objects.equals(this.propertyName, that.propertyName) &&
                this.sharedType == that.sharedType &&
                Objects.equals(this.sharedName, that.sharedName) &&
                this.guestNumber == that.guestNumber &&
                this.bedroomNumber == that.bedroomNumber &&
                this.bedsNumber == that.bedsNumber &&
                this.bedroomLocked == that.bedroomLocked &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price) &&
                Objects.equals(this.address1, that.address1) &&
                Objects.equals(this.address2, that.address2) &&
                Objects.equals(this.city, that.city) &&
                Objects.equals(this.province, that.province) &&
                Objects.equals(this.country, that.country) &&
                Objects.equals(this.postal_code, that.postal_code) &&
                Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(that.latitude) &&
                Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, description, propertyType, propertyName, sharedType, sharedName, guestNumber, bedroomNumber, bedsNumber, bedroomLocked, price, address1, address2, city, province, country, postal_code, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Property[" +
                "propertyId=" + propertyId + ", " +
                "description=" + description + ", " +
                "propertyType=" + propertyType + ", " +
                "propertyName=" + propertyName + ", " +
                "sharedType=" + sharedType + ", " +
                "sharedName=" + sharedName + ", " +
                "guestNumber=" + guestNumber + ", " +
                "bedroomNumber=" + bedroomNumber + ", " +
                "bedsNumber=" + bedsNumber + ", " +
                "bedroomLocked=" + bedroomLocked + ", " +
                "price=" + price + ", " +
                "address1=" + address1 + ", " +
                "address2=" + address2 + ", " +
                "city=" + city + ", " +
                "province=" + province + ", " +
                "country=" + country + ", " +
                "postal_code=" + postal_code + ", " +
                "latitude=" + latitude + ", " +
                "longitude=" + longitude + ']';
    }


}
