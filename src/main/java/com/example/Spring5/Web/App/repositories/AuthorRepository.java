package com.example.Spring5.Web.App.repositories;

import com.example.Spring5.Web.App.domain.Author;
import org.springframework.data.repository.CrudRepository;

//We are providing the interface and spring interface will provide the implementation
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
