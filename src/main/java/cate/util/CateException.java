package cate.util;

/**
 * Represents a custom exception used in the Cate application.
 * A {@code CateException} is thrown when the user provides
 * invalid input or when an error occurs during command processing.
 */
public class CateException extends Exception {

    /**
     * Constructs a {@code CateException} with the specified detail message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public CateException(String message) {
        super(message);
    }
}
