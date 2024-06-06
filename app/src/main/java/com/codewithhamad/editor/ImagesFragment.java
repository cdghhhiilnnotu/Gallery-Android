package com.codewithhamad.editor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.codewithhamad.editor.databinding.FragmentImagesBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesFragment extends Fragment{

    GalleryActivity galleryActivity;
    private RecyclerView galleryView;

    public ImagesFragment() {
        // Required empty public constructor
    }

    public ImagesFragment(GalleryActivity gallery) {
        // Required empty public constructor
        galleryActivity = gallery;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_images, container, false);

        galleryView = view.findViewById(R.id.gallery_images_view);
        ArrayList<GalleryItem> items = new ArrayList<>();
        items.add(new GalleryItem());
        items.add(new GalleryItem());
        items.add(new GalleryItem());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://thanhduong123.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GalleryRequest requestItem = retrofit.create(GalleryRequest.class);
        Call<ArrayList<GalleryItem>> call = requestItem.getImages();
        call.enqueue(new Callback<ArrayList<GalleryItem>>() {
            @Override
            public void onResponse(Call<ArrayList<GalleryItem>> call, Response<ArrayList<GalleryItem>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ImagesFragment.this.getContext(), "Response not Successful!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<GalleryItem> item_list = response.body();
                ItemAdapter imageAdapter = new ItemAdapter(ImagesFragment.this.getContext(), galleryActivity);
                imageAdapter.setContacts(item_list);
                galleryView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<GalleryItem>> call, Throwable t) {
                Toast.makeText(ImagesFragment.this.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        ItemAdapter adapter = new ItemAdapter(this.getContext(), galleryActivity);
        adapter.setContacts(items);
        galleryView.setAdapter(adapter);
        galleryView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        return view;

    }

}