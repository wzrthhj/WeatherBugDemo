package com.weatherbug.demo.model;

public class Notice {

    private String title;
    private String description;
    private String filename;
    public Notice(String title, String description, String filename) {
        this.title = title;
        this.description = description;
        this.filename = filename;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}