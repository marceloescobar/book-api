package com.mescobar.bookapi;

import com.mescobar.bookapi.model.Book;

public class TestUtils {

    public static Book getDefaultBook() {
        return Book.builder()
                .title("title")
                .author("author")
                .year(2023)
                .build();
    }

    public static final String API_BOOKS_URL = "/api/books";
    public static final String API_BOOKS_ID_URL = "/api/books/%s";

}
