package com.mescobar.bookapi.controller.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UpdateBookRequestTest {

    @Autowired
    private JacksonTester<UpdateBookRequest> jacksonTester;

    @Test
    void testSerialize() throws IOException {
        UpdateBookRequest updateBookRequest = new UpdateBookRequest("title", "author", 2023);

        JsonContent<UpdateBookRequest> jsonContent = jacksonTester.write(updateBookRequest);

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
        String content = "{\"title\":\"title\",\"author\":\"author\",\"year\":2023}";

        UpdateBookRequest updateBookRequest = jacksonTester.parseObject(content);

        assertThat(updateBookRequest.title()).isEqualTo("title");
        assertThat(updateBookRequest.year()).isEqualTo(2023);
        assertThat(updateBookRequest.author()).isEqualTo("author");
    }
}
