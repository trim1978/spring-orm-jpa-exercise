package ru.otus.trim.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.trim.model.Comment;
import ru.otus.trim.repository.AuthorRepository;
import ru.otus.trim.repository.BookRepository;
import ru.otus.trim.repository.CommentRepository;
import ru.otus.trim.repository.GenreRepository;
import ru.otus.trim.model.Author;
import ru.otus.trim.model.Book;
import ru.otus.trim.model.Genre;

import java.util.List;

@AllArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    public final BookRepository books;
    public final AuthorRepository authors;
    public final GenreRepository genres;
    public final CommentRepository comments;

    @Transactional
    @Override
    public Book removeBookById(long bookID) {
        Book book = books.getBookById(bookID);
        if (book != null) books.deleteBookById(book);
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(long bookID) {
        return books.getBookById(bookID);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooks() {
        return books.getAllBooks();
    }

    @Transactional
    @Override
    public Author getAuthor(String name) {
        return authors.getAllAuthors().stream().filter(t -> t.getName().equalsIgnoreCase(name)).findAny().orElse(authors.save(new Author(0, name)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAuthors() {
        return authors.getAllAuthors();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getGenres() {
        return genres.getAllGenres();
    }

    @Transactional
    @Override
    public Comment addComment(long bookID, String text) {
        Book book = books.getBookById(bookID);
        if (book != null) {
            Comment comment = new Comment(0L, text, book);
            comments.save(comment);
            return comment;
        }
        return null;
    }

    @Transactional
    @Override
    public void removeComment(long commentID) {
        Comment comment = new Comment(commentID, "", null);
        comments.remove(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getComments(long bookId) {
        return comments.getAllComments(bookId);
    }
}
