package ru.otus.trim;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.trim.model.Author;
import ru.otus.trim.service.LibraryService;

@SpringBootApplication
public class MainDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainDemo.class);
        LibraryService aRepository = context.getBean(LibraryService.class);

        System.out.println("\n\n-------------------------------------------\n\n");
        System.out.println("Начинаем вставку сущностей A/B/C: ");
        //context.register(LibraryConfig.class);
        //context.refresh();
        LibraryService service = context.getBean(LibraryService.class);

        //System.out.println("All count " + ervice.library.getAuthorsCount());
        //service.
        Author get = service.getAuthor("Lermontov");

        //System.out.println("All count " + ervice.library.getAuthorsCount());

        Author author = service.getAuthor(get.getName());

        System.out.println("Author id: " + author.getId() + " name: " + author.getName());

        //Console.main(args);
	}

}
