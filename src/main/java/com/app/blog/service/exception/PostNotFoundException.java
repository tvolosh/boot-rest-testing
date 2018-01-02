package com.app.blog.service.exception;

/**
 * This exception is thrown when the requested post entry is not found.
 */
public class PostNotFoundException extends RuntimeException  {

    public PostNotFoundException(String id) {
        super(String.format("No post entry found with id: <%s>", id));
    }
}
