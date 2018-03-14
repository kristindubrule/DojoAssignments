package com.codingdojo.books.services;

import com.codingdojo.books.models.Book;
import com.codingdojo.books.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private List<Book> books = new ArrayList<Book>(Arrays.asList(
            new Book("The Illiad","About a war","Greek",302),
            new Book("The Republic","About a city","Greek",202),
            new Book("how to Read a Book","About reading books","English",200)));

    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void destroyBook(Long id) {
        bookRepository.deleteById(id);
    }
}
