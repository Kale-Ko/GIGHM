package io.github.kale_ko.gighm.exception;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Thrown if a method is called from the wrong thread
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
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
    public IncorrectThreadException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}