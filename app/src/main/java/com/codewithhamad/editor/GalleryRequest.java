package com.codewithhamad.editor;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GalleryRequest {

    @GET("/images/{id_image}")
    void getItem(@Path("id_image") String id_image);

    @GET("/images")
    Call<ArrayList<GalleryItem>> getImages();

}
