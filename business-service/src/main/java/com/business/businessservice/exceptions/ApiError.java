package com.business.businessservice.exceptions;

public record ApiError(String path, String message, int statusCode) {
}
