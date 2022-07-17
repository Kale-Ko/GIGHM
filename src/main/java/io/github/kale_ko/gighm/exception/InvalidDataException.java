package io.github.kale_ko.gighm.exception;

/**
 * Thrown if some piece of data is invalid
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 2.0.0
 */
public class InvalidDataException extends RuntimeException {
    /**
     * Create an InvalidDataException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public InvalidDataException(String exception) {
        super(exception);
    }
}