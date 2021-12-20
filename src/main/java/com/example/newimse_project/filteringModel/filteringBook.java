package com.example.newimse_project.filteringModel;

import java.util.Date;
import java.util.List;

public class filteringBook {

    Long Id;

    int chaptersNumber;

    String Name,Link;

    List<filteringPublisher> publisherName;

    List<filteringCategory> categories;

    List<Date> publishDate;


    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public List<filteringCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<filteringCategory> categories) {
        this.categories = categories;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<filteringPublisher> getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(List<filteringPublisher> publisherName) {
        this.publisherName = publisherName;
    }

    public List<Date> getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(List<Date> publishDate) {
        this.publishDate = publishDate;
    }


    public int getChaptersNumber() {
        return chaptersNumber;
    }

    public void setChaptersNumber(int chaptersNumber) {
        this.chaptersNumber = chaptersNumber;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
