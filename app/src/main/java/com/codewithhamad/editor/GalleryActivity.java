package com.codewithhamad.editor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.codewithhamad.editor.databinding.ActivityGalleryBinding;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GalleryActivity extends AppCompatActivity {

    ActivityGalleryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFragment(new ImagesFragment(this), false);
        binding.galleryMenu.setOnNavigationItemSelectedListener( item -> {
            switch (item.getItemId()){
                case R.id.menu_images_btn:
                    loadFragment(new ImagesFragment(GalleryActivity.this), false);
                    break;
                case R.id.menu_videos_btn:
                    loadFragment(new VideosFragment(), false);
                    break;
                case R.id.menu_audios_btn:
                    loadFragment(new AudiosFragment(), false);
                    break;
            }
            return true;
        });
    }

    public void change(String url_image){
        Uri uri= Uri.parse(url_image);
        Log.e("TAG", "response 33: "+ url_image );

        Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
        dsPhotoEditorIntent.setData(uri);

        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "picaso");

        int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION};
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
        startActivityForResult(dsPhotoEditorIntent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 200){
            if(data.getData()!=null) {
                Toast.makeText(this, "Image saved to gallery.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GalleryActivity.this, GalleryActivity.class);
                intent.setData(data.getData());
                startActivity(intent);
            }
        }

    }

    private void loadFragment(Fragment fragment, boolean isAppInitialzed){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialzed){
            fragmentTransaction.add(R.id.gallery_frame, fragment);
        }
        fragmentTransaction.replace(R.id.gallery_frame, fragment);
        fragmentTransaction.commit();
    }

}