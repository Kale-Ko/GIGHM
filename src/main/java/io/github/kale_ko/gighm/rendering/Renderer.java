package io.github.kale_ko.gighm.rendering;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;
import javax.validation.constraints.NotNull;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Camera;
import io.github.kale_ko.gighm.scene.components.Mesh;
import io.github.kale_ko.gighm.scene.components.Transform;

/**
 * A renderer to render a scene
 * 
 * @since 1.0.0
 */
public class Renderer {
    /**
     * The scene to render
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
     * Weather the window is initialized
     * 
     * @since 1.0.0
     */
    private @NotNull boolean initialized = false;

    /**
     * The shader to use
     * 
     * @since 1.0.0
     */
    private @NotNull Shader shader;

    /**
     * Create a renderer to render a scene
     * 
     * @param scene The scene to render
     * 
     * @since 1.0.0
     */
    public Renderer(@NotNull Scene scene, Camera camera, Shader shader) {
        this.scene = scene;
        this.camera = camera;

        this.shader = shader;
    }

    /**
     * Initialize the renderer (Must be called from a {@link Window})
     * 
     * @throws RuntimeException If the renderer is already initialized
     * 
     * @since 1.0.0
     */
    public void init() {
        if (this.initialized) {
            throw new RuntimeException("The renderer is already initialized");
        }

        this.initialized = true;

        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Render a frame to a window (Must be called from a {@link Window})
     * 
     * @since 1.0.0
     */
    public void render(@NotNull long windowId) {
        glClear(GL_COLOR_BUFFER_BIT);

        if (!this.shader.getInitialized()) {
            this.shader.init();
        }

        this.shader.bind();

        Matrix4f cameraProjection = camera.getProjection();

        for (GameObject object : this.scene.getObjects()) {
            Mesh mesh = object.getComponent(Mesh.class);
            if (mesh != null) {
                if (!mesh.getInitialized()) {
                    mesh.init();
                }

                Texture2D texture = mesh.getTexture();
                if (texture != null) {
                    if (!texture.getInitialized()) {
                        texture.init();
                    }

                    texture.bind(this.shader);
                } else {
                    // TODO Draw in white
                }

                shader.setUniform("projection", cameraProjection.mul(object.getComponent(Transform.class).getMatrix()));

                mesh.render(camera);
            }
        }

        glfwSwapBuffers(windowId);
    }
}