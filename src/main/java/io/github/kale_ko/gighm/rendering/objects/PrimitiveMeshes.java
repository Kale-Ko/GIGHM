package io.github.kale_ko.gighm.rendering.objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.github.kale_ko.gighm.rendering.textures.Texture2DLoader;
import io.github.kale_ko.gighm.scene.components.Mesh;

/**
 * Meshes for a few primitive shapes
 * 
 * @author Kale Ko
 * 
 * @version 2.3.0
 * @since 2.0.0
 */
public class PrimitiveMeshes {
    /**
     * Meshes for a few primitive shapes
     * 
     * @since 2.0.0
     */
    public static final Mesh
        PLANE = createPlane(0.5f),
        CUBE = createCube(0.5f),
        CIRCLE = createCircle(0.5f, 64);

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

    /**
     * Create a circle with a certain size and quality
     * 
     * @param size The size of the circle
     * @param quality The quality of the circle
     * 
     * @return A new circle with the specified size
     * 
     * @since 2.3.0
     */
    public static Mesh createCircle(Float size, Integer quality) {
        try {
            List<Float> points = new ArrayList<Float>();
            List<Float> uvs = new ArrayList<Float>();

            for (Integer i = 0; i < quality; i++) {
                points.add((float) Math.cos(((Math.PI * 2) / quality) * i));
                points.add((float) Math.sin(((Math.PI * 2) / quality) * i));

                points.add(0f);
                points.add(0f);

                points.add((float) Math.cos(((Math.PI * 2) / quality) * (i + 1)));
                points.add((float) Math.sin(((Math.PI * 2) / quality) * (i + 1)));

                uvs.add(Math.abs((float) Math.cos(((Math.PI * 2) / quality) * i) / 2 + 0.5f));
                uvs.add(Math.abs(-(float) Math.sin(((Math.PI * 2) / quality) * i) / 2 + 0.5f));

                uvs.add(0.5f);
                uvs.add(0.5f);

                uvs.add(Math.abs((float) Math.cos(((Math.PI * 2) / quality) * (i + 1)) / 2 + 0.5f));
                uvs.add(Math.abs(-(float) Math.sin(((Math.PI * 2) / quality) * (i + 1)) / 2 + 0.5f));
            }

            return new Mesh(points.toArray(new Float[] {}), 2, Texture2DLoader.loadTexture(Texture2DLoader.class.getResourceAsStream("/assets/white.png")), uvs.toArray(new Float[] {}));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}