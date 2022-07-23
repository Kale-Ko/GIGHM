package io.github.kale_ko.gighm.exception;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Thrown if an object is modified in an invalid way
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
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
    public InvalidModificationException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}