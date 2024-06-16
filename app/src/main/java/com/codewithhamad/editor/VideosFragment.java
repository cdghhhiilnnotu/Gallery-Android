package com.codewithhamad.editor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideosFragment extends Fragment implements ItemInterface, IFragment{

    GalleryActivity galleryActivity;
    private RecyclerView galleryView;
    private ArrayList<GalleryItem> item_list;

    public VideosFragment() {
        // Required empty public constructor
    }

    public VideosFragment(GalleryActivity gallery) {
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

        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        galleryView = view.findViewById(R.id.gallery_videos_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://thanhduong123.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GalleryRequest requestItem = retrofit.create(GalleryRequest.class);
        Call<ArrayList<GalleryItem>> call = requestItem.getVideos();
        call.enqueue(new Callback<ArrayList<GalleryItem>>() {
            @Override
            public void onResponse(Call<ArrayList<GalleryItem>> call, Response<ArrayList<GalleryItem>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(VideosFragment.this.getContext(), "Response not Successful!", Toast.LENGTH_SHORT).show();
                    return;
                }
                item_list = response.body();
                ItemAdapter itemAdapter = new ItemAdapter(VideosFragment.this, galleryActivity, R.layout.video_item);
                itemAdapter.setContacts(item_list);
                galleryView.setAdapter(itemAdapter);
                galleryView.setLayoutManager(new GridLayoutManager(VideosFragment.this.getContext(), 1));
            }

            @Override
            public void onFailure(Call<ArrayList<GalleryItem>> call, Throwable t) {
                Toast.makeText(VideosFragment.this.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void item_onclick(GalleryItem item) {
        galleryActivity.loadFragment(new VPlayFragment(galleryActivity, item), false);
    }

    @Override
    public void OnFragmentChanged() {

    }
}