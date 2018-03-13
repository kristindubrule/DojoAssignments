package com.codingdojo.books.services;

import com.codingdojo.books.models.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<Book>(Arrays.asList(
            new Book("The Illiad","About a war","Greek",302),
            new Book("The Republic","About a city","Greek",202),
            new Book("how to Read a Book","About reading books","English",200)));

    public List<Book> allBooks() {
        return books;
    }

    public Book findBookByIndex(int index) {
        if (index < books.size()) {
            return books.get(index);
        } else {
            return null;
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int id, Book book) {
        if (id < books.size()) {
            books.set(id, book);
        }
    }

    public void destroyBook(int id) {
        if (id < books.size()) {
            books.remove(id);
        }
    }
}
