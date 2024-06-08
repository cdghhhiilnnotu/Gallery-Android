package com.codewithhamad.editor;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VPlayFragment extends Fragment implements IFragment{

    VideoView videoView;
    GalleryActivity galleryActivity;
    GalleryItem item;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_v_play, container, false);

        videoView = view.findViewById(R.id.video_display);
        Log.e("TAG", "response 33: "+ GalleryItem.base_url + item.item_url);
        Uri uri = Uri.parse(GalleryItem.base_url + item.item_url);
        videoView.setVideoURI(uri);
        videoView.start();

        MediaController medioController = new MediaController(getContext());
        videoView.setMediaController(medioController);

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