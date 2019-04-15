package com.example.retrofitgson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {
    private TextView tvResult;
    private jsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Retrofit And Gson!");

        tvResult = findViewById(R.id.textViewResult);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(com.example.retrofitgson.jsonPlaceHolderAPI.class);

        getPosts();
//        getComments();

    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderAPI.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) { //can have error 404
                    tvResult.setText("Code: " + response.code());
                    return;
                }
                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String text = "";
                    text += "ID: " + comment.getId() + "\n";
                    text += "Post ID: " + comment.getPostID() + "\n";
                    text += "Name: " + comment.getName() + "\n";
                    text += "E-mail: " + comment.getEmail() + "\n";
                    text += "Text: " + comment.getText() + "\n\n";

                    tvResult.append(text);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts(4, "id", "desc");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String txt = "";
                    txt += "ID: " + post.getId() + "\n";
                    txt += "User ID: " + post.getUserID() + "\n";
                    txt += "Title " + post.getTitle() + "\n";
                    txt += "Text " + post.getText() + "\n";

                    tvResult.append(txt);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }
}
