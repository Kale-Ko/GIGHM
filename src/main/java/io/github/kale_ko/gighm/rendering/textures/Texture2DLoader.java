package io.github.kale_ko.gighm.rendering.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.validation.constraints.NotNull;
import org.lwjgl.BufferUtils;

/**
 * Utility for loading image data from a file to use in textures
 * 
 * @since 1.0.0
 */
public class Texture2DLoader {
    /**
     * Load an image into a texture from a file
     * 
     * @param file The file to load
     * 
     * @return A {@link Texture2D} representing the image
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull String file) throws IOException {
        return loadTexture(new File(file));
    }

    /**
     * Load an image into a texture from a file
     * 
     * @param file The file to load
     * 
     * @return A {@link Texture2D} representing the image
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull File file) throws IOException {
        BufferedImage img = ImageIO.read(file);

        int[] pixelData = new int[img.getWidth() * img.getHeight() * 4];
        pixelData = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());

        ByteBuffer pixels = BufferUtils.createByteBuffer(pixelData.length * 4);

        for (int i = 0; i < pixelData.length; i++) {
            int pixel = pixelData[i];

            pixels.put((byte) ((pixel >> 16) & 0xFF));
            pixels.put((byte) ((pixel >> 8) & 0xFF));
            pixels.put((byte) ((pixel >> 0) & 0xFF));
            pixels.put((byte) ((pixel >> 24) & 0xFF));
        }

        pixels.flip();

        return new Texture2D(img.getWidth(), img.getHeight(), pixels);
    }

    /**
     * Load an image from a file
     * 
     * @param file The file to load
     * 
     * @return A {@link ByteBuffer} containing the image data
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull String file) throws IOException {
        return loadTextureData(new File(file));
    }

    /**
     * Load an image from a file
     * 
     * @param file The file to load
     * 
     * @return A {@link ByteBuffer} containing the image data
     * 
     * @throws IOException
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull File file) throws IOException {
        BufferedImage img = ImageIO.read(file);

        int[] pixelData = new int[img.getWidth() * img.getHeight() * 4];
        pixelData = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());

        ByteBuffer pixels = BufferUtils.createByteBuffer(pixelData.length * 4);

        for (int i = 0; i < pixelData.length; i++) {
            int pixel = pixelData[i];

            pixels.put((byte) ((pixel << 16) & 0xFF));
            pixels.put((byte) ((pixel << 8) & 0xFF));
            pixels.put((byte) ((pixel << 0) & 0xFF));
            pixels.put((byte) ((pixel << 24) & 0xFF));
        }

        pixels.flip();

        return pixels;
    }
}