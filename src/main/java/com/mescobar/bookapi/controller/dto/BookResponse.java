package com.mescobar.bookapi.controller.dto;

import java.io.Serializable;

public record BookResponse(String id, String title, String author, Integer year) implements Serializable {
}
