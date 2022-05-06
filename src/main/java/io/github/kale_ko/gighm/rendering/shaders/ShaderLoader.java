package io.github.kale_ko.gighm.rendering.shaders;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.validation.constraints.NotNull;

/**
 * Utility for loading shader data from a file to use with shaders
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShaderLoader {
    /**
     * Load a shader from file
     * 
     * @param vertexPath The vertex file to load
     * @param fragmentPath The fragment file to load
     * 
     * @return A {@link Shader} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static Shader loadShader(@NotNull String vertexPath, @NotNull String fragmentPath) throws IOException {
        return loadShader(new File(vertexPath), new File(fragmentPath));
    }

    /**
     * Load a shader from file
     * 
     * @param vertexFile The vertex file to load
     * @param fragmentFile The fragment file to load
     * 
     * @return A {@link Shader} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static Shader loadShader(@NotNull File vertexFile, @NotNull File fragmentFile) throws IOException {
        return loadShader(new FileInputStream(vertexFile), new FileInputStream(fragmentFile));
    }

    /**
     * Load a shader from stream
     * 
     * @param vertexStream The vertex stream to load
     * @param fragmentStream The fragment stream to load
     * 
     * @return A {@link Shader} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static Shader loadShader(@NotNull InputStream vertexStream, @NotNull InputStream fragmentStream) throws IOException {
        return new Shader(loadShaderData(vertexStream), loadShaderData(fragmentStream));
    }

    /**
     * Load a shader's data from file
     * 
     * @param path The file to load
     * 
     * @return A {@link String} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static String loadShaderData(@NotNull String path) throws IOException {
        return loadShaderData(new File(path));
    }

    /**
     * Load a shader's data from file
     * 
     * @param file The file to load
     * 
     * @return A {@link String} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static String loadShaderData(@NotNull File file) throws IOException {
        return loadShaderData(new FileInputStream(file));
    }

    /**
     * Load a shader's data from stream
     * 
     * @param stream The stream to load
     * 
     * @return A {@link String} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static String loadShaderData(@NotNull InputStream stream) throws IOException {
        BufferedInputStream reader = new BufferedInputStream(stream);
        StringBuilder data = new StringBuilder();

        int read;
        byte[] contents = new byte[1024];
        while ((read = reader.read(contents)) != -1) {
            data.append(new String(contents, 0, read));
        }

        reader.close();

        return data.toString();
    }
}