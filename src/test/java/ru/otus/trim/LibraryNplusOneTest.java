package ru.otus.trim;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.trim.model.Book;
import ru.otus.trim.service.LibraryServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы со библиотекой")
@DataJpaTest
@ComponentScan("ru.otus.trim")
@Import(LibraryServiceImpl.class)
class LibraryNplusOneTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 2;
    private static final long BOOK_ID = 1L;

    @Autowired
    private LibraryServiceImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен загружать информацию о нужном студенте по его id")
    @Test
    void shouldFindExpectedStudentById() {
        val optionalActualStudent = repositoryJpa.getBookById(BOOK_ID);
        val expectedStudent = em.find(Book.class, BOOK_ID);
        assertThat(optionalActualStudent).usingRecursiveComparison().isEqualTo(expectedStudent);
    }

    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val students = repositoryJpa.getBooks();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null)
                //.allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0)
        ;
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
    }
    @DisplayName("должен загружать список всех комментариев с полной информацией о них")
    @Test
    void shouldReturnCorrectCommentsListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val students = repositoryJpa.getComments(2);
        assertThat(students).isNotNull().hasSize(2)
                .allMatch(s -> !s.getText().equals(""))
                .allMatch(s -> s.getBook() != null)
                .allMatch(s -> s.getBook().getAuthor() != null)
                .allMatch(s -> s.getBook().getGenre() != null)
        //.allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0)
        ;
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(2);
    }
}