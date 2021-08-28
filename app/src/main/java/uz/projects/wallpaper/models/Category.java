package uz.projects.wallpaper.models;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private String title;
    private List<ImageData> imageDataList;

    public Category(String title, List<ImageData> imageDataList) {
        this.title = title;
        this.imageDataList = imageDataList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageData> getImageDataList() {
        return imageDataList;
    }

    public void setImageDataList(List<ImageData> imageDataList) {
        this.imageDataList = imageDataList;
    }
}
