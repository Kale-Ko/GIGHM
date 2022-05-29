package io.github.kale_ko.gighm.rendering.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import io.github.kale_ko.gighm.util.ArrayUtils;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Utility for loading image data from a file to use in textures
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class Texture2DLoader {
    /**
     * Load a shader from file
     * 
     * @param path The file to load the data from
     * 
     * @return A {@link Texture2D} from the data passed
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull String path) throws IOException {
        NullUtils.checkNulls(path, "path");

        return loadTexture(new File(path));
    }

    /**
     * Load a shader from file
     * 
     * @param file The file to load the data from
     * 
     * @return A {@link Texture2D} from the data passed
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull File file) throws IOException {
        NullUtils.checkNulls(file, "file");

        return loadTexture(new FileInputStream(file));
    }

    /**
     * Load a shader from stream
     * 
     * @param stream The stream to load the data from
     * 
     * @return A {@link Texture2D} from the data passed
     * 
     * @throws IOException If it fails to read the stream
     * 
     * @since 1.0.0
     */
    public static @NotNull Texture2D loadTexture(@NotNull InputStream stream) throws IOException {
        NullUtils.checkNulls(stream, "stream");

        BufferedImage image = ImageIO.read(stream);

        return new Texture2D(image.getWidth(), image.getHeight(), loadTextureData(image));
    }

    /**
     * Load a textures data from file
     * 
     * @param path The file to load from
     * 
     * @return A {@link ByteBuffer} representing the data
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull String path) throws IOException {
        NullUtils.checkNulls(path, "path");

        return loadTextureData(new File(path));
    }

    /**
     * Load a textures data from file
     * 
     * @param file The file to load from
     * 
     * @return A {@link ByteBuffer} representing the data
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull File file) throws IOException {
        NullUtils.checkNulls(file, "file");

        return loadTextureData(new FileInputStream(file));
    }

    /**
     * Load a textures data from stream
     * 
     * @param stream The stream to load from
     * 
     * @return A {@link ByteBuffer} representing the data
     * 
     * @throws IOException If it fails to read the stream
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull InputStream stream) throws IOException {
        NullUtils.checkNulls(stream, "stream");

        return loadTextureData(ImageIO.read(stream));
    }

    /**
     * Load a textures data from image
     * 
     * @param image The image to load from
     * 
     * @return A {@link ByteBuffer} representing the data
     * 
     * @since 1.0.0
     */
    public static @NotNull ByteBuffer loadTextureData(@NotNull BufferedImage image) {
        NullUtils.checkNulls(image, "image");

        Integer[] pixelData = new Integer[image.getWidth() * image.getHeight() * 4];
        pixelData = ArrayUtils.toWrapper(image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()));

        ByteBuffer pixels = BufferUtils.createByteBuffer(pixelData.length * 4);

        for (Integer i = 0; i < pixelData.length; i++) {
            Integer pixel = pixelData[i];

            pixels.put((byte) ((pixel >> 16) & 0xFF));
            pixels.put((byte) ((pixel >> 8) & 0xFF));
            pixels.put((byte) ((pixel >> 0) & 0xFF));
            pixels.put((byte) ((pixel >> 24) & 0xFF));
        }

        pixels.flip();

        return pixels;
    }
}