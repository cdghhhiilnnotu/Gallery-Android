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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesFragment extends Fragment implements ItemInterface, IFragment{

    GalleryActivity galleryActivity;
    private RecyclerView galleryView;
    private ArrayList<GalleryItem> item_list;
    Retrofit retrofit;

    public ImagesFragment(GalleryActivity gallery) {
        // Required empty public constructor
        galleryActivity = gallery;
        this.retrofit= new Retrofit.Builder()
                .baseUrl(GalleryConstants.server_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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

        GalleryRequest requestItem = retrofit.create(GalleryRequest.class);
        Call<ArrayList<GalleryItem>> call = requestItem.getImages();
        call.enqueue(new Callback<ArrayList<GalleryItem>>() {
            @Override
            public void onResponse(Call<ArrayList<GalleryItem>> call, Response<ArrayList<GalleryItem>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ImagesFragment.this.getContext(), "Response not Successful!", Toast.LENGTH_SHORT).show();
                    return;
                }
                item_list = response.body();
                ItemAdapter itemAdapter = new ItemAdapter((ItemInterface) ImagesFragment.this, galleryActivity, R.layout.image_item);
                itemAdapter.setContacts(item_list);
                galleryView.setAdapter(itemAdapter);
                galleryView.setLayoutManager(new GridLayoutManager(ImagesFragment.this.getContext(), 4));
            }

            @Override
            public void onFailure(Call<ArrayList<GalleryItem>> call, Throwable t) {
                Toast.makeText(ImagesFragment.this.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void item_onclick(GalleryItem item) {

        galleryActivity.onEditImage(item.item_url);
    }

    @Override
    public void OnFragmentChanged() {

    }
}