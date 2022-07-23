package io.github.kale_ko.gighm.exception;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Thrown if there is a problem pausing a thread
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
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
    public ThreadPauseException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}