package com.example.Spring5.Web.App.bootstrap;

import com.example.Spring5.Web.App.domain.Author;
import com.example.Spring5.Web.App.domain.Book;
import com.example.Spring5.Web.App.repositories.AuthorRepository;
import com.example.Spring5.Web.App.repositories.BookRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Spring will look for instances of Command Line Runner and run the methods within them

@Component //This tells the spring framework to go ahead and detect this a spring manage component
public class BootStrapData implements CommandLineRunner {

    //The component enables dependency injection. Spring will create an object using the below interface constructor for us.
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric); // Saves into H2 Database
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "389348934");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod); // Saves into H2 Database
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());


    }
}
