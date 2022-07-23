package io.github.kale_ko.gighm.exception;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Thrown if some piece of data is invalid
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
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
    public InvalidDataException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}