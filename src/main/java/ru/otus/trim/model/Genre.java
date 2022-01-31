package ru.otus.trim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity // Указывает, что данный класс является сущностью
@Table(name = "genres") // Задает имя таблицы, на которую будет отображаться сущность@AllArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
