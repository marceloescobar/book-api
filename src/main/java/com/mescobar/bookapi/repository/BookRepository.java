package com.mescobar.bookapi.repository;

import com.mescobar.bookapi.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
