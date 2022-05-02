package io.github.kale_ko.gighm.rendering.shaders;

import static org.lwjgl.opengl.GL20.*;
import javax.validation.constraints.NotNull;

/**
 * A shader
 * 
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
     * Weather the window is initialized
     * 
     * @since 1.0.0
     */
    private @NotNull boolean initialized = false;

    /**
     * The id of the shader program
     * 
     * @since 1.0.0
     */
    private int programId;

    /**
     * The id of the vertex shader
     * 
     * @since 1.0.0
     */
    private int vertexId;

    /**
     * The id of the fragment shader
     * 
     * @since 1.0.0
     */
    private int fragmentId;

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
     * Initialize the shader (Must be called from a {@link Renderer})
     * 
     * @throws RuntimeException If the renderer is already initialized
     * 
     * @since 1.0.0
     */
    public void init() {
        if (this.initialized) {
            throw new RuntimeException("The texture is already initialized");
        }

        this.initialized = true;

        this.programId = glCreateProgram();

        this.vertexId = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(this.vertexId, this.vertexSource);
        glCompileShader(this.vertexId);
        if (glGetShaderi(this.vertexId, GL_COMPILE_STATUS) != GL_TRUE) {
            System.err.println(glGetShaderInfoLog(this.vertexId));
            throw new RuntimeException("Failed to compile vertex shader");
        }

        this.fragmentId = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(this.fragmentId, this.fragmentSource);
        glCompileShader(this.fragmentId);
        if (glGetShaderi(this.fragmentId, GL_COMPILE_STATUS) != GL_TRUE) {
            System.err.println(glGetShaderInfoLog(this.fragmentId));
            throw new RuntimeException("Failed to compile fragment shader");
        }

        glAttachShader(this.programId, this.vertexId);
        glAttachShader(this.programId, this.fragmentId);

        glBindAttribLocation(this.programId, 0, "vertices");
        glBindAttribLocation(this.programId, 1, "uvs");

        glLinkProgram(this.programId);
        if (glGetProgrami(this.programId, GL_LINK_STATUS) != GL_TRUE) {
            System.err.println(glGetProgramInfoLog(this.programId));
            throw new RuntimeException("Failed to compile shader program");
        }
        glValidateProgram(this.programId);
        if (glGetProgrami(this.programId, GL_VALIDATE_STATUS) != GL_TRUE) {
            System.err.println(glGetProgramInfoLog(this.programId));
            throw new RuntimeException("Failed to compile shader program");
        }
    }

    public void setUniform(String id, int value) {
        int loc = glGetUniformLocation(this.programId, id);
        if (loc == -1) {
            throw new RuntimeException("Invalid uniform location: " + id);
        }

        glUniform1i(loc, value);
    }

    /**
     * Get weather the window is initialized
     * 
     * @return Weather the window is initialized
     * 
     * @since 1.0.0
     */
    public @NotNull boolean getInitialized() {
        return this.initialized;
    }

    /**
     * Get the id of the shader program
     * 
     * @return The id of the shader program
     * 
     * @since 1.0.0
     */
    public @NotNull int getProgramId() {
        return this.programId;
    }

    /**
     * Get the id of the vertex shader
     * 
     * @return The id of the vertex shader
     * 
     * @since 1.0.0
     */
    public @NotNull int getVertexId() {
        return this.vertexId;
    }

    /**
     * Get the id of the fragment shader
     * 
     * @return The id of the fragment shader
     * 
     * @since 1.0.0
     */
    public @NotNull int getFragmentId() {
        return this.fragmentId;
    }
}