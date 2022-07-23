package io.github.kale_ko.gighm.exception;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Thrown if the object in question is already initialized
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
 * @since 2.0.0
 */
public class AlreadyInitializedException extends RuntimeException {
    /**
     * Create an AlreadyInitializedException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public AlreadyInitializedException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}