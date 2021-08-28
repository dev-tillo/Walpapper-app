package uz.projects.wallpaper.models;

import java.io.Serializable;

public class ImageData implements Serializable {
    private String imageUrl;
    private String website;
    private String author;
    private String download;
    private String size;
    private boolean liked = false;

    public ImageData(String imageUrl, String website, String author, String download, String size, boolean liked) {
        this.imageUrl = imageUrl;
        this.website = website;
        this.author = author;
        this.download = download;
        this.size = size;
        this.liked = liked;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public ImageData(String imageUrl, String website, String author, String download, String size) {
        this.imageUrl = imageUrl;
        this.website = website;
        this.author = author;
        this.download = download;
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
