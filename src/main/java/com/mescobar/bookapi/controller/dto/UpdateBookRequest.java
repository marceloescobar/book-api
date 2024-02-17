package com.mescobar.bookapi.controller.dto;

public record UpdateBookRequest(String title, String author, Integer year) {
}
