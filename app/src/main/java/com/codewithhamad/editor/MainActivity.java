package com.codewithhamad.editor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.codewithhamad.editor.databinding.ActivityMainBinding;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int IMAGE_REQUEST_CODE= 45;
    int CAMERA_REQUEST_CODE= 14;
    int EDITED_IMAGE_RESULT_CODE= 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });
    }

//    public void edittingPhoto(String url_image){
//        Uri uri= Uri.parse("https://scontent.fhan14-3.fna.fbcdn.net/v/t39.30808-1/415960787_3513982028841506_2559140225309382177_n.jpg?stp=dst-jpg_p200x200&_nc_cat=111&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeG7gO88_Ok3InDuNNxoBO1CBa_Nsw6kcjkFr82zDqRyORBEEsHYVs4SZAW1-aqFw3ZyhIzF951RL87vufaQBbG3&_nc_ohc=RzX3ksapCJcQ7kNvgHKXz6o&_nc_ht=scontent.fhan14-3.fna&oh=00_AYAIayzcsXvClnWvJGEctLzILAcpqfFrpQhPOUnbwPh7Vg&oe=666591E9");
//
//        // navigating to edit activity after capturing image from camera
//        Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
//        dsPhotoEditorIntent.setData(uri);
//
//        // directory for edited images
//        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "picaso");
//
//        int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION};
//        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
//        startActivityForResult(dsPhotoEditorIntent, EDITED_IMAGE_RESULT_CODE);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST_CODE){
            if(data.getData() != null){
                Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
                dsPhotoEditorIntent.setData(data.getData());

                // directory for edited images
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "picaso");

                int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                startActivityForResult(dsPhotoEditorIntent, EDITED_IMAGE_RESULT_CODE);
            }
        }

        if(requestCode == EDITED_IMAGE_RESULT_CODE){
            if(data.getData()!=null) {
                Toast.makeText(this, "Image saved to gallery.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.setData(data.getData());
                startActivity(intent);
            }
        }

        if(requestCode == CAMERA_REQUEST_CODE){
            Uri uri= Uri.parse("https://scontent.fhan14-3.fna.fbcdn.net/v/t39.30808-1/415960787_3513982028841506_2559140225309382177_n.jpg?stp=dst-jpg_p200x200&_nc_cat=111&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeG7gO88_Ok3InDuNNxoBO1CBa_Nsw6kcjkFr82zDqRyORBEEsHYVs4SZAW1-aqFw3ZyhIzF951RL87vufaQBbG3&_nc_ohc=RzX3ksapCJcQ7kNvgHKXz6o&_nc_ht=scontent.fhan14-3.fna&oh=00_AYAIayzcsXvClnWvJGEctLzILAcpqfFrpQhPOUnbwPh7Vg&oe=666591E9");

            // navigating to edit activity after capturing image from camera
            Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
            dsPhotoEditorIntent.setData(uri);

            // directory for edited images
            dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "picaso");

            int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION};
            dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
            startActivityForResult(dsPhotoEditorIntent, EDITED_IMAGE_RESULT_CODE);
        }
    }

    public Uri getImageUri(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String path= MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", "Desc");
        return Uri.parse(path);
    }
}