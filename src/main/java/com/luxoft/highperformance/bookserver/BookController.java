package com.luxoft.highperformance.bookserver;

import com.luxoft.highperformance.bookserver.model.Book;
import com.luxoft.highperformance.bookserver.model.BookWord;
import com.luxoft.highperformance.bookserver.repositories.BookRepository;
import com.luxoft.highperformance.bookserver.repositories.BookWordRepository;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {

    public final int BOOKS_AMOUNT=10_000;

    private final BookRepository bookRepository;
    private final BookWordRepository bookWordRepository;

    private final Map<String, Set<Book>> library;

    public BookController(BookRepository bookRepository, BookWordRepository bookWordRepository) {
        this.bookRepository = bookRepository;
        this.bookWordRepository = bookWordRepository;
        this.library = new HashMap<>();
        bookRepository.findAll()
        .forEach(
                book -> {
                    val words = BookUtil.split(book.getTitle());
                    for (String word : words) {
                        var set = library.get(word);
                        if(set == null) {
                            set = new HashSet<>();
                            library.put(word, set);
                        }
                        set.add(book);
                    }
                }
        );
    }

    @GetMapping("find/simple/{keywordsString}")
    public List<Book> getBookByTitle(@PathVariable String keywordsString) {
        String[] keywords = keywordsString.split(" ");
        if (keywords.length == 1) {
            return bookRepository.findAllByTitleContaining(keywords[0]);
        } else if (keywords.length == 2) {
            return bookRepository.findAllByTitleContainingAndTitleContaining(
                keywords[0], keywords[1]);
        } else if (keywords.length == 3) {
            return bookRepository.findAllByTitleContainingAndTitleContainingAndTitleContaining(
                keywords[0], keywords[1], keywords[2]);
        }
        return Collections.emptyList();
    }

    @GetMapping("find/words/{keywordsString}")
    public List<Book> method2(@PathVariable String keywordsString) {
        String[] keywords = BookUtil.split(keywordsString);
        if (keywords.length == 1) {
            return bookRepository.findByWords(keywords[0]);
        } else if (keywords.length == 2) {
            return bookRepository.findByWords(keywords[0], keywords[1]);
        } else if (keywords.length == 3) {
            return bookRepository.findByWords(keywords[0], keywords[1], keywords[2]);
        }
        return Collections.emptyList();
    }


    @GetMapping("find/in-memory/{keywordsString}")
    public List<Book> inMemory(@PathVariable String keywordsString) {
        String[] keywords = BookUtil.split(keywordsString);
        Set current = library.getOrDefault(keywords[0], new HashSet<>());
        for(int i= 1; i< keywords.length; i++){
            if(current.isEmpty()) return Collections.emptyList();
            val set = library.getOrDefault(keywords[i], new HashSet<>());
            current.retainAll(set);
        }
        return new ArrayList<>(current);
    }

    @GetMapping("find/dict/{keywordsString}")
    public List<Book> dict(@PathVariable String keywordsString) {
        String[] keywords = BookUtil.split(keywordsString);
        val bookWords = bookWordRepository.findByWords(new ArrayList<>(Arrays.asList(keywords)), Long.valueOf(keywords.length));
        //todo check for dubl words in result (need filter)
        if(!bookWords.isEmpty()) {
            return bookRepository.findAllById(bookWords);
        }
        else return Collections.emptyList();
    }

    @GetMapping("/random")
    public Book getBookRandom() {
        Random random = new Random();
        int index = random.nextInt(BOOKS_AMOUNT);
        List<Book> all = bookRepository.findAll();
        return all.get(index);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

}
