package io.github.kale_ko.gighm.exception;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Thrown if OpenGL fails to initialize
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
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
    public GLInitializeException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}