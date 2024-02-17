package com.mescobar.bookapi.mapper;

import com.mescobar.bookapi.controller.dto.BookResponse;
import com.mescobar.bookapi.controller.dto.CreateBookRequest;
import com.mescobar.bookapi.controller.dto.UpdateBookRequest;
import com.mescobar.bookapi.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(BookMapperImpl.class)
class BookMapperImplTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void testToBook() {
        CreateBookRequest createBookRequest = new CreateBookRequest("title", "author", 2023);

        Book book = bookMapper.toBook(createBookRequest);

        assertThat(book.getId()).isNull();
        assertThat(book.getTitle()).isEqualTo("title");
        assertThat(book.getAuthor()).isEqualTo("author");
        assertThat(book.getYear()).isEqualTo(2023);
    }

    @ParameterizedTest
    @MethodSource("provideUpdateBookRequests")
    void testUpdateBookFromUpdateBookRequest(UpdateBookRequest updateupdateBookRequest, Book expectedBook) {
        Book book = Book.builder()
                .author("author")
                .title("title")
                .year(2023)
                .build();
        bookMapper.updateBookFromUpdateBookRequest(updateupdateBookRequest, book);

        assertThat(book.getId()).isEqualTo(expectedBook.getId());
        assertThat(book.getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(book.getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(book.getYear()).isEqualTo(expectedBook.getYear());
    }

    private static Stream<Arguments> provideUpdateBookRequests() {
        return Stream.of(
                Arguments.of(new UpdateBookRequest("newTitle", null, null), createBook("author", "newTitle", 2023)),
                Arguments.of(new UpdateBookRequest(null, "newAuthor", null), createBook("newAuthor", "title", 2023)),
                Arguments.of(new UpdateBookRequest(null, null, 2024), createBook("author", "title", 2024)),
                Arguments.of(new UpdateBookRequest("newTitle", "newAuthor", 2024), createBook("newAuthor", "newTitle", 2024))
        );
    }

    @Test
    void testToBookResponse() {
        Book book = createBook("author", "title", 2023);
        BookResponse bookResponse = bookMapper.toBookResponse(book);

        assertThat(bookResponse.id()).isNull();
        assertThat(bookResponse.title()).isEqualTo("title");
        assertThat(bookResponse.author()).isEqualTo("author");
        assertThat(bookResponse.year()).isEqualTo(2023);
    }

    private static Book createBook(String author, String title, int year) {
        return Book.builder()
                .author(author)
                .title(title)
                .year(year)
                .build();
    }

}
