package io.github.kale_ko.gighm.exception;

/**
 * Thrown if a method is called from the wrong thread
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 2.0.0
 */
public class IncorrectThreadException extends RuntimeException {
    /**
     * Create an IncorrectThreadException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public IncorrectThreadException(String exception) {
        super(exception);
    }
}