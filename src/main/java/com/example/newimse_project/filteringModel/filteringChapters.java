package com.example.newimse_project.filteringModel;

public class filteringChapters {


    private int chapterNo;
    private String title;

    public filteringChapters(String title,  int chapterNo) {
        this.chapterNo = chapterNo;
        this.title = title;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
