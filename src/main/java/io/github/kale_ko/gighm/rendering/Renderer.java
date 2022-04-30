package io.github.kale_ko.gighm.rendering;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL15.*;
import java.io.IOException;
import javax.validation.constraints.NotNull;
import org.lwjgl.opengl.GL;
import io.github.kale_ko.gighm.components.Mesh;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.rendering.textures.Texture2DLoader;

/**
 * A renderer to render a scene
 * 
 * @since 1.0.0
 */
public class Renderer {
    /**
     * Weather the window is initialized
     * 
     * @since 1.0.0
     */
    private @NotNull boolean initialized = false;

    /**
     * Create a renderer to render a scene
     * 
     * @since 1.0.0
     */
    public Renderer() {}

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

        try {
            Texture2D texture = Texture2DLoader.loadTexture("C:/Users/Kale Ko/Downloads/54416665.png");
            texture.init();
            glBindTexture(GL_TEXTURE_2D, texture.getTextureId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mesh mesh = new Mesh(new float[] { -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f }, 2, new float[] { 0, 0, 1, 0, 1, 1, 0, 1 }, 2, new int[] { 0, 1, 2, 2, 3, 0 });
        mesh.init();
        mesh.render();

        glfwSwapBuffers(windowId);
    }
}