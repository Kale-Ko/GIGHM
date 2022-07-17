package io.github.kale_ko.gighm.exception;

/**
 * Thrown if an object is modified in an invalid way
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 2.0.0
 */
public class InvalidModificationException extends RuntimeException {
    /**
     * Create an InvalidModificationException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public InvalidModificationException(String exception) {
        super(exception);
    }
}