package io.github.kale_ko.gighm.rendering.objects;

import java.awt.Color;
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
        return new Mesh(new Float[] {
            -size, size,
            size, size,
            size, -size,
            -size, -size
        }, 2, new Color(255, 255, 255), new Float[] {
            0f, 0f,
            1f, 0f,
            1f, 1f,
            0f, 1f
        }, new Integer[] {
            0, 1, 2,
            2, 3, 0
        });
    }

    /**
     * Create a cube with a certain size (TODO Unfinished)
     * 
     * @param size The size of the cube
     * 
     * @return A new cube with the specified size
     * 
     * @since 2.0.0
     */
    public static Mesh createCube(Float size) {
        return new Mesh(new Float[] {
            -size, size, size,
            size, size, size,
            size, -size, size,
            -size, -size, size,

            -size, size, -size,
            size, size, -size,
            size, -size, -size,
            -size, -size, -size
        }, 3, new Color(255, 255, 255), new Float[] {
            0f, 0f,
            1f, 0f,
            1f, 1f,
            0f, 1f,

            1f, 0f,
            0f, 0f,
            0f, 1f,
            1f, 1f
        }, new Integer[] { 
            0, 1, 2,
            2, 3, 0,

            4, 5, 6,
            6, 7, 4,

            4, 7, 3,
            0, 4, 3,

            5, 6, 2,
            1, 5, 2
        });
    }
}