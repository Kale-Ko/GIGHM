package io.github.kale_ko.gighm.scene.components;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.validation.constraints.NotNull;
import org.lwjgl.BufferUtils;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;

/**
 * A mesh to render
 * 
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
     * The id of the OpenGL vertices buffer
     * 
     * @since 1.0.0
     */
    private Integer vertId;

    /**
     * The id of the OpenGL uvs buffer
     * 
     * @since 1.0.0
     */
    private Integer uvId;

    /**
     * The id of the OpenGL triangles buffer
     * 
     * @since 1.0.0
     */
    private Integer triId;

    /**
     * Weather the mesh is initialized
     * 
     * @since 1.0.0
     */
    private @NotNull boolean initialized = false;

    /**
     * Create a mesh to render
     * 
     * @since 1.0.0
     */
    public Mesh(@NotNull float[] vertices, @NotNull int verticeSize) {
        this(vertices, verticeSize, null, null, 0);
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
     * Initialize the mesh (Must be called from a {@link Renderer})
     * 
     * @throws RuntimeException If the mesh is already initialized
     * 
     * @since 1.0.0
     */
    public void init() {
        if (this.initialized) {
            throw new RuntimeException("The mesh is already initialized");
        }

        this.initialized = true;

        this.vertId = glGenBuffers();

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices);
        vertBuffer.flip();

        glBindBuffer(GL_ARRAY_BUFFER, this.vertId);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);

        if (this.uvs != null) {
            this.uvId = glGenBuffers();

            FloatBuffer uvBuffer = BufferUtils.createFloatBuffer(uvs.length);
            uvBuffer.put(uvs);
            uvBuffer.flip();

            glBindBuffer(GL_ARRAY_BUFFER, this.uvId);
            glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);
        }

        if (this.triangles != null) {
            this.triId = glGenBuffers();

            IntBuffer triBuffer = BufferUtils.createIntBuffer(triangles.length);
            triBuffer.put(triangles);
            triBuffer.flip();

            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.triId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, triBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        }
    }

    /**
     * Render the object to the window (Must be called from a {@link Renderer})
     * 
     * @since 1.0.0
     */
    public void render(Camera camera) {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, this.vertId);
        glVertexAttribPointer(0, verticeSize, GL_FLOAT, false, 0, NULL);

        if (this.uvs != null) {
            glBindBuffer(GL_ARRAY_BUFFER, this.uvId);
            glVertexAttribPointer(1, uvSize, GL_FLOAT, false, 0, NULL);
        }

        if (this.triangles != null) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.triId);

            glDrawElements(GL_TRIANGLES, this.triangles.length, GL_UNSIGNED_INT, 0);

            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        } else {
            glDrawArrays(GL_TRIANGLES, 0, this.vertices.length / this.verticeSize);
        }

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
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
     * Get weather the mesh is initialized
     * 
     * @return Weather the mesh is initialized
     * 
     * @since 1.0.0
     */
    public @NotNull boolean getInitialized() {
        return this.initialized;
    }

    /**
     * Get the id of the OpenGL vertices buffer
     * 
     * @return The id of the OpenGL vertices buffer
     * 
     * @since 1.0.0
     */
    public int getVerticesBufferId() {
        return this.vertId;
    }

    /**
     * Get the id of the OpenGL uvs buffer
     * 
     * @return The id of the OpenGL uvs buffer
     * 
     * @since 1.0.0
     */
    public int getUVsBufferId() {
        return this.uvId;
    }

    /**
     * Get the id of the OpenGL triangles buffer
     * 
     * @return The id of the OpenGL triangles buffer
     * 
     * @since 1.0.0
     */
    public int getTrianglesBufferId() {
        return this.triId;
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