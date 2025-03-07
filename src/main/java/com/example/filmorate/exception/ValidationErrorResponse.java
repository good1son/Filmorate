package com.example.filmorate.exception;

import java.util.List;

public record ValidationErrorResponse(List<ErrorResponse> validationErrors) {
}
