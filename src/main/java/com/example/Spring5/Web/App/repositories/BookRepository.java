package com.example.Spring5.Web.App.repositories;

import com.example.Spring5.Web.App.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository <Book, Long>{
}
