package com.codewithhamad.editor;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private ArrayList<GalleryItem> items = new ArrayList<>();
    private ItemInterface itemInterface;
    private GalleryActivity galleryActivity;

    public ItemAdapter(ItemInterface itemInterface, GalleryActivity gallery){
        this.galleryActivity = gallery;
        this.itemInterface = itemInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        GalleryItem item = items.get(position);
        holder.name.setText(item.item_name);
        try{
            Picasso.get().load(GalleryItem.base_url + item.item_theme).into(holder.image);
        }
        catch (Exception e){
            Picasso.get().load("https://thanhduong123.pythonanywhere.com/images/i0000000115.jpg").into(holder.image);

        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemInterface.item_onclick(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setContacts(ArrayList<GalleryItem> contacts) {
        this.items = contacts;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView name;
        private CardView parent;
        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            parent = itemView.findViewById(R.id.parent);

            image = itemView.findViewById(R.id.image_item);
            name = itemView.findViewById(R.id.txt_name);
        }
    }

}