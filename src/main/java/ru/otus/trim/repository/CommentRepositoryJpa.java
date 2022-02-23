package ru.otus.trim.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.trim.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void save (Comment comment){
        if (comment.getId() <= 0) {
            em.persist(comment);
            //return comment;
        } else {
            //return
                    em.merge(comment);
        }
    }
    @Override
    public void remove (Comment comment){
        em.remove(comment);
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
}
