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

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.Objects;

public class VPlayFragment extends Fragment implements IFragment{

    VideoView videoView;
    SimpleExoPlayer player;
    PlayerView playerView;
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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_play, container, false);

        speedBtn = view.findViewById(R.id.video_speed_btn);
        speedBtn.setText(String.valueOf(listOfSpeeds[indexOfSpeed % 5]) + "x");

        Uri uri = Uri.parse(GalleryConstants.gallery_url + item.item_url);

        player = new SimpleExoPlayer.Builder(Objects.requireNonNull(getContext())).build();
        player.setMediaItem(MediaItem.fromUri(uri));
        player.prepare();

        playerView = view.findViewById(R.id.video_display);
        playerView.setPlayer(player);

        speedBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                indexOfSpeed++;
                float speed = listOfSpeeds[indexOfSpeed % 5];
                speedBtn.setText(String.valueOf(speed) + "x");
                PlaybackParameters params = new PlaybackParameters(speed, 1.0f);

                player.setPlaybackParameters(params);
            }
        });

        return view;
    }

    @Override
    public void OnFragmentChanged() {
        try {
//            videoView.stopPlayback();
//            Toast.makeText(getContext(), "Hello V", Toast.LENGTH_SHORT).show();
            if (player != null) {
                player.stop();
                player.release();
                player = null;
            }
        }
        catch (Exception e){

        }
    }
}