package com.luxoft.highperformance.bookserver.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(indexes = {
        @Index(columnList = "word", name = "word_ind")
})
public class BookWord {
    @Id
    @GeneratedValue
    private Integer id;
    private String word;
    private Integer bookId;

}
