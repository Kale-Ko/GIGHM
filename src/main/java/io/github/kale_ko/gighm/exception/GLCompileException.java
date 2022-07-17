package io.github.kale_ko.gighm.exception;

/**
 * Thrown if gl fails to compile a shader
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
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
    public GLCompileException(String exception) {
        super(exception);
    }
}