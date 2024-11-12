package com.data.dataservice.exceptions;

public record ApiError(String path, String message, int statusCode) {
}
