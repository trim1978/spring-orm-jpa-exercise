package ru.otus.trim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity // Указывает, что данный класс является сущностью
@Table(name = "books") // Задает имя таблицы, на которую будет отображаться сущность@AllArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "author-genre-entity-graph",attributeNodes = {@NamedAttributeNode("author"),@NamedAttributeNode("genre")})
public class Book {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    // Указывает на связь между таблицами "один к одному"
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "author")
    @ToString.Exclude
    private Author author;

    // Указывает на связь между таблицами "один к одному"
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "genre")
    @ToString.Exclude
    private Genre genre;
}
