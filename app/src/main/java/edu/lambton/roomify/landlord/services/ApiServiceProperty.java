package edu.lambton.roomify.landlord.services;

import java.util.List;

import edu.lambton.roomify.landlord.dto.PropertyPhotoRequest;
import edu.lambton.roomify.landlord.dto.PropertyPhotoResponse;
import edu.lambton.roomify.landlord.dto.PropertyRequest;
import edu.lambton.roomify.landlord.dto.PropertyResponse;
import edu.lambton.roomify.landlord.dto.PropertyResponseComplete;
import edu.lambton.roomify.landlord.dto.PropertyResponseInfo;
import edu.lambton.roomify.student.dto.StudentPropertyResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceProperty {

    @POST("/api/v1/properties")
    Call<PropertyResponse> createProperty(@Body PropertyRequest property);

    @GET("/api/v1/properties")
    Call<StudentPropertyResponse> getAllProperties();

    @GET("/api/v1/properties")
    Call<PropertyResponseComplete> getAllPropertiesInfo();

    @POST("api/v1/property-photos")
    Call<PropertyPhotoResponse> addPhotoToProperty(@Body PropertyPhotoRequest propertyPhotoRequest);

    @GET("/api/v1/properties/{id}")
    Call<PropertyResponseInfo> getPropertyInfo(@Path("id") String id);
}
