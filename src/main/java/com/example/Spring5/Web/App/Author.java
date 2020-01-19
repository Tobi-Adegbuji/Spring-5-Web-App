package com.example.Spring5.Web.App;
//Using JPA
//d2 url: http://localhost:8080/h2-console
//d2 driver url: jdbc:h2:mem:testdb
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    //Making this a primary key is what we call leakage. B/C truly in an OO World we don't care about an ID/Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //Not sure if oracle supports this but MySql and d2 do. 
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author(){
    }

    public Author(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName, Set<Book> books){
        this(firstName,lastName);
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
