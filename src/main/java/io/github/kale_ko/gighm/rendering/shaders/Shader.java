package io.github.kale_ko.gighm.rendering.shaders;

/**
 * A shader
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
    private String vertexSource;

    /**
     * The source data of the fragment shader
     * 
     * @since 1.0.0
     */
    private String fragmentSource;

    /**
     * Create a shader
     * 
     * @param vertexSource The source data of the vertex shader
     * @param fragmentSource The source data of the fragment shader
     * 
     * @see ShaderLoader#loadShader
     * 
     * @since 1.0.0
     */
    public Shader(String vertexSource, String fragmentSource) {
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
    public String getVertexSource() {
        return this.vertexSource;
    }

    /**
     * Get the source of the fragment shader
     * 
     * @return The source of the fragment shader
     * 
     * @since 1.2.0
     */
    public String getFragmentSource() {
        return this.fragmentSource;
    }
}