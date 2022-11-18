package io.github.kale_ko.gighm.rendering;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import io.github.kale_ko.gighm.exception.AlreadyInitializedException;
import io.github.kale_ko.gighm.exception.GLCompileException;
import io.github.kale_ko.gighm.exception.IncorrectThreadException;
import io.github.kale_ko.gighm.exception.NotInitializedException;
import io.github.kale_ko.gighm.rendering.objects.Skybox;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Camera;
import io.github.kale_ko.gighm.scene.components.Mesh;
import io.github.kale_ko.gighm.scene.components.Transform;
import io.github.kale_ko.gighm.util.ArrayUtils;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A renderer for rendering scenes to windows
 * 
 * @author Kale Ko
 * 
 * @version 1.5.0
 * @since 1.0.0
 */
public class Renderer {
    /**
     * The scene to be rendered
     * 
     * @since 1.0.0
     */
    private @NotNull Scene scene;

    /**
     * The camera to render from
     * 
     * @since 1.0.0
     */
    private @NotNull Camera camera;

    /**
     * The shader to use while rendering
     * 
     * @since 1.0.0
     */
    private @NotNull Shader shader;

    /**
     * The skybox to clear the background with
     * 
     * @since 2.1.0
     */
    private @NotNull Skybox skybox;

    /**
     * The color to clear the background with
     * 
     * @since 1.1.0
     */
    private @NotNull Color clearColor;

    /**
     * Weather the window is initialized
     * 
     * @since 1.0.0
     */
    private @NotNull Boolean initialized = false;

    /**
     * A map of shaders to their gl program ids (Only used internally)
     * 
     * @since 1.3.0
     */
    private @NotNull Map<Shader, Integer> shaderPrograms = new HashMap<Shader, Integer>();

    /**
     * A map of shaders to their gl vertex shader ids (Only used internally)
     * 
     * @since 1.3.0
     */
    private @NotNull Map<Shader, Integer> shaderVertexShaders = new HashMap<Shader, Integer>();

    /**
     * A map of shaders to their gl fragment shader ids (Only used internally)
     * 
     * @since 1.3.0
     */
    private @NotNull Map<Shader, Integer> shaderFragmentShaders = new HashMap<Shader, Integer>();

    /**
     * A map of meshes to their gl vertex buffer ids (Only used internally)
     * 
     * @since 1.3.0
     */
    private @NotNull Map<Mesh, Integer> meshVertBuffers = new HashMap<Mesh, Integer>();

    /**
     * A map of meshes to their gl uv buffer ids (Only used internally)
     * 
     * @since 1.3.0
     */
    private @NotNull Map<Mesh, Integer> meshUvBuffers = new HashMap<Mesh, Integer>();

    /**
     * A map of meshes to their gl triangle buffer ids (Only used internally)
     * 
     * @since 1.3.0
     */
    private @NotNull Map<Mesh, Integer> meshTriBuffers = new HashMap<Mesh, Integer>();

    /**
     * A map of textures to their gl texture ids (Only used internally)
     * 
     * @since 1.3.0
     */
    private @NotNull Map<Texture2D, Integer> textures = new HashMap<Texture2D, Integer>();

    /**
     * Create a renderer
     * 
     * @param scene The scene to be rendered
     * @param camera The camera to render from
     * @param shader The shader to use while rendering
     * 
     * @since 1.0.0
     */
    public Renderer(@NotNull Scene scene, @NotNull Camera camera, @NotNull Shader shader) {
        this(scene, camera, shader, new Color(0, 0, 0));
    }

    /**
     * Create a renderer
     * 
     * @param scene The scene to be rendered
     * @param camera The camera to render from
     * @param shader The shader to use while rendering
     * @param skybox The skybox to clear the background with
     * 
     * @since 2.1.0
     */
    public Renderer(@NotNull Scene scene, @NotNull Camera camera, @NotNull Shader shader, @NotNull Skybox skybox) {
        NullUtils.checkNulls(scene, "scene");
        NullUtils.checkNulls(camera, "camera");
        NullUtils.checkNulls(shader, "shader");
        NullUtils.checkNulls(skybox, "skybox");

        this.scene = scene;
        this.camera = camera;

        this.shader = shader;

        this.clearColor = new Color(0, 0, 0);
        this.skybox = skybox;
    }

    /**
     * Create a renderer
     * 
     * @param scene The scene to be rendered
     * @param camera The camera to render from
     * @param shader The shader to use while rendering
     * @param clearColor The color to clear the background with
     * 
     * @since 1.0.0
     */
    public Renderer(@NotNull Scene scene, @NotNull Camera camera, @NotNull Shader shader, @NotNull Color clearColor) {
        NullUtils.checkNulls(scene, "scene");
        NullUtils.checkNulls(camera, "camera");
        NullUtils.checkNulls(shader, "shader");
        NullUtils.checkNulls(clearColor, "clearColor");

        this.scene = scene;
        this.camera = camera;

        this.shader = shader;

        this.clearColor = clearColor;
    }

    /**
     * Initialize the renderer (Must be called from a {@link Window})
     * 
     * @throws AlreadyInitializedException If the renderer is already initialized
     * @throws IncorrectThreadException If the method is not called from the window
     * 
     * @since 1.0.0
     */
    public void init() throws AlreadyInitializedException, IncorrectThreadException {
        if (!Thread.currentThread().getName().startsWith("GIGHM-")) {
            throw new IncorrectThreadException("You can only call this method from a Window");
        }

        if (this.initialized) {
            throw new AlreadyInitializedException("The renderer is already initialized");
        }

        this.initialized = true;

        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
    }

    /**
     * Render the scene to the window (Must be called from a {@link Window})
     * 
     * @throws NotInitializedException If the renderer is not initialized
     * @throws IncorrectThreadException If the method is not called from the window
     * 
     * @since 1.0.0
     */
    public void render() throws NotInitializedException, IncorrectThreadException {
        if (!Thread.currentThread().getName().startsWith("GIGHM-")) {
            throw new IncorrectThreadException("You can only call this method from a Window");
        }

        if (!this.initialized) {
            throw new NotInitializedException("The renderer is not initialized");
        }

        glClearColor(((float) this.clearColor.getRed()) / 255f, ((float) this.clearColor.getGreen()) / 255f, ((float) this.clearColor.getBlue()) / 255f, 1.0f);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        if (!this.shaderPrograms.containsKey(shader)) {
            Integer programId = glCreateProgram();
            this.shaderPrograms.put(shader, programId);

            Integer vertexId = glCreateShader(GL_VERTEX_SHADER);
            this.shaderVertexShaders.put(shader, vertexId);
            glShaderSource(vertexId, shader.getVertexSource());
            glCompileShader(vertexId);
            if (glGetShaderi(vertexId, GL_COMPILE_STATUS) != GL_TRUE) {
                System.err.println(glGetShaderInfoLog(vertexId));

                throw new GLCompileException("Failed to compile vertex shader");
            }

            Integer fragmentId = glCreateShader(GL_FRAGMENT_SHADER);
            this.shaderFragmentShaders.put(shader, fragmentId);
            glShaderSource(fragmentId, shader.getFragmentSource());
            glCompileShader(fragmentId);
            if (glGetShaderi(fragmentId, GL_COMPILE_STATUS) != GL_TRUE) {
                System.err.println(glGetShaderInfoLog(fragmentId));

                throw new GLCompileException("Failed to compile fragment shader");
            }

            glAttachShader(programId, vertexId);
            glAttachShader(programId, fragmentId);

            glBindAttribLocation(programId, 0, "vertices");
            glBindAttribLocation(programId, 1, "uvs");

            glLinkProgram(programId);
            if (glGetProgrami(programId, GL_LINK_STATUS) != GL_TRUE) {
                System.err.println(glGetProgramInfoLog(programId));

                throw new GLCompileException("Failed to compile shader program");
            }
            glValidateProgram(programId);
            if (glGetProgrami(programId, GL_VALIDATE_STATUS) != GL_TRUE) {
                System.err.println(glGetProgramInfoLog(programId));

                throw new GLCompileException("Failed to compile shader program");
            }
        }

        glUseProgram(this.shaderPrograms.get(shader));

        if (this.skybox != null) {
            glDisable(GL_DEPTH_TEST);

            Float size = this.camera.getNear() * 2;
            Mesh[] skyboxMeshes = new Mesh[] {
                new Mesh(new Float[] {
                    -size, size, size,
                    size, size, size,
                    size, -size, size,

                    -size, size, size,
                    -size, -size, size,
                    size, -size, size
                }, 3, this.skybox.front, new Float[] {
                    0f, 0f,
                    1f, 0f,
                    1f, 1f,

                    0f, 0f,
                    0f, 1f,
                    1f, 1f
                }),
                new Mesh(new Float[] {
                    -size, size, -size,
                    size, size, -size,
                    size, -size, -size,

                    -size, size, -size,
                    -size, -size, -size,
                    size, -size, -size
                }, 3, this.skybox.back, new Float[] {
                    1f, 0f,
                    0f, 0f,
                    0f, 1f,

                    1f, 0f,
                    1f, 1f,
                    0f, 1f
                }),
                new Mesh(new Float[] {
                    -size, -size, size,
                    -size, size, size,
                    -size, size, -size,

                    -size, -size, size,
                    -size, -size, -size,
                    -size, size, -size
                }, 3, this.skybox.left, new Float[] {
                    1f, 1f,
                    1f, 0f,
                    0f, 0f,

                    1f, 1f,
                    0f, 1f,
                    0f, 0f
                }),
                new Mesh(new Float[] {
                    size, -size, size,
                    size, size, size,
                    size, size, -size,

                    size, -size, size,
                    size, -size, -size,
                    size, size, -size
                }, 3, this.skybox.right, new Float[] {
                    0f, 1f,
                    0f, 0f,
                    1f, 0f,

                    0f, 1f,
                    1f, 1f,
                    1f, 0f
                }),
                new Mesh(new Float[] {
                    -size, size, size,
                    size, size, size,
                    size, size, -size,

                    -size, size, size,
                    -size, size, -size,
                    size, size, -size
                }, 3, this.skybox.top, new Float[] {
                    0f, 1f,
                    1f, 1f,
                    1f, 0f,

                    0f, 1f,
                    0f, 0f,
                    1f, 0f
                }),
                new Mesh(new Float[] {
                    -size, -size, size,
                    size, -size, size,
                    size, -size, -size,

                    -size, -size, size,
                    -size, -size, -size,
                    size, -size, -size
                }, 3, this.skybox.bottom, new Float[] {
                    0f, 1f,
                    1f, 1f,
                    1f, 0f,

                    0f, 1f,
                    0f, 0f,
                    1f, 0f
                })
            };

            for (Mesh mesh : skyboxMeshes) {
                if (!this.meshVertBuffers.containsKey(mesh)) {
                    Integer vertId = glGenBuffers();
                    this.meshVertBuffers.put(mesh, vertId);

                    FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(mesh.getVertices().length);
                    vertBuffer.put(ArrayUtils.toPrimitive(mesh.getVertices()));
                    vertBuffer.flip();

                    glBindBuffer(GL_ARRAY_BUFFER, vertId);
                    glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);

                    glBindBuffer(GL_ARRAY_BUFFER, 0);

                    if (mesh.getUVs() != null) {
                        Integer uvId = glGenBuffers();
                        this.meshUvBuffers.put(mesh, uvId);

                        FloatBuffer uvBuffer = BufferUtils.createFloatBuffer(mesh.getUVs().length);
                        uvBuffer.put(ArrayUtils.toPrimitive(mesh.getUVs()));
                        uvBuffer.flip();

                        glBindBuffer(GL_ARRAY_BUFFER, uvId);
                        glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);

                        glBindBuffer(GL_ARRAY_BUFFER, 0);
                    }

                    if (mesh.getTriangles() != null) {
                        Integer triId = glGenBuffers();
                        this.meshTriBuffers.put(mesh, triId);

                        IntBuffer triBuffer = BufferUtils.createIntBuffer(mesh.getTriangles().length);
                        triBuffer.put(ArrayUtils.toPrimitive(mesh.getTriangles()));
                        triBuffer.flip();

                        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, triId);
                        glBufferData(GL_ELEMENT_ARRAY_BUFFER, triBuffer, GL_STATIC_DRAW);

                        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
                    }
                }

                Texture2D texture = mesh.getTexture();
                if (texture != null) {
                    if (!this.textures.containsKey(texture)) {
                        Integer textureId = glGenTextures();
                        this.textures.put(texture, textureId);
                        glBindTexture(GL_TEXTURE_2D, textureId);

                        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

                        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, texture.getRawData());
                    }

                    Integer loc = glGetUniformLocation(this.shaderPrograms.get(shader), "sampler");
                    glUniform1i(loc, this.textures.get(texture));

                    glActiveTexture(GL_TEXTURE0 + this.textures.get(texture));
                    glBindTexture(GL_TEXTURE_2D, this.textures.get(texture));
                } else {
                    Integer loc = glGetUniformLocation(this.shaderPrograms.get(shader), "sampler");
                    glUniform1i(loc, -1);

                    glActiveTexture(0);
                    glBindTexture(GL_TEXTURE_2D, 0);
                }

                Integer projectionLoc = glGetUniformLocation(this.shaderPrograms.get(shader), "projection");
                FloatBuffer projectionBuffer = BufferUtils.createFloatBuffer(16);
                camera.getProjection().mul(new Matrix4f().translate(new Vector3f(-camera.getGameObject().getComponent(Transform.class).getPosition().x, -camera.getGameObject().getComponent(Transform.class).getPosition().y, -camera.getGameObject().getComponent(Transform.class).getPosition().z))).get(projectionBuffer);
                glUniformMatrix4fv(projectionLoc, false, projectionBuffer);

                glEnableVertexAttribArray(0);
                glEnableVertexAttribArray(1);

                glBindBuffer(GL_ARRAY_BUFFER, this.meshVertBuffers.get(mesh));
                glVertexAttribPointer(0, mesh.getVerticeSize(), GL_FLOAT, false, 0, NULL);

                Integer hasSamplerLoc = glGetUniformLocation(this.shaderPrograms.get(shader), "hasSampler");
                glUniform1i(hasSamplerLoc, this.meshUvBuffers.containsKey(mesh) ? 1 : 0);

                if (this.meshUvBuffers.containsKey(mesh)) {
                    glBindBuffer(GL_ARRAY_BUFFER, this.meshUvBuffers.get(mesh));
                    glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, NULL);
                } else {
                    Integer colorLoc = glGetUniformLocation(this.shaderPrograms.get(shader), "color");
                    if (mesh.getColor() != null) {
                        glUniform3f(colorLoc, mesh.getColor().getRed(), mesh.getColor().getGreen(), mesh.getColor().getBlue());
                    } else {
                        glUniform3f(colorLoc, 255, 255, 255);
                    }
                }

                if (this.meshTriBuffers.containsKey(mesh)) {
                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.meshTriBuffers.get(mesh));

                    glDrawElements(GL_TRIANGLES, mesh.getTriangles().length, GL_UNSIGNED_INT, 0);

                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
                } else {
                    glDrawArrays(GL_TRIANGLES, 0, mesh.getVertices().length / mesh.getVerticeSize());
                }

                glBindBuffer(GL_ARRAY_BUFFER, 0);

                glDisableVertexAttribArray(0);
                glDisableVertexAttribArray(1);
            }
        }

        glEnable(GL_DEPTH_TEST);

        for (GameObject object : this.scene.getObjects()) {
            Mesh mesh = object.getComponent(Mesh.class);

            if (mesh != null) {
                if (!this.meshVertBuffers.containsKey(mesh)) {
                    Integer vertId = glGenBuffers();
                    this.meshVertBuffers.put(mesh, vertId);

                    FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(mesh.getVertices().length);
                    vertBuffer.put(ArrayUtils.toPrimitive(mesh.getVertices()));
                    vertBuffer.flip();

                    glBindBuffer(GL_ARRAY_BUFFER, vertId);
                    glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);

                    glBindBuffer(GL_ARRAY_BUFFER, 0);

                    if (mesh.getUVs() != null) {
                        Integer uvId = glGenBuffers();
                        this.meshUvBuffers.put(mesh, uvId);

                        FloatBuffer uvBuffer = BufferUtils.createFloatBuffer(mesh.getUVs().length);
                        uvBuffer.put(ArrayUtils.toPrimitive(mesh.getUVs()));
                        uvBuffer.flip();

                        glBindBuffer(GL_ARRAY_BUFFER, uvId);
                        glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);

                        glBindBuffer(GL_ARRAY_BUFFER, 0);
                    }

                    if (mesh.getTriangles() != null) {
                        Integer triId = glGenBuffers();
                        this.meshTriBuffers.put(mesh, triId);

                        IntBuffer triBuffer = BufferUtils.createIntBuffer(mesh.getTriangles().length);
                        triBuffer.put(ArrayUtils.toPrimitive(mesh.getTriangles()));
                        triBuffer.flip();

                        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, triId);
                        glBufferData(GL_ELEMENT_ARRAY_BUFFER, triBuffer, GL_STATIC_DRAW);

                        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
                    }
                }

                Texture2D texture = mesh.getTexture();
                if (texture != null) {
                    if (!this.textures.containsKey(texture)) {
                        Integer textureId = glGenTextures();
                        this.textures.put(texture, textureId);
                        glBindTexture(GL_TEXTURE_2D, textureId);

                        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

                        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, texture.getRawData());
                    }

                    Integer loc = glGetUniformLocation(this.shaderPrograms.get(shader), "sampler");
                    glUniform1i(loc, this.textures.get(texture));

                    glActiveTexture(GL_TEXTURE0 + this.textures.get(texture));
                    glBindTexture(GL_TEXTURE_2D, this.textures.get(texture));
                } else {
                    Integer loc = glGetUniformLocation(this.shaderPrograms.get(shader), "sampler");
                    glUniform1i(loc, -1);

                    glActiveTexture(0);
                    glBindTexture(GL_TEXTURE_2D, 0);
                }

                Integer projectionLoc = glGetUniformLocation(this.shaderPrograms.get(shader), "projection");
                FloatBuffer projectionBuffer = BufferUtils.createFloatBuffer(16);
                camera.getProjection().mul(object.getComponent(Transform.class).getMatrix()).get(projectionBuffer);
                glUniformMatrix4fv(projectionLoc, false, projectionBuffer);

                glEnableVertexAttribArray(0);
                glEnableVertexAttribArray(1);

                glBindBuffer(GL_ARRAY_BUFFER, this.meshVertBuffers.get(mesh));
                glVertexAttribPointer(0, mesh.getVerticeSize(), GL_FLOAT, false, 0, NULL);

                Integer hasSamplerLoc = glGetUniformLocation(this.shaderPrograms.get(shader), "hasSampler");
                glUniform1i(hasSamplerLoc, this.meshUvBuffers.containsKey(mesh) ? 1 : 0);

                if (this.meshUvBuffers.containsKey(mesh)) {
                    glBindBuffer(GL_ARRAY_BUFFER, this.meshUvBuffers.get(mesh));
                    glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, NULL);
                } else {
                    Integer colorLoc = glGetUniformLocation(this.shaderPrograms.get(shader), "color");
                    if (mesh.getColor() != null) {
                        glUniform3f(colorLoc, mesh.getColor().getRed(), mesh.getColor().getGreen(), mesh.getColor().getBlue());
                    } else {
                        glUniform3f(colorLoc, 255, 255, 255);
                    }
                }

                if (this.meshTriBuffers.containsKey(mesh)) {
                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.meshTriBuffers.get(mesh));

                    glDrawElements(GL_TRIANGLES, mesh.getTriangles().length, GL_UNSIGNED_INT, 0);

                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
                } else {
                    glDrawArrays(GL_TRIANGLES, 0, mesh.getVertices().length / mesh.getVerticeSize());
                }

                glBindBuffer(GL_ARRAY_BUFFER, 0);

                glDisableVertexAttribArray(0);
                glDisableVertexAttribArray(1);
            }
        }
    }

    /**
     * Get the scene to be rendered
     * 
     * @return The scene to be rendered
     * 
     * @since 1.2.0
     */
    public @NotNull Scene getScene() {
        return this.scene;
    }

    /**
     * Set the scene to be rendered
     * 
     * @param scene The scene to be rendered
     * 
     * @since 1.2.0
     */
    public void setScene(@NotNull Scene scene) {
        NullUtils.checkNulls(scene, "scene");

        this.scene = scene;
    }

    /**
     * Get the camera to render from
     * 
     * @return The camera to render from
     * 
     * @since 1.2.0
     */
    public @NotNull Camera getCamera() {
        return this.camera;
    }

    /**
     * Set the camera to render from
     * 
     * @param camera The camera to render from
     * 
     * @since 1.2.0
     */
    public void setCamera(@NotNull Camera camera) {
        NullUtils.checkNulls(camera, "camera");

        this.camera = camera;
    }

    /**
     * Get the shader to use while rendering
     * 
     * @return The shader to use while rendering
     * 
     * @since 1.2.0
     */
    public @NotNull Shader getShader() {
        return this.shader;
    }

    /**
     * Set the shader to use while rendering
     * 
     * @param shader The shader to use while rendering
     * 
     * @since 1.5.0
     */
    public void setShader(@NotNull Shader shader) {
        NullUtils.checkNulls(shader, "shader");

        this.shader = shader;
    }

    /**
     * Get the color to clear the background with
     * 
     * @return The color to clear the background with
     * 
     * @since 1.2.0
     */
    public @NotNull Color getClearColor() {
        return this.clearColor;
    }

    /**
     * Set the color to clear the background with
     * 
     * @param color Set the color to clear the background with
     * 
     * @since 1.1.0
     */
    public void setClearColor(@NotNull Color color) {
        NullUtils.checkNulls(color, "color");

        this.clearColor = color;
    }

    /**
     * Get the skybox to clear the background with
     * 
     * @return The skybox to clear the background with
     * 
     * @since 2.1.0
     */
    public @NotNull Skybox getSkybox() {
        return this.skybox;
    }

    /**
     * Set the skybox to clear the background with
     * 
     * @param skybox Set the skybox to clear the background with
     * 
     * @since 2.1.0
     */
    public void setSkybox(@NotNull Skybox skybox) {
        NullUtils.checkNulls(skybox, "skybox");

        this.skybox = skybox;
    }

    /**
     * Get weather the renderer is initialized
     * 
     * @return Weather the renderer is initialized
     * 
     * @since 1.2.0
     */
    public @NotNull Boolean getInitialized() {
        return this.initialized;
    }
}