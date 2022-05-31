package io.github.kale_ko.gighm.scene.components;

import java.awt.Color;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * A mesh that can be rendered
 * 
 * @author Kale Ko
 * 
 * @version 1.9.0
 * @since 1.0.0
 */
public class Mesh extends Component {
    /**
     * The vertices of the mesh
     * 
     * @since 1.0.0
     */
    private @NotNull Float[] vertices;

    /**
     * How many numbers define a point in the mesh
     * 
     * @since 1.0.0
     */
    private @NotNull Integer verticeSize;

    /**
     * The texture of the mesh
     * 
     * @since 1.0.0
     */
    private @Nullable Texture2D texture;

    /**
     * The uvs of the mesh
     * 
     * @since 1.0.0
     */
    private @Nullable Float[] uvs;

    /**
     * The color of the mesh (Will only be used if there is no texture)
     * 
     * @since 1.9.0
     */
    private @Nullable Color color;

    /**
     * The triangles of the mesh
     * 
     * @since 1.0.0
     */
    private @Nullable Integer[] triangles;

    /**
     * Create a mesh
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize How many numbers define a point in the mesh
     * @param texture The texture of the mesh
     * @param uvs The uvs of the mesh
     * 
     * @since 1.0.0
     */
    public Mesh(@NotNull Float[] vertices, @NotNull Integer verticeSize, @Nullable Texture2D texture, @Nullable Float[] uvs) {
        this(vertices, verticeSize, texture, uvs, null);
    }

    /**
     * Create a mesh
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize How many numbers define a point in the mesh
     * @param color The color of the mesh
     * 
     * @since 1.9.0
     */
    public Mesh(@NotNull Float[] vertices, @NotNull Integer verticeSize, @Nullable Color color) {
        this(vertices, verticeSize, color, null);
    }

    /**
     * Create a mesh
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize How many numbers define a point in the mesh
     * @param texture The texture of the mesh
     * @param uvs The uvs of the mesh
     * @param triangles The triangles of the mesh
     * 
     * @since 1.0.0
     */
    public Mesh(@NotNull Float[] vertices, @NotNull Integer verticeSize, @Nullable Texture2D texture, @Nullable Float[] uvs, @Nullable Integer[] triangles) {
        NullUtils.checkNulls(vertices, "vertices");
        NullUtils.checkNulls(verticeSize, "verticeSize");

        this.vertices = vertices;
        this.verticeSize = verticeSize;

        this.texture = texture;
        this.uvs = uvs;

        this.triangles = triangles;

        if (this.verticeSize != 2 && this.verticeSize != 3) {
            throw new RuntimeException("Vertice size must be either 2 or 3");
        }
    }

    /**
     * Create a mesh
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize How many numbers define a point in the mesh
     * @param color The color of the mesh
     * @param triangles The triangles of the mesh
     * 
     * @since 1.9.0
     */
    public Mesh(@NotNull Float[] vertices, @NotNull Integer verticeSize, @Nullable Color color, @Nullable Integer[] triangles) {
        NullUtils.checkNulls(vertices, "vertices");
        NullUtils.checkNulls(verticeSize, "verticeSize");

        this.vertices = vertices;
        this.verticeSize = verticeSize;

        this.color = color;

        this.triangles = triangles;

        if (this.verticeSize != 2 && this.verticeSize != 3) {
            throw new RuntimeException("Vertice size must be either 2 or 3");
        }
    }

    /**
     * Get the vertices of the mesh
     * 
     * @return The vertices of the mesh
     * 
     * @since 1.0.0
     */
    public @NotNull Float[] getVertices() {
        return this.vertices;
    }

    /**
     * Get how many numbers define a point in the mesh
     * 
     * @return How many numbers define a point in the mesh
     * 
     * @since 1.0.0
     */
    public @NotNull Integer getVerticeSize() {
        return this.verticeSize;
    }

    /**
     * Get the texture of the mesh
     * 
     * @return The texture of the mesh
     * 
     * @since 1.0.0
     */
    public @Nullable Texture2D getTexture() {
        return this.texture;
    }

    /**
     * Get the uvs of the mesh
     * 
     * @return The uvs of the mesh
     * 
     * @since 1.0.0
     */
    public @Nullable Float[] getUVs() {
        return this.uvs;
    }

    /**
     * Get the color of the mesh
     * 
     * @return The color of the mesh
     * 
     * @since 1.9.0
     */
    public @Nullable Color getColor() {
        return this.color;
    }

    /**
     * Get the triangles of the mesh
     * 
     * @return The triangles of the mesh
     * 
     * @since 1.0.0
     */
    public @Nullable Integer[] getTriangles() {
        return this.triangles;
    }

    /**
     * Get the complete vertices of the mesh (The combined vertices and triangles)
     * 
     * @return The complete vertices of the mesh (The combined vertices and triangles)
     * 
     * @since 1.7.0
     */
    public @NotNull Float[] getFullVertices() {
        Float[] fullVertices = new Float[this.getTriangles().length];

        for (Integer i = 0; i < this.getTriangles().length; i++) {
            fullVertices[i] = this.getVertices()[this.getTriangles()[i]];
        }

        return fullVertices;
    }
}