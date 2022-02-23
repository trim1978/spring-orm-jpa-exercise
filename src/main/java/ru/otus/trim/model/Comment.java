package ru.otus.trim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity // Указывает, что данный класс является сущностью
@Table(name = "comments") // Задает имя таблицы, на которую будет отображаться сущность@AllArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode()
//@ToString()
public class Comment {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    //@Column(name = "time", nullable = false)
    //private long time; // TODO

    // Указывает на связь между таблицами "один к одному"
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "book")
    @ToString.Exclude
    private Book book;
}
