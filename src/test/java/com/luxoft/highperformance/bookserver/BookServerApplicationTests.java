package com.luxoft.highperformance.bookserver;

import com.luxoft.highperformance.bookserver.model.Book;
import com.luxoft.highperformance.bookserver.repositories.BookRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
@Commit
class BookServerApplicationTests {

    public final int BOOKS_AMOUNT = 100_000;

    @Autowired
    BookRepository bookRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void removeAllBooks() {
        bookRepository.deleteAll();
    }

    @Test
    @Transactional
    public void addBooks() {
        Random random = new Random();
        for (int i=0; i<BOOKS_AMOUNT; i++) {
            String title = "Book"+random.nextInt(BOOKS_AMOUNT);
            String authorName = "AuthorName"+random.nextInt(BOOKS_AMOUNT)+" ";
            String authorSurname = "AuthorSurname"+random.nextInt(BOOKS_AMOUNT);
            Book book = new Book();
            val total = title+" by "+authorName+authorSurname;
            book.setTitle(total);
            val words = BookUtil.split(total);
            if(words.length >= 1) book.setFirst(words[0]);
            if(words.length >= 2) book.setSecond(words[1]);
            if(words.length >= 3) book.setThird(words[2]);
            if(words.length >= 4) book.setFourth(words[3]);
            if(words.length >= 5) book.setFifth(words[4]);
            bookRepository.save(book);
        }
    }

    @Test
    public void showRandomBooks() {
        Random random = new Random();
        List<Book> all = bookRepository.findAll();
        System.out.println("Found "+all.size()+" books");
        for (int i=0;i<10;i++) {
            int index = random.nextInt(BOOKS_AMOUNT);
            System.out.println(all.get(index));
        }
    }

}
