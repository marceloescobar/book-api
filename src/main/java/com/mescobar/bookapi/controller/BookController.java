package com.mescobar.bookapi.controller;

import com.mescobar.bookapi.controller.dto.BookResponse;
import com.mescobar.bookapi.controller.dto.CreateBookRequest;
import com.mescobar.bookapi.controller.dto.UpdateBookRequest;
import com.mescobar.bookapi.mapper.BookMapper;
import com.mescobar.bookapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Operation(
            summary = "Get all books",
            description = "This endpoint is used to get all books ..."
    )
    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<BookResponse> getBook() {
        return bookService.getBooks().map(bookMapper::toBookResponse);
    }

    @Operation(
            summary = "Get a book",
            description = "This endpoint is used to get a specific book ..."
    )
    @GetMapping("/{id}")
    public Mono<BookResponse> getBook(@PathVariable String id) {
        return bookService.validateAndGetBookById(id).map(bookMapper::toBookResponse);
    }

    @Operation(
            summary = "Create a book",
            description = "This endpoint is used to create a specific book ..."
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<BookResponse> createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        return bookService.saveBook(bookMapper.toBook(createBookRequest)).map(bookMapper::toBookResponse);
    }

    @Operation(
            summary = "Update a book",
            description = "This endpoint is used to update a specific book ..."
    )
    @PatchMapping("/{id}")
    public Mono<BookResponse> updateBook(@PathVariable String id,
            @RequestBody UpdateBookRequest updateBookRequest) {
        return bookService.validateAndGetBookById(id)
                .doOnSuccess(book -> {
                    bookMapper.updateBookFromUpdateBookRequest(updateBookRequest, book);
                    bookService.saveBook(book).subscribe();
                })
                .map(bookMapper::toBookResponse);
    }

    @Operation(
            summary = "Delete a book",
            description = "This endpoint is used to delete a specific book ..."
    )
    @DeleteMapping("/{id}")
    public Mono<BookResponse> deleteBook(@PathVariable String id) {
        return bookService.validateAndGetBookById(id)
                .doOnSuccess(book -> bookService.deleteBook(book).subscribe())
                .map(bookMapper::toBookResponse);
    }
}
