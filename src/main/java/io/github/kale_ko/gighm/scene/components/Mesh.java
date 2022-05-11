package io.github.kale_ko.gighm.scene.components;

import javax.validation.constraints.NotNull;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;

/**
 * A mesh to render
 * 
 * @version 1.3.0
 * @since 1.0.0
 */
public class Mesh extends Component {
    /**
     * The vertices of the mesh
     * 
     * @since 1.0.0
     */
    private @NotNull float[] vertices;

    /**
     * The size of a vertice in the mesh
     * 
     * @since 1.0.0
     */
    private @NotNull int verticeSize;

    /**
     * The texture of the mesh
     * 
     * @since 1.0.0
     */
    private Texture2D texture;

    /**
     * The uvs of the mesh
     * 
     * @since 1.0.0
     */
    private float[] uvs;

    /**
     * The size of a uv in the mesh
     * 
     * @since 1.0.0
     */
    private int uvSize;

    /**
     * The triangles of the mesh
     * 
     * @since 1.0.0
     */
    private int[] triangles;

    /**
     * Create a mesh to render
     * 
     * @since 1.0.0
     */
    public Mesh(@NotNull float[] vertices, @NotNull int verticeSize) {
        this(vertices, verticeSize, null);
    }

    /**
     * Create a mesh to render
     * 
     * @since 1.0.0
     */
    public Mesh(@NotNull float[] vertices, @NotNull int verticeSize, int[] triangles) {
        this(vertices, verticeSize, null, null, 0, triangles);
    }

    /**
     * Create a mesh to render
     * 
     * @since 1.0.0
     */
    public Mesh(@NotNull float[] vertices, @NotNull int verticeSize, Texture2D texture, float[] uvs, int uvSize) {
        this(vertices, verticeSize, texture, uvs, uvSize, null);
    }

    /**
     * Create a mesh to render
     * 
     * @since 1.0.0
     */
    public Mesh(@NotNull float[] vertices, @NotNull int verticeSize, Texture2D texture, float[] uvs, int uvSize, int[] triangles) {
        super();

        this.vertices = vertices;

        this.texture = texture;
        this.uvs = uvs;

        this.verticeSize = verticeSize;
        this.uvSize = uvSize;

        this.triangles = triangles;
    }

    /**
     * Get the vertices of the mesh
     * 
     * @return The vertices of the mesh
     * 
     * @since 1.0.0
     */
    public @NotNull float[] getVertices() {
        return this.vertices;
    }

    /**
     * Get the size of a vertice in the mesh
     * 
     * @return The size of a vertice in the mesh
     * 
     * @since 1.0.0
     */
    public @NotNull int getVerticeSize() {
        return this.verticeSize;
    }

    /**
     * Get the texture of the mesh
     * 
     * @return The texture of the mesh
     * 
     * @since 1.0.0
     */
    public Texture2D getTexture() {
        return this.texture;
    }

    /**
     * Get the uvs of the mesh
     * 
     * @return The uvs of the mesh
     * 
     * @since 1.0.0
     */
    public float[] getUVs() {
        return this.uvs;
    }

    /**
     * Get the size of a uv in the mesh
     * 
     * @return The size of a uv in the mesh
     * 
     * @since 1.0.0
     */
    public int getUVSize() {
        return this.uvSize;
    }

    /**
     * Get the triangles of the mesh
     * 
     * @return The triangles of the mesh
     * 
     * @since 1.0.0
     */
    public int[] getTriangles() {
        return this.triangles;
    }

    /**
     * Get the name of the component
     * 
     * @return The name of the component
     * 
     * @since 1.0.0
     */
    public static @NotNull String getName() {
        return "Mesh";
    }
}