package com.codewithhamad.editor;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VPlayFragment extends Fragment implements IFragment{

    VideoView videoView;
    Button speedBtn;
    GalleryActivity galleryActivity;
    GalleryItem item;

    private final float[] listOfSpeeds = {0.25f, 0.5f, 1.0f, 1.5f, 2.0f};
    int indexOfSpeed = 2;

    public VPlayFragment(GalleryActivity galleryActivity, GalleryItem item){
        this.galleryActivity = galleryActivity;
        this.item = item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_play, container, false);

        videoView = view.findViewById(R.id.video_display);
        speedBtn = view.findViewById(R.id.video_speed_btn);
        Uri uri = Uri.parse(GalleryConstants.gallery_url + item.item_url);
        videoView.setVideoURI(uri);
        videoView.start();

        MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);

        speedBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                indexOfSpeed++;
                speedBtn.setText(String.valueOf(listOfSpeeds[indexOfSpeed % 5]) + "x");
            }
        });

        return view;
    }

    @Override
    public void OnFragmentChanged() {
        try {
            videoView.stopPlayback();
//            Toast.makeText(getContext(), "Hello V", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){

        }
    }
}