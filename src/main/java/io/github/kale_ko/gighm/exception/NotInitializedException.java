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
public class NotInitializedException extends RuntimeException {
    /**
     * Create a NotInitializedException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public NotInitializedException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}