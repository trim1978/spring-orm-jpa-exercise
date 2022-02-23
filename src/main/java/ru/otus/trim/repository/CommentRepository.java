package ru.otus.trim.repository;

import ru.otus.trim.model.Comment;
import ru.otus.trim.model.Genre;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void save (Comment comment);
    void remove (Comment comment);
    List<Comment> getAllComments(long bookId);
}
