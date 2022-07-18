package io.github.kale_ko.gighm.rendering.objects;

import java.io.IOException;
import io.github.kale_ko.gighm.rendering.textures.Texture2DLoader;
import io.github.kale_ko.gighm.scene.components.Mesh;

/**
 * Meshes for a few primitive shapes
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 2.0.0
 */
public class PrimitiveObjects {
    /**
     * Meshes for a few primitive shapes
     * 
     * @since 2.0.0
     */
    public static final Mesh
        PLANE = createPlane(0.5f),
        CUBE = createCube(0.5f);

    /**
     * Create a plane with a certain size
     * 
     * @param size The size of the plane
     * 
     * @return A new plane with the specified size
     * 
     * @since 2.0.0
     */
    public static Mesh createPlane(Float size) {
        try {
            return new Mesh(new Float[] {
                -size, size,
                size, size,
                size, -size,

                -size, size,
                -size, -size,
                size, -size
            }, 2, Texture2DLoader.loadTexture(Texture2DLoader.class.getResourceAsStream("/assets/white.png")), new Float[] {
                0f, 0f,
                1f, 0f,
                1f, 1f,

                0f, 0f,
                0f, 1f,
                1f, 1f
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Create a cube with a certain size
     * 
     * @param size The size of the cube
     * 
     * @return A new cube with the specified size
     * 
     * @since 2.0.0
     */
    public static Mesh createCube(Float size) {
        try {
            return new Mesh(new Float[] {
                // Front
                -size, size, size,
                size, size, size,
                size, -size, size,

                -size, size, size,
                -size, -size, size,
                size, -size, size,

                // Back
                -size, size, -size,
                size, size, -size,
                size, -size, -size,

                -size, size, -size,
                -size, -size, -size,
                size, -size, -size,

                // Left
                -size, -size, size,
                -size, size, size,
                -size, size, -size,

                -size, -size, size,
                -size, -size, -size,
                -size, size, -size,

                // Right
                size, -size, size,
                size, size, size,
                size, size, -size,

                size, -size, size,
                size, -size, -size,
                size, size, -size,

                // Top
                -size, size, size,
                size, size, size,
                size, size, -size,

                -size, size, size,
                -size, size, -size,
                size, size, -size,

                // Bottom
                -size, -size, size,
                size, -size, size,
                size, -size, -size,

                -size, -size, size,
                -size, -size, -size,
                size, -size, -size
            }, 3, Texture2DLoader.loadTexture(Texture2DLoader.class.getResourceAsStream("/assets/white.png")), new Float[] {
                // Front
                0f, 0f,
                1f, 0f,
                1f, 1f,

                0f, 0f,
                0f, 1f,
                1f, 1f,

                // Back
                1f, 0f,
                0f, 0f,
                0f, 1f,

                1f, 0f,
                1f, 1f,
                0f, 1f,

                // Left
                1f, 1f,
                1f, 0f,
                0f, 0f,

                1f, 1f,
                0f, 1f,
                0f, 0f,

                // Right
                0f, 1f,
                0f, 0f,
                1f, 0f,

                0f, 1f,
                1f, 1f,
                1f, 0f,

                // Top
                0f, 1f,
                1f, 1f,
                1f, 0f,

                0f, 1f,
                0f, 0f,
                1f, 0f,

                // Bottom
                0f, 1f,
                1f, 1f,
                1f, 0f,

                0f, 1f,
                0f, 0f,
                1f, 0f,
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}