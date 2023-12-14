package edu.lambton.roomify.landlord.services;

import edu.lambton.roomify.auth.landlord.dto.UserRequest;
import edu.lambton.roomify.auth.landlord.dto.UserResponse;
import edu.lambton.roomify.landlord.dto.BookPropertyRequest;
import edu.lambton.roomify.landlord.dto.BookPropertyResponse;
import edu.lambton.roomify.landlord.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService extends ApiServiceProperty {

    @POST("/api/v1/create-user")
    Call<UserResponse> createUser(@Body UserRequest userRequest);

    @GET("/api/v1/user/{uid}")
    Call<UserResponse> findUserById(@Path("uid") String userId);

    @DELETE("/api/v1/user/{uid}")
    Call<User> deleteUser(@Path("uid") String uid);

    @PUT("/api/v1/edit-user")
    Call<edu.lambton.roomify.landlord.dto.UserResponse> updateUser(@Body User user);

    @GET("/api/v1/user/{uid}")
    Call<edu.lambton.roomify.landlord.dto.UserResponse> getUserById(@Path("uid") String uid);

    @POST("/api/v1/create-user")
    Call<User> saveUser(@Body User user);


}
