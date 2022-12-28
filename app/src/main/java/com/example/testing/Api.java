package com.example.testing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.example.testing.Root;

public interface Api {
    @GET("movie/popular")
    Call<Root> getMovie(@Query("api_key") String token);

    @GET("movie/{movie_id}")
    Call<com.example.testing.model.Root> getDetail(@Path("movie_id") int id, @Query("api_key") String token);
}
