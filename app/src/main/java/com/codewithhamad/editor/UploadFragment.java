package com.codewithhamad.editor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadFragment extends Fragment implements IFragment{

    GalleryActivity galleryActivity;
    Button select_btn;
    private ArrayList<GalleryItem> item_list;
    Retrofit retrofit;

    public UploadFragment(GalleryActivity gallery) {
        // Required empty public constructor
        galleryActivity = gallery;
        this.retrofit= new Retrofit.Builder()
                .baseUrl("https://thanhduong123.pythonanywhere.com/")
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        select_btn = view.findViewById(R.id.select_image_btn);
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, PICK_IMAGE_REQUEST);
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void OnFragmentChanged() {

    }
}