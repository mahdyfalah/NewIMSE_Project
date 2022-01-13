package com.example.newimse_project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CompositChapterID implements Serializable {

    @Column(name = "chapterNO")
    private Long chapterNO;

    @Column(name = "book_id")
    private Long bookId;



}
