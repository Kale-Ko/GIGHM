package io.github.kale_ko.gighm.rendering.textures;

import java.nio.ByteBuffer;
import javax.validation.constraints.NotNull;

/**
 * A 2D texture
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
    private @NotNull int width;

    /**
     * The height of the texture
     * 
     * @since 1.0.0
     */
    private @NotNull int height;

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
     * @see Texture2DLoader#loadTexture
     * 
     * @since 1.0.0
     */
    public Texture2D(@NotNull int width, @NotNull int height, @NotNull ByteBuffer data) {
        this.width = width;
        this.height = height;

        this.data = data;
    }

    /**
     * Get the width of the texture
     * 
     * @return The width of the texture
     * 
     * @since 1.2.0
     */
    public @NotNull int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the texture
     * 
     * @return The height of the texture
     * 
     * @since 1.2.0
     */
    public @NotNull int getHeight() {
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