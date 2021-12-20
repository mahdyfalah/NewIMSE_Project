package com.example.newimse_project.Model;

import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserID;
    private String pulisher_name;
    private String email;


    @OneToMany(mappedBy = "publisher")
    private List<Book_Publisher> bookPublishers = new ArrayList<>();


    public Publisher()
    {}

    public Publisher(String pulisher_name, String email) {
        this.pulisher_name = pulisher_name;
        this.email = email;

    }

    public String getPulisher_name() {
        return pulisher_name;
    }

    public void setPulisher_name(String pulisher_name) {
        this.pulisher_name = pulisher_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
