package com.mescobar.bookapi.controller.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookResponseTest {
    @Autowired
    private JacksonTester<BookResponse> jacksonTester;

    @Test
    void testSerialize() throws IOException {
        BookResponse bookResponse = new BookResponse("123", "title", "author", 2023);

        JsonContent<BookResponse> jsonContent = jacksonTester.write(bookResponse);

        assertThat(jsonContent)
                .hasJsonPathStringValue("@.id")
                .extractingJsonPathStringValue("@.id").isEqualTo("123");

        assertThat(jsonContent)
                .hasJsonPathStringValue("@.title")
                .extractingJsonPathStringValue("@.title").isEqualTo("title");

        assertThat(jsonContent)
                .hasJsonPathStringValue("@.author")
                .extractingJsonPathStringValue("@.author").isEqualTo("author");

        assertThat(jsonContent)
                .hasJsonPathNumberValue("@.year")
                .extractingJsonPathNumberValue("@.year").isEqualTo(2023);
    }

    @Test
    void testDeserialize() throws IOException {
        String content = "{\"id\":\"123\",\"title\":\"title\",\"author\":\"author\",\"year\":2023}";

        BookResponse bookResponse = jacksonTester.parseObject(content);

        assertThat(bookResponse.id()).isEqualTo("123");
        assertThat(bookResponse.title()).isEqualTo("title");
        assertThat(bookResponse.author()).isEqualTo("author");
        assertThat(bookResponse.year()).isEqualTo(2023);
    }
}
