package com.naldonatanael.project_uts.api;

import com.naldonatanael.project_uts.response.BookingResponse;
import com.naldonatanael.project_uts.response.LayananResponse;
import com.naldonatanael.project_uts.response.ObjectLayananResponse;
import com.naldonatanael.project_uts.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //Api User
    @GET("user")
    Call<UserResponse> getAllUser(@Query("data") String data);

    @GET("user/{id}")
    Call<UserResponse> getUserById(@Path("id") String id,
                                   @Query("data") String data);

    @POST("user")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("name") String nama,
                                  @Field("alamat") String alamat,
                                  @Field("noTelp") String noTelp);

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> loginRequest(@Field("nim") String nim,
                                    @Field("password") String password);

    @PUT("user/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateUser(@Path("id") String id,
                                  @Query("data") String data,
                                  @Field("email") String email,
                                  @Field("password") String password,
                                  @Field("nama") String nama,
                                  @Field("alamat") String alamat,
                                  @Field("noTelp") String noTelp,
                                  @Field("image") String image);

    @POST("user/delete/{id}")
    Call<UserResponse> deleteUser(@Path("id") String id);

    //Api Booking
    @POST("user")
    @FormUrlEncoded
    Call<BookingResponse> createBooking(@Field("userID") String userID,
                                        @Field("nama") String nama,
                                        @Field("noTelp") String noTelp,
                                        @Field("jenisLayanan") String jenisLayanan,
                                        @Field("tglBook") String tglBook);


    //Api Layanan
    @GET("layanan")
    Call<LayananResponse> getAllLayanan(@Query("data")String data);

    @GET("layanan/{id}")
    Call<ObjectLayananResponse> getLayananById(@Path("id")String id,
                                             @Query("data")String data);

    @POST("layanan")
    @FormUrlEncoded
    Call<ObjectLayananResponse> createLayanan(@Field("namaLayanan")String namaLayanan, @Field("rambut")String rambut,
                                              @Field("berat")String berat,@Field("durasi")String durasi,
                                              @Field("tarif")String tarif,@Field("imageURL")String image);

    @PUT("layanan/{id}")
    @FormUrlEncoded
    Call<ObjectLayananResponse> updateLayanan(@Path("id")String id,
                                            @Field("namaLayanan")String namaLayanan, @Field("rambut")String rambut,
                                            @Field("berat")String berat,@Field("durasi")String durasi,
                                            @Field("tarif")String tarif,@Field("imageURL")String image);

    @DELETE("layanan/{id}")
    Call<ObjectLayananResponse> deleteLayanan(@Path("id")String id);
}
