package com.example.justbreathe.atti.Activity.Object;

public class RecAC_list {
    private String url;
    private String title;
    private String location;
    private String day;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RecAC_list(String url, String title, String location, String day) {
        this.url = url;
        this.title = title;
        this.location = location;
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
