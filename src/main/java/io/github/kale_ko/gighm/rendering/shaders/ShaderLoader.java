package io.github.kale_ko.gighm.rendering.shaders;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import javax.validation.constraints.NotNull;

/**
 * Utility for loading shader data from a file to use with shaders
 * 
 * @since 1.0.0
 */
public class ShaderLoader {
    /**
     * Load a shader from a file
     * 
     * @param file The file to load
     * 
     * @return A {@link Shader} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static Shader loadShader(@NotNull String vertexFile, @NotNull String fragmentFile) throws IOException {
        return loadShader(new File(vertexFile), new File(fragmentFile));
    }

    /**
     * Load a shader from a file
     * 
     * @param file The file to load
     * 
     * @return A {@link Shader} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static Shader loadShader(@NotNull File vertexFile, @NotNull File fragmentFile) throws IOException {
        return new Shader(loadShaderData(vertexFile), loadShaderData(fragmentFile));
    }

    /**
     * Load a shader's data from a file
     * 
     * @param file The file to load
     * 
     * @return A {@link String} representing the shader
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static String loadShaderData(@NotNull String file) throws IOException {
        return loadShaderData(new File(file));
    }

    /**
     * Load a shader's data from a file
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
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder data = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            data.append(line + "\n");
        }

        reader.close();

        return data.toString();
    }

    /**
     * Load a shader's data from a file
     * 
     * @param file The file to load
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