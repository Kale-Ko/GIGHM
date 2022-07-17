package io.github.kale_ko.gighm.rendering.textures;

import java.nio.ByteBuffer;
import io.github.kale_ko.gighm.exception.InvalidDataException;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A 2D texture for applying to meshes
 * 
 * @author Kale Ko
 * 
 * @version 1.3.0
 * @since 1.0.0
 */
public class Texture2D {
    /**
     * The width of the texture
     * 
     * @since 1.0.0
     */
    private @NotNull Integer width;

    /**
     * The height of the texture
     * 
     * @since 1.0.0
     */
    private @NotNull Integer height;

    /**
     * The raw data of the texture
     * 
     * @since 1.0.0
     */
    private @NotNull ByteBuffer data;

    /**
     * Create a 2D texture
     * 
     * @param width The width of the texture
     * @param height The height of the texture
     * @param data The raw data of the texture
     * 
     * @throws InvalidDataException If the data is invalid
     * 
     * @since 1.0.0
     */
    public Texture2D(@NotNull Integer width, @NotNull Integer height, @NotNull ByteBuffer data) throws InvalidDataException {
        NullUtils.checkNulls(width, "width");
        NullUtils.checkNulls(height, "height");
        NullUtils.checkNulls(data, "data");

        this.width = width;
        this.height = height;

        this.data = data;

        if (data.capacity() / 4 != width * height && data.capacity() / 4 != width * height) {
            throw new InvalidDataException("Data size does not match width/height");
        }
    }

    /**
     * Get the width of the texture
     * 
     * @return The width of the texture
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getWidth() {
        return this.width;
    }

    /**
     * Get the height of the texture
     * 
     * @return The height of the texture
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getHeight() {
        return this.height;
    }

    /**
     * Get the raw data of the texture
     * 
     * @return The raw data of the texture
     * 
     * @since 1.2.0
     */
    public @NotNull ByteBuffer getRawData() {
        return this.data;
    }
}