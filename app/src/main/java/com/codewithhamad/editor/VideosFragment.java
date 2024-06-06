package com.codewithhamad.editor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class VideosFragment extends Fragment {

    GalleryActivity galleryActivity;
    private RecyclerView galleryView;

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
        ArrayList<GalleryItem> items = new ArrayList<>();
        items.add(new GalleryItem());
        items.add(new GalleryItem());
        items.add(new GalleryItem());
        items.add(new GalleryItem());
        items.add(new GalleryItem());

        ItemAdapter adapter = new ItemAdapter(this.getContext(), galleryActivity);
        adapter.setContacts(items);
        galleryView.setAdapter(adapter);
        galleryView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        return view;
    }
}