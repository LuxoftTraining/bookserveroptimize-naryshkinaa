package com.luxoft.highperformance.bookserver.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(indexes = {
        @Index(columnList = "title", name = "title_ind"),
        @Index(columnList = "first,second,third,fourth,fifth", name = "first_ind")
})
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;

}
