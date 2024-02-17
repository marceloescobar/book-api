package com.mescobar.bookapi.mapper;

import com.mescobar.bookapi.controller.dto.BookResponse;
import com.mescobar.bookapi.controller.dto.CreateBookRequest;
import com.mescobar.bookapi.controller.dto.UpdateBookRequest;
import com.mescobar.bookapi.model.Book;

public interface BookMapper {
    Book toBook(CreateBookRequest createBookRequest);

    void updateBookFromUpdateBookRequest(UpdateBookRequest updateBookRequest, Book book);

    BookResponse toBookResponse(Book book);
}
