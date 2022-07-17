package io.github.kale_ko.gighm.exception;

/**
 * Thrown if there is a problem pausing a thread
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 2.0.0
 */
public class ThreadPauseException extends RuntimeException {
    /**
     * Create a ThreadPauseException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public ThreadPauseException(String exception) {
        super(exception);
    }
}