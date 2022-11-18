package io.github.kale_ko.gighm.exception;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Thrown if OpenGL fails to compile a shader
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
 * @since 2.0.0
 */
public class GLCompileException extends RuntimeException {
    /**
     * Create a GLCompileException
     * 
     * @param exception The exception text
     * 
     * @since 2.0.0
     */
    public GLCompileException(@NotNull String exception) {
        super(exception);

        NullUtils.checkNulls(exception, "exception");
    }
}