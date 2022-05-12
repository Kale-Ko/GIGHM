package io.github.kale_ko.gighm.rendering.textures;

import java.nio.ByteBuffer;

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
    private int width;

    /**
     * The height of the texture
     * 
     * @since 1.0.0
     */
    private int height;

    /**
     * The raw data of the texture
     * 
     * @since 1.0.0
     */
    private ByteBuffer data;

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
    public Texture2D(int width, int height, ByteBuffer data) {
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
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the texture
     * 
     * @return The height of the texture
     * 
     * @since 1.2.0
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Get the raw data of the texture
     * 
     * @return The raw data of the texture
     * 
     * @since 1.2.0
     */
    public ByteBuffer getRawData() {
        return this.data;
    }
}