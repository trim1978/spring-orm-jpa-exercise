package ru.otus.trim.repository;

import ru.otus.trim.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();

    Book save (Book book);
    void deleteBookById(Book book);
//
//    void updateBookById(long id, int genre);
//
//    Book insertBook(String title, int authorId, int genreId);
//
    Book getBookById(long id);
}
