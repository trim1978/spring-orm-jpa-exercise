package ru.otus.trim.service;

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

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    public BookRepository books;
    @Autowired
    public AuthorRepository authors;
    @Autowired
    public GenreRepository genres;
    @Autowired
    public CommentRepository comments;

//    @Transactional
//    @Override
//    public Book setBook(Book book) {
//        Genre genre = book.getGenre();
//        int genreId = genre.getId();
////        if (genreId == 0) {
////            genre = getGenre(genre.getName());
////        }
//        if (book.getId() == 0){
//            Author author = book.getAuthor();
//            if (author.getId() == 0){
//                author = authors.insertAuthor(author.getName());
//            }
//            return books.insertBook(book.getTitle(), author.getId(), genreId);
//        }
//        else{
//            books.updateBookById(book.getId(), genreId);
//        }
//        return book;
//    }

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
    public Genre getGenre(String name) {
        return genres.getAllGenres().stream().filter(t -> t.getName().equalsIgnoreCase(name)).findAny().orElse(null);
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

    @Override
    public Comment addComment(long bookID, String text) {
        Book book = books.getBookById(bookID);
        if (book != null) {
            Comment comment = new Comment(0l, text, book);
            comments.save(comment);
            return comment;
        }
        return null;
    }

    @Override
    public void removeComment(long commentID) {
        Comment comment = new Comment(commentID, "", null);
        comments.remove(comment);
    }

    @Override
    public List<Comment> getComments(long bookId) {
        return comments.getAllComments(bookId);
    }
}
