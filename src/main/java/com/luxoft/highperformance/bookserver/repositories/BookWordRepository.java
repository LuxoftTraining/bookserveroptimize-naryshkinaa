package com.luxoft.highperformance.bookserver.repositories;


import com.luxoft.highperformance.bookserver.model.Book;
import com.luxoft.highperformance.bookserver.model.BookWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookWordRepository extends JpaRepository<BookWord, Integer> {

    @Query("SELECT b.bookId \n" +
            "    FROM BookWord b\n" +
            "    WHERE b.word in (:words) " +
            "    GROUP BY b.bookId\n" +
            "    HAVING COUNT(b) >= :size")
    List<Integer> findByWords(List<String> words, Long size);

}
