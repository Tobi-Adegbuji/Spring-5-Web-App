package com.example.Spring5.Web.App.controllers;

import com.example.Spring5.Web.App.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//  This is an example of a simple Spring MVC Controller

@Controller
public class BookController {

    private final BookRepository bookRepository;

    //We are injecting an instantiation of the Book Repo into our controller
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //When we do an action against the URL of books, this method will be invoked by Spring MVC framework
    @RequestMapping("/books") //The Model object is what gets returned to the view.
    public String getBooks(Model model){
        //Note that spring provides the model
        //The will have an attribute of an instance of our book repo which will then be returned to the view layer
        model.addAttribute("books", bookRepository.findAll());

        return "books/list";
    }

}
