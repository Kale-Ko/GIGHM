package io.github.kale_ko.gighm.rendering.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.validation.constraints.NotNull;
import org.lwjgl.BufferUtils;

/**
 * Utility for loading image data from a file to use in textures
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class Texture2DLoader {
    /**
     * Load a texture from file
     * 
     * @param path The texture file to load
     * 
     * @return A {@link Texture2D} representing the image
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull String path) throws IOException {
        return loadTexture(new File(path));
    }

    /**
     * Load a texture from file
     * 
     * @param file The texture file to load
     * 
     * @return A {@link Texture2D} representing the image
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull File file) throws IOException {
        return loadTexture(new FileInputStream(file));
    }

    /**
     * Load a texture from stream
     * 
     * @param path The texture stream to load
     * 
     * @return A {@link Texture2D} representing the image
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull InputStream stream) throws IOException {
        BufferedImage image = ImageIO.read(stream);

        return new Texture2D(image.getWidth(), image.getHeight(), loadTextureData(image));
    }

    /**
     * Load a texture data from file
     * 
     * @param path The texture file to load
     * 
     * @return A {@link ByteBuffer} containing the image data
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull String path) throws IOException {
        return loadTextureData(new File(path));
    }

    /**
     * Load a texture data from file
     * 
     * @param file The texture file to load
     * 
     * @return A {@link ByteBuffer} containing the image data
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull File file) throws IOException {
        return loadTextureData(new FileInputStream(file));
    }

    /**
     * Load a texture data from stream
     * 
     * @param stream The texture stream to load
     * 
     * @return A {@link ByteBuffer} containing the image data
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull InputStream stream) throws IOException {
        return loadTextureData(ImageIO.read(stream));
    }

    /**
     * Load a texture data from stream
     * 
     * @param stream The texture stream to load
     * 
     * @return A {@link ByteBuffer} containing the image data
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull BufferedImage image) {
        int[] pixelData = new int[image.getWidth() * image.getHeight() * 4];
        pixelData = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        ByteBuffer pixels = BufferUtils.createByteBuffer(pixelData.length * 4);

        for (int i = 0; i < pixelData.length; i++) {
            int pixel = pixelData[i];

            pixels.put((byte) ((pixel >> 16) & 0xFF));
            pixels.put((byte) ((pixel >> 8) & 0xFF));
            pixels.put((byte) ((pixel >> 0) & 0xFF));
            pixels.put((byte) ((pixel >> 24) & 0xFF));
        }

        pixels.flip();

        return pixels;
    }
}