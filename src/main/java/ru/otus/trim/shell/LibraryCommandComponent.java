package ru.otus.trim.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.trim.model.Author;
import ru.otus.trim.model.Book;
import ru.otus.trim.model.Comment;
import ru.otus.trim.model.Genre;
import ru.otus.trim.service.LibraryService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommandComponent {

    private final LibraryService library;

    @ShellMethod(value = "Add author", key = {"ins_author","ia","aa"})
    public Author addAuthor(String name) {
        return library.getAuthor(name);
    }

    @ShellMethod(value = "Remove book", key = {"remove_book","rb","db"})
    public void removeBook(long bookID) {
        library.removeBookById(bookID);
        //return "book was removed " + removed;
    }

    @ShellMethod(value = "Get book", key = {"get_book", "gb"})
    public Book getBook(long bookID) {
        return library.getBookById(bookID);
    }

    @ShellMethod(value = "Get all authors", key = {"get_authors", "gaa"})
    public List<Author> getAuthors() {
        return library.getAuthors();
    }

    @ShellMethod(value = "Get all genres", key = {"get_genres","gag"})
    public List<Genre> getGenres() {
        return library.getGenres();
    }

    @ShellMethod(value = "Get all books", key = {"get_books","gab"})
    public List<Book> getBooks() {
        return library.getBooks();
    }

    @ShellMethod(value = "Get all comments", key = {"get_comments","gac"})
    public List<Comment> getComments(long bookID) {
        return library.getComments(bookID);
    }

    @ShellMethod(value = "Add comment", key = {"add_comment","ac"})
    public Comment addComment(long bookID, String text) {
        return library.addComment(bookID, text);
    }

    @ShellMethod(value = "Remove comment", key = {"remove_comment","rc"})
    public void removeComment(long commentID) {
        library.removeComment(commentID);
    }

    @ShellMethod(value = "Change comment", key = {"change_comment","cc"})
    public void changeComment(long commentID, String text) {
        library.changeComment(commentID, text);
    }

}
