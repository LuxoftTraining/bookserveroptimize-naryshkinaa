package com.luxoft.highperformance.bookserver.repositories;


import com.luxoft.highperformance.bookserver.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByTitleContaining(String keyword);
    List<Book> findAllByTitleContainingAndTitleContaining(String keyword1, String keyword2);
    List<Book> findAllByTitleContainingAndTitleContainingAndTitleContaining(
        String keyword1, String keyword2, String keyword3);

    @Query("select b from Book b where b.first = :word1 OR b.second = :word1 OR b.third = :word1 OR b.fourth = :word1 OR b.fifth = :word1")
    List<Book> findByWords(String word1);

    @Query("select b from Book b where " +
            " (b.first = :word1 OR b.second = :word1 OR b.third = :word1 OR b.fourth = :word1 OR b.fifth = :word1) AND" +
            " (b.first = :word2 OR b.second = :word2 OR b.third = :word2 OR b.fourth = :word2 OR b.fifth = :word2)"
    )
    List<Book> findByWords(String word1, String word2);

    @Query("select b from Book b where " +
            " (b.first = :word1 OR b.second = :word1 OR b.third = :word1 OR b.fourth = :word1 OR b.fifth = :word1) AND" +
            " (b.first = :word2 OR b.second = :word2 OR b.third = :word2 OR b.fourth = :word2 OR b.fifth = :word2) AND" +
            " (b.first = :word3 OR b.second = :word3 OR b.third = :word3 OR b.fourth = :word3 OR b.fifth = :word3)"
    )
    List<Book> findByWords(String word1, String word2, String word3);

}
