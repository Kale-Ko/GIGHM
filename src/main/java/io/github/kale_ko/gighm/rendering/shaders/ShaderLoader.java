package io.github.kale_ko.gighm.rendering.shaders;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A utility for loading shader data from a file to use with shaders
 * 
 * @author Kale Ko
 * 
 * @version 1.4.0
 * @since 1.0.0
 */
public class ShaderLoader {
    /**
     * Create a shader loader
     * 
     * @since 2.0.0
     */
    private ShaderLoader() {}

    /**
     * Load a shader from file
     * 
     * @param vertexPath The file to load the vertex data from
     * @param fragmentPath The file to load the fragment data from
     * 
     * @return A {@link Shader} from the data passed
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static @NotNull Shader loadShader(@NotNull String vertexPath, @NotNull String fragmentPath) throws IOException {
        NullUtils.checkNulls(vertexPath, "vertexPath");
        NullUtils.checkNulls(fragmentPath, "fragmentPath");

        return loadShader(new File(vertexPath), new File(fragmentPath));
    }

    /**
     * Load a shader from file
     * 
     * @param vertexFile The file to load the vertex data from
     * @param fragmentFile The file to load the fragment data from
     * 
     * @return A {@link Shader} from the data passed
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static @NotNull Shader loadShader(@NotNull File vertexFile, @NotNull File fragmentFile) throws IOException {
        NullUtils.checkNulls(vertexFile, "vertexFile");
        NullUtils.checkNulls(fragmentFile, "fragmentFile");

        return loadShader(new FileInputStream(vertexFile), new FileInputStream(fragmentFile));
    }

    /**
     * Load a shader from stream
     * 
     * @param vertexStream The stream to load the vertex data from
     * @param fragmentStream The stream to load the fragment data from
     * 
     * @return A {@link Shader} from the data passed
     * 
     * @throws IOException If it fails to read the stream
     * 
     * @since 1.0.0
     */
    public static @NotNull Shader loadShader(@NotNull InputStream vertexStream, @NotNull InputStream fragmentStream) throws IOException {
        NullUtils.checkNulls(vertexStream, "vertexStream");
        NullUtils.checkNulls(fragmentStream, "fragmentStream");

        return new Shader(loadShaderData(vertexStream), loadShaderData(fragmentStream));
    }

    /**
     * Load the default shader
     * 
     * @return The default shader
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.4.0
     */
    public static @NotNull Shader loadDefault() throws IOException {
        return loadShader(ShaderLoader.class.getResourceAsStream("/vertex.glsl"), ShaderLoader.class.getResourceAsStream("/fragment.glsl"));
    }

    /**
     * Load a file's contents
     * 
     * @param path The file to load from
     * 
     * @return A {@link String} representing the data
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static String loadShaderData(String path) throws IOException {
        return loadShaderData(new File(path));
    }

    /**
     * Load a file's contents
     * 
     * @param file The file to load from
     * 
     * @return A {@link String} representing the data
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static String loadShaderData(File file) throws IOException {
        return loadShaderData(new FileInputStream(file));
    }

    /**
     * Load a stream's contents
     * 
     * @param stream The stream to load from
     * 
     * @return A {@link String} representing the data
     * 
     * @throws IOException If it fails to read the stream
     * 
     * @since 1.0.0
     */
    public static String loadShaderData(InputStream stream) throws IOException {
        BufferedInputStream reader = new BufferedInputStream(stream);
        StringBuilder data = new StringBuilder();

        Integer read;
        byte[] contents = new byte[1024];
        while ((read = reader.read(contents)) != -1) {
            data.append(new String(contents, 0, read));
        }

        reader.close();

        return data.toString();
    }
}