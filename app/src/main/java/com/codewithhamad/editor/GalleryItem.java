package com.codewithhamad.editor;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class GalleryItem {

    static String base_url = "https://cdghhhiilnnotu.github.io/Gallery-Server/";
    @SerializedName(value="image_id", alternate={"video_id", "audio_id"})
    String item_id;
    @SerializedName(value="image_name", alternate={"video_name", "audio_name"})
    String item_name;
    @SerializedName(value="image_url", alternate={"video_url", "audio_url"})
    String item_url;
    @SerializedName(value="theme_url")
    String item_theme;
    @SerializedName(value="image_description", alternate={"video_description", "audio_description"})
    String item_desc;
    @SerializedName(value="upload_date")
    String item_upload;
    @SerializedName(value="view_count")
    int item_view;
    @SerializedName(value="download_count")
    int item_download;

    public GalleryItem(String id, String name, int view, String upload, int download, String url, String theme, String desc) {
        this.item_id = id;
        this.item_name = name;
        this.item_view = view;
        this.item_upload = upload;
        this.item_download = download;
        this.item_url = url;
        this.item_theme = theme;
        this.item_desc = desc;
    }

    public GalleryItem() {

    }

    public String Descriptions(){
        return "Lượt xem: " + this.item_view + "\n" +
                "Lượt tải xuống: " + this.item_download + "\n" +
                "Mô tả: " + this.item_desc + "\n";
    }

    public Map<String, Object> toMapImage() {
        Map<String, Object> map = new HashMap<>();
        map.put("image_id", item_id);
        map.put("image_name", item_name);
        map.put("image_url", item_url);
        map.put("theme_url", item_theme);
        map.put("image_description", item_desc);
        map.put("upload_date", item_upload);
        map.put("view_count", item_view);
        map.put("download_count", item_download);
        return map;
    }

}

