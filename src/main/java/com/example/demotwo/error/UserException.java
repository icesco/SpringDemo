package com.example.demotwo.error;

public class UserException extends RuntimeException {

    public static class UserNotFoundException extends UserException { }

    public static class UserAlreadyExistsException extends UserException { }
}
