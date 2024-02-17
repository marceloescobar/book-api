package com.mescobar.bookapi.mapper;

import com.mescobar.bookapi.controller.dto.BookResponse;
import com.mescobar.bookapi.controller.dto.CreateBookRequest;
import com.mescobar.bookapi.controller.dto.UpdateBookRequest;
import com.mescobar.bookapi.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper{

    @Override
    public Book toBook(CreateBookRequest createBookRequest) {
        if (createBookRequest == null) {
            return null;
        }
        return Book.builder()
                .title(createBookRequest.title())
                .author(createBookRequest.author())
                .year(createBookRequest.year())
                .build();
    }

    @Override
    public void updateBookFromUpdateBookRequest(UpdateBookRequest updateBookRequest, Book book) {
        if (updateBookRequest == null) {
            return;
        }
        if (updateBookRequest.title() != null) {
            book.setTitle(updateBookRequest.title());
        }
        if (updateBookRequest.author() != null) {
            book.setAuthor(updateBookRequest.author());
        }
        if (updateBookRequest.year() != null) {
            book.setYear(updateBookRequest.year());
        }
    }

    @Override
    public BookResponse toBookResponse(Book book) {
        if (book == null) {
            return null;
        }
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
    }
}
