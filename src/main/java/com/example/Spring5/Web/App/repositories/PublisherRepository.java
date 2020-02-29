package com.example.Spring5.Web.App.repositories;

import com.example.Spring5.Web.App.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
