package ru.otus.trim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import ru.otus.trim.model.Author;
import ru.otus.trim.service.LibraryService;
import ru.otus.trim.service.LibraryServiceImpl;

@SpringBootApplication
@ComponentScan("ru.otus.trim")
@Profile("demo")
public class MainDemo {
    @Autowired
    LibraryServiceImpl service;
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainDemo.class);//new AnnotationConfigApplicationContext(MainDemo.class);
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainDemo.class);

        System.out.println("\n\n-------------------------------------------\n\n");
        //context.register(LibraryServiceImpl.class);
        //context.refresh();
        LibraryService service = context.getBean(LibraryServiceImpl.class);

        //System.out.println("All count " + ervice.library.getAuthorsCount());
        //service.
        Author get = service.getAuthor("Lermontov");

        //System.out.println("All count " + ervice.library.getAuthorsCount());

        Author author = service.getAuthor(get.getName());

        System.out.println("Author id: " + author.getId() + " name: " + author.getName());
	}

}
