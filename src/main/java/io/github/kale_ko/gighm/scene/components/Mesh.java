package io.github.kale_ko.gighm.scene.components;

import io.github.kale_ko.gighm.rendering.textures.Texture2D;

/**
 * A mesh to render
 * 
 * @version 1.7.0
 * @since 1.0.0
 */
public class Mesh extends Component {
    /**
     * The vertices of the mesh
     * 
     * @since 1.0.0
     */
    private float[] vertices;

    /**
     * The size of a vertice in the mesh
     * 
     * @since 1.0.0
     */
    private int verticeSize;

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
     * The triangles of the mesh
     * 
     * @since 1.0.0
     */
    private int[] triangles;

    /**
     * Create a mesh to render
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize The size of a vertice in the mesh
     * 
     * @since 1.0.0
     */
    public Mesh(float[] vertices, int verticeSize) {
        this(vertices, verticeSize, null);
    }

    /**
     * Create a mesh to render
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize The size of a vertice in the mesh
     * @param triangles The triangles of the mesh
     * 
     * @since 1.0.0
     */
    public Mesh(float[] vertices, int verticeSize, int[] triangles) {
        this(vertices, verticeSize, null, null, triangles);
    }

    /**
     * Create a mesh to render
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize The size of a vertice in the mesh
     * @param texture The texture of the mesh
     * @param uvs The uvs of the mesh
     * 
     * @since 1.0.0
     */
    public Mesh(float[] vertices, int verticeSize, Texture2D texture, float[] uvs) {
        this(vertices, verticeSize, texture, uvs, null);
    }

    /**
     * Create a mesh to render
     * 
     * @param vertices The vertices of the mesh
     * @param verticeSize The size of a vertice in the mesh
     * @param texture The texture of the mesh
     * @param uvs The uvs of the mesh
     * @param triangles The triangles of the mesh
     * 
     * @since 1.0.0
     */
    public Mesh(float[] vertices, int verticeSize, Texture2D texture, float[] uvs, int[] triangles) {
        this.vertices = vertices;

        this.texture = texture;
        this.uvs = uvs;

        this.verticeSize = verticeSize;

        if (this.verticeSize != 2 && this.verticeSize != 3) {
            throw new RuntimeException("Vertice size must be either 2 or 3");
        }

        this.triangles = triangles;
    }

    /**
     * Get the vertices of the mesh
     * 
     * @return The vertices of the mesh
     * 
     * @since 1.0.0
     */
    public float[] getVertices() {
        return this.vertices;
    }

    /**
     * Get the size of a vertice in the mesh
     * 
     * @return The size of a vertice in the mesh
     * 
     * @since 1.0.0
     */
    public int getVerticeSize() {
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
     * Get the complete vertices of the mesh
     * 
     * @return The complete vertices of the mesh
     * 
     * @since 1.7.0
     */
    public float[] getFullVertices() {
        float[] fullVertices = new float[this.getTriangles().length];

        for (int i = 0; i < this.getTriangles().length; i++) {
            fullVertices[i] = this.getVertices()[this.getTriangles()[i]];
        }

        return fullVertices;
    }

    /**
     * Get the name of the component
     * 
     * @return The name of the component
     * 
     * @since 1.0.0
     */
    public static String getName() {
        return "Mesh";
    }
}