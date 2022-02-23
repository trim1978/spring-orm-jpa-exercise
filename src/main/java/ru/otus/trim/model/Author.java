package ru.otus.trim.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity // Указывает, что данный класс является сущностью
@Table(name = "authors") // Задает имя таблицы, на которую будет отображаться сущность@AllArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class Author {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
