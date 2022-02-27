package ru.otus.trim;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.trim.model.Book;
import ru.otus.trim.service.LibraryServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Репозиторий Book должен")
@ComponentScan("ru.otus.trim")
@DataJpaTest
@Import(LibraryServiceImpl.class)
class LibraryJpaTest {

    private static final long FIRST_ID = 1L;
    private static final long SECOND_ID = 2L;
    private static final long THIRD_ID = 3L;
    private static final long FOURTH_ID = 4L;
    private static final long SEVENTH_ID = 7L;
    private static final long EIGTH_ID = 8L;

    private static final int BOOKS_COUNT = 2;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private LibraryServiceImpl employeeRepository;


    @DisplayName("возвращать список всех книг")
    @Test
    void shouldFindAllBooks() {
        List<Book> employees = employeeRepository.getBooks();
        assertThat(employees).hasSize(BOOKS_COUNT);
    }

    @DisplayName("возвращать книгу по её id")
    @Test
    void shouldFindBookById() {
        Book employee = employeeRepository.getBookById(FIRST_ID);
        assertThat(employee)
                .hasFieldOrPropertyWithValue("title", "Metel");
    }

    //-------------------------------------------------------------------------------------------------------
/*
    @DisplayName("возвращать список окладов по городам")
    @Test
    void shouldCalcAvgBookSalaryByCities() {
        List<CitySalary> avgSalaryByCities = employeeRepository.calcAvgSalaryByCities();
        assertThat(avgSalaryByCities)
                .containsExactlyInAnyOrder(SARATOV_SALARY, MOSCOW_SALARY, OMSK_SALARY);
    }

    @DisplayName("возвращать сортированный список окладов по городам")
    @Test
    void shouldCalcAvgBookSalaryByCitiesSorted() {
        List<CitySalary> avgSalaryByCities = employeeRepository.calcAvgSalaryByCitiesSorted();
        assertThat(avgSalaryByCities)
                .containsExactly(SARATOV_SALARY, OMSK_SALARY, MOSCOW_SALARY);
    }

    @DisplayName("возвращать список окладов по городам, где средний доход сотрудников более 100000")
    @Test
    void shouldCalcAvgBookSalaryByCitiesHavingValueOver100000() {
        List<CitySalary> avgSalaryByCities = employeeRepository.calcAvgSalaryByCitiesHavingValueOver100000();
        assertThat(avgSalaryByCities).containsExactly(OMSK_SALARY, MOSCOW_SALARY);
    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("возвращать список всех сотрудников работающих над заданными проектами")
    @Test
    void shouldFindBooksWithGivenProjects() {
        Book employee2 = em.find(Book.class, SECOND_ID);
        Book employee4 = em.find(Book.class, FOURTH_ID);
        List<Book> employees = employeeRepository.findBooksWithGivenProjects(PROJECT_3, PROJECT_4);
        assertThat(employees).hasSize(2).containsExactlyInAnyOrder(employee2, employee4);
    }

    @DisplayName("возвращать количество проектов по сотрудникам")
    @Test
    void shouldFindBooksProjectsCount() {
        Book employee4 = em.find(Book.class, FOURTH_ID);
        List<BookProjects> employeeProjects = employeeRepository.findBooksProjectsCount();
        assertThat(employeeProjects).hasSize(EMPLOYEES_COUNT)
                .contains(new BookProjects(employee4, FOURTH_PROJECTS_COUNT));
    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("возвращать список всех сотрудников имеющих одно из двух заданных имен")
    @Test
    void shouldFindBooksWithGivenFirstNames() {
        Book employee1 = em.find(Book.class, FIRST_ID);
        Book employee7 = em.find(Book.class, SEVENTH_ID);
        List<Book> employees = employeeRepository.findBooksWithGivenFirstNames("fn1", "fn7");
        assertThat(employees).hasSize(2).containsExactlyInAnyOrder(employee1, employee7);
    }

    @DisplayName("возвращать список всех сотрудников имеющих имя, совпадающее с одним из заданного списка")
    @Test
    void shouldFindBooksWithFirstNamesFromGivenList() {
        Book employee1 = em.find(Book.class, FIRST_ID);
        Book employee7 = em.find(Book.class, SEVENTH_ID);
        List<Book> employees = employeeRepository.findBooksWithFirstNamesFromGivenList(List.of("fn1", "fn7"));
        assertThat(employees).hasSize(2).containsExactlyInAnyOrder(employee1, employee7);
    }

    @DisplayName("возвращать список всех однофамильцев заданного сотрудника")
    @Test
    void shouldFindBooksNameSakes() {
        Book employee1 = em.find(Book.class, FIRST_ID);
        Book employee9 = em.persistAndFlush(new Book(NAME_SAKE_NAME_1, employee1.getLastName()));
        Book employee10 = em.persistAndFlush(new Book(NAME_SAKE_NAME_2, employee1.getLastName()));
        List<Book> nameSakes = employeeRepository.findBookNameSakes(employee1);
        assertThat(nameSakes).hasSize(2).containsExactlyInAnyOrder(employee9, employee10);
    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("возвращать список всех тезок заданного сотрудника")
    @Test
    void shouldFindBookNameSake() {
        Book employee1 = em.find(Book.class, FIRST_ID);
        Book employee9 = em.persistAndFlush(new Book(employee1.getFirstName(), NAME_SAKE_NAME_1));
        Book nameSake = employeeRepository.findBookNameSake(employee1);
        assertThat(nameSake).usingRecursiveComparison().isEqualTo(employee9);

        em.persistAndFlush(new Book(employee1.getFirstName(), NAME_SAKE_NAME_2));
        assertThatCode(() -> employeeRepository.findBookNameSake(employee1))
                .isInstanceOf(NonUniqueResultException.class);
    }

    @DisplayName("возвращать список всех сотрудников имеющих оклад меньше, чем у заданного сотрудника")
    @Test
    void shouldFindBooksWithSalaryLessThanGivenBook() {
        Book employee1 = em.find(Book.class, FIRST_ID);
        Book employee2 = em.find(Book.class, SECOND_ID);
        Book employee3 = em.find(Book.class, THIRD_ID);
        Book employee7 = em.find(Book.class, SEVENTH_ID);
        List<Book> employees = employeeRepository.findBooksWithSalaryLessThanGivenBook(employee7);
        assertThat(employees).hasSize(3).containsExactlyInAnyOrder(employee1, employee2, employee3);
    }

    @DisplayName("возвращать сотрудника, являющегося тезкой любому другому сотруднику")
    @Test
    void shouldFindBookWithNameMatchingAnyOtherBooksNames() {
        Book employee1 = em.find(Book.class, FIRST_ID);
        Book employee9 = em.persistAndFlush(new Book(employee1.getFirstName(), NAME_SAKE_NAME_1));
        List<Book> nameSakes = employeeRepository.findBookWithNameMatchingAnyOtherBooksNames();
        assertThat(nameSakes).hasSize(2).containsExactlyInAnyOrder(employee9, employee1);
    }

    @DisplayName("возвращать сотрудника имеющго оклад меньше, чем у всех")
    @Test
    void shouldFindBooksWithSalaryLessThanAllBooks() {
        Book employee3 = em.find(Book.class, THIRD_ID);
        List<Book> employees = employeeRepository.findBooksWithSalaryLessThanAllBooks();
        assertThat(employees).hasSize(1).containsOnly(employee3);

    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("изменять значение оклада сотрудника имеющего заданный оклад")
    @Test
    void shouldUpdateBooksSalary() {
        Book employee3 = em.find(Book.class, THIRD_ID);
        BigDecimal oldSalary = employee3.getSalary();
        BigDecimal newSalary = oldSalary.multiply(new BigDecimal(2));
        em.detach(employee3);
        employeeRepository.updateBooksSalary(oldSalary, newSalary);

        employee3 = em.find(Book.class, THIRD_ID);
        assertThat(employee3.getSalary()).isEqualTo(newSalary);
    }

    @DisplayName("изменять значение оклада в два раза, у сотрудника имеющего заданный оклад")
    @Test
    void shouldDoubleBooksSalary() {
        Book employee3 = em.find(Book.class, THIRD_ID);
        BigDecimal oldSalary = employee3.getSalary();
        BigDecimal newSalary = oldSalary.multiply(new BigDecimal(2));
        em.detach(employee3);
        employeeRepository.doubleBooksSalary(oldSalary);

        employee3 = em.find(Book.class, THIRD_ID);
        assertThat(employee3.getSalary()).isEqualTo(newSalary);
    }

    @DisplayName("удалять сотрудников не относящихся ни к одному отделу")
    @Test
    void shouldDeleteBooksWithoutDepartment() {
        Book employee2 = em.find(Book.class, SECOND_ID);
        Book employee8 = em.find(Book.class, EIGTH_ID);
        assertThat(employee2).isNotNull();
        assertThat(employee8).isNotNull();
        employeeRepository.deleteBooksWithoutDepartment();

        em.clear();

        employee2 = em.find(Book.class, SECOND_ID);
        employee8 = em.find(Book.class, EIGTH_ID);
        assertThat(employee2).isNull();
        assertThat(employee8).isNull();
    }
*/
}