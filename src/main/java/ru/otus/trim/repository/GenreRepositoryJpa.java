package ru.otus.trim.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.trim.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> getGenreById(int id) {
        //TypedQuery<Genre> query = em.createQuery("select a from genres a where id="+id, Genre.class);
        //return query.getSingleResult();
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = em.createQuery("select a from Genre a", Genre.class);
        return query.getResultList();
    }
}
