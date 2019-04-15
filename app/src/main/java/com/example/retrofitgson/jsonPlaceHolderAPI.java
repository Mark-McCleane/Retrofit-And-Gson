package com.example.retrofitgson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface jsonPlaceHolderAPI {
    @GET("posts")
    Call<List<Post>> getPosts();


}
