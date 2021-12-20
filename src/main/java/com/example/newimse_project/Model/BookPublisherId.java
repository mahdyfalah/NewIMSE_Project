package com.example.newimse_project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookPublisherId implements Serializable {

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "publisher_id")
    private Long publisherId;
}
