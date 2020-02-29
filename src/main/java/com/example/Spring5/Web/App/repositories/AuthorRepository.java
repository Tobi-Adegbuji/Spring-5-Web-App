package com.example.Spring5.Web.App.repositories;

import com.example.Spring5.Web.App.domain.Author;
import org.springframework.data.repository.CrudRepository;

//We are providing the interface and spring class will provide the implementation
//CrudRepository provides implementation of spring data methods in our author repo
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
