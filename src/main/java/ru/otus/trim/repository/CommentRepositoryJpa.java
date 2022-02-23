package ru.otus.trim.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.trim.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository{
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void add(Comment comment){
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }
    @Override
    public void remove (long commentID){
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", commentID);
        query.executeUpdate();
    }

    @Override
    public void update(long commentID, String text) {
        Query query = em.createQuery("update Comment c set c.text = :text where c.id = :id");
        query.setParameter("text", text);
        query.setParameter("id", commentID);
        query.executeUpdate();
    }

    @Override
    public List<Comment> getAllComments(long bookId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Comment> q = cb.createQuery(Comment.class);
        Root<Comment> c = q.from(Comment.class);
        q.select(c);
        ParameterExpression<Long> p1 = cb.parameter(Long.class);
        q.where(
                cb.equal(c.get("book"), bookId)
        );
        return em.createQuery(q).getResultList();
    }

    @Override
    public void removeComments(long bookId) {
        Query query = em.createQuery("delete from Comment c where c.book.id = :book");
        query.setParameter("book", bookId);
        query.executeUpdate();
    }
}
