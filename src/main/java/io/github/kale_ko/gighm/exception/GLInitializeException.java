package io.github.kale_ko.gighm.exception;

/**
 * Thrown if gl fails to initialize
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 2.0.0
 */
public class GLInitializeException extends RuntimeException {
    /**
     * Create a GLInitializeException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public GLInitializeException(String exception) {
        super(exception);
    }
}