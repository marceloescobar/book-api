package com.mescobar.bookapi.service;

import com.mescobar.bookapi.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<Book> getBooks();

    Mono<Book> validateAndGetBookById(String id);

    Mono<Book> saveBook(Book book);

    Mono<Void> deleteBook(Book book);
}
