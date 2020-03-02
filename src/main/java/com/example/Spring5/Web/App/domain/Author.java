package com.example.Spring5.Web.App.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
//JPA (Java Persistence) is an interface.

@Entity //Tells Hibernate that this is an entity. Hibernate uses JPA
public class Author {

    @Id //Tells hibernates this is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //Tells hibernate this key is automatically generated.
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors") //We are saying the author POJO has a many-many relationship with books
    private Set<Book> books = new HashSet<>(); //In the books POJO, we created a join table to connect books and author in separate table

    //JPA requires a 0 arg constructor
    //JPA is an Object Relational Mapping tool

    public Author() {

    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

//Leakage means we are mixing classes with databases. We are treading the database territory
