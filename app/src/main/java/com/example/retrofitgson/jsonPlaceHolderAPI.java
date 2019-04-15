package com.example.retrofitgson;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface jsonPlaceHolderAPI {
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId, //int is nullable
                              @Query("_sort") String sort,
                              @Query("_order") String order

    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> params);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postID);

    @GET
    Call<List<Comment>> getComments(@Url String url);


}
