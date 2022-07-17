package io.github.kale_ko.gighm.rendering.shaders;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A shader used durning rendering
 * 
 * @author Kale Ko
 * 
 * @version 1.3.0
 * @since 1.0.0
 */
public class Shader {
    /**
     * The source data of the vertex shader
     * 
     * @since 1.0.0
     */
    private @NotNull String vertexSource;

    /**
     * The source data of the fragment shader
     * 
     * @since 1.0.0
     */
    private @NotNull String fragmentSource;

    /**
     * Create a shader
     * 
     * @param vertexSource The source data of the vertex shader
     * @param fragmentSource The source data of the fragment shader
     * 
     * @since 1.0.0
     */
    public Shader(@NotNull String vertexSource, @NotNull String fragmentSource) {
        NullUtils.checkNulls(vertexSource, "vertexSource");
        NullUtils.checkNulls(fragmentSource, "fragmentSource");

        this.vertexSource = vertexSource;
        this.fragmentSource = fragmentSource;
    }

    /**
     * Get the source of the vertex shader
     * 
     * @return The source of the vertex shader
     * 
     * @since 1.2.0
     */
    public @NotNull String getVertexSource() {
        return this.vertexSource;
    }

    /**
     * Get the source of the fragment shader
     * 
     * @return The source of the fragment shader
     * 
     * @since 1.2.0
     */
    public @NotNull String getFragmentSource() {
        return this.fragmentSource;
    }
}