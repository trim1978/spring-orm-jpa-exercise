package ru.otus.trim.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.trim.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book getBookById(long id) {
        TypedQuery<Book> query = em.createQuery("select a from Book a where id="+id, Book.class);
        //query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("select a from Book a", Book.class);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void deleteBookById(Book book) {
        em.remove(book);
    }

}
