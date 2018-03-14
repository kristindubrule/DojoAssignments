package com.codingdojo.books.controllers;

import com.codingdojo.books.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.codingdojo.books.models.Book;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;

@Controller
public class Books {
    private final BookService bookService;

    public Books(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("books",books);
        return "books";
    }

    @RequestMapping("/books/{id}")
    public String findBookByIndex(Model model, @PathVariable("id") Long id) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book",book);
        return "showBook";
    }

    @RequestMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "newBook";
    }

    @PostMapping("/books/new")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "newBook";
        } else {
            bookService.addBook(book);
            return "redirect:/books";
        }
    }

    @RequestMapping("/books/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book",book);
            return "editBook";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/books/edit")
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "editBook";
        } else {
            System.out.println("Update book");
            bookService.updateBook(book);
            return "redirect:/books";
        }
    }

    @RequestMapping("/books/delete/{id}")
    public String destroyBook(@PathVariable("id") Long id) {
        bookService.destroyBook(id);
        return "redirect:/books";
    }

}
