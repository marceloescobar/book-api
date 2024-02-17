package com.mescobar.bookapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateBookRequest(
        @Schema(example = "Spring Boot in Action") @NotBlank String title,
        @Schema(example = "Craig Walls") @NotBlank String author,
        @Schema(example = "2015") @Positive Integer year) {
}
