package com.example.Spring5.Web.App.bootstrap;

import com.example.Spring5.Web.App.domain.Author;
import com.example.Spring5.Web.App.domain.Book;
import com.example.Spring5.Web.App.domain.Publisher;
import com.example.Spring5.Web.App.repositories.AuthorRepository;
import com.example.Spring5.Web.App.repositories.BookRepository;
import com.example.Spring5.Web.App.repositories.PublisherRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Spring will look for instances of Command Line Runner and run the methods within them

@Component //This tells the spring framework to go ahead and detect this a spring manage component
public class BootStrapData implements CommandLineRunner {

    //The component enables dependency injection. Spring will create an object using the below interface constructor for us.
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        //By putting constructor, you are letting spring data know which ones need dependency injection
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("Atlanta Inc");
        publisher.setCity("Atlanta");
        publisher.setState("GA");
        publisher.setZip("30303");
        publisher.setAddress("55 Park Pl");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd); //Now the publisher has a new book

        authorRepository.save(eric); // Saves into H2 Database. Save is a method that came from CrudRepository class
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "389348934");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod); // Saves into H2 Database
        bookRepository.save(noEJB); //This method is coming from CrudRepository Interface
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());


    }
}


//@Component tells spring that this a spring managed component. We are then implementing the methods
//from the CommandLineRunner interface. The only method that needed to be implemented was the run method.
//We are also saying the String managed component has 2 properties: AuthorRepo and BookRepo
//These properties are initialized through the constructor. When Spring Boot constructs this it has to inject
//an instance of AuthorRepo and BookRepo
//So when the run method starts up, it is configured by the spring framework. Inside the method we created author and
//book. We then use the repo methods to save them. Underneath the covers spring data JPA is utilizing hibernate
//to save these into an in-memory H2 Database.