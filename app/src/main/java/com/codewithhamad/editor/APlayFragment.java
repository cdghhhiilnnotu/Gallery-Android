package com.codewithhamad.editor;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import a.a.b.b.e;

public class APlayFragment extends Fragment implements IFragment{

    GalleryActivity galleryActivity;
    GalleryItem item;
    TextView player_position, player_duration;
    SeekBar seek_bar;
    ImageView btn_rew, btn_play, btn_pause, btn_ff;

    MediaPlayer media_player;
    Handler handler = new Handler();
    Runnable runnable;


    public APlayFragment(GalleryActivity galleryActivity, GalleryItem item) {
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
        View view = inflater.inflate(R.layout.fragment_a_play, container, false);

        player_position = view.findViewById(R.id.player_position);
        player_duration = view.findViewById(R.id.player_duration);
        seek_bar = view.findViewById(R.id.seek_bar);
        btn_rew = view.findViewById(R.id.btn_rew);
        btn_play = view.findViewById(R.id.btn_play);
        btn_pause = view.findViewById(R.id.btn_pause);
        btn_ff = view.findViewById(R.id.btn_ff);

        media_player = MediaPlayer.create(view.getContext(),
                Uri.parse(GalleryConstants.gallery_url + item.item_url));

        runnable = new Runnable() {
            @Override
            public void run() {
                seek_bar.setProgress(media_player.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        };

        int duration = media_player.getDuration();
        String sDuration = convertFormat(duration);

        player_duration.setText(sDuration);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_play.setVisibility(View.GONE);
                btn_pause.setVisibility(View.VISIBLE);
                media_player.start();
                seek_bar.setMax(media_player.getDuration());
                handler.postDelayed(runnable, 0);
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_pause.setVisibility(View.GONE);
                btn_play.setVisibility(View.VISIBLE);
                media_player.pause();
                handler.removeCallbacks(runnable);
            }
        });

        btn_ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_position = media_player.getCurrentPosition();
                int duration = media_player.getDuration();
                if(media_player.isPlaying() && duration != current_position){
                    current_position = current_position + 5000;
                    player_position.setText(convertFormat(current_position));
                    media_player.seekTo(current_position);
                }
            }
        });

        btn_rew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_position = media_player.getCurrentPosition();
                int duration = media_player.getDuration();
                if(media_player.isPlaying() && duration > 5000){
                    current_position = current_position - 5000;
                    player_position.setText(convertFormat(current_position));
                    media_player.seekTo(current_position);
                }
            }
        });

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    media_player.seekTo(i);
                }
                player_position.setText(convertFormat(media_player.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        media_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btn_pause.setVisibility(View.GONE);
                btn_play.setVisibility(View.VISIBLE);
                media_player.seekTo(0);
            }
        });

        return view;
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration){
        return String.format("%02d:%02d"
                , TimeUnit.MILLISECONDS.toMinutes(duration)
                , TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))

        );
    }

    @Override
    public void OnFragmentChanged() {
        try {
            media_player.stop();
        }
        catch (Exception e){

        }
    }
}