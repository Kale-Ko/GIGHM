package io.github.kale_ko.gighm.rendering;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import java.nio.IntBuffer;
import javax.validation.constraints.NotNull;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

/**
 * A window to render to
 * 
 * @since 1.0.0
 */
public class Window {
    /**
     * The renderer being used by the window
     * 
     * @since 1.0.0
     */
    private Renderer renderer;

    /**
     * The title of the window
     * 
     * @since 1.0.0
     */
    private @NotNull String title;

    /**
     * The width of the window (Note this may not be accurate if the user can resize the window, use {@link #getWidth})
     * 
     * @since 1.0.0
     */
    private @NotNull int width;

    /**
     * The height of the window (Note this may not be accurate if the user can resize the window, use {@link #getHeight})
     * 
     * @since 1.0.0
     */
    private @NotNull int height;

    /**
     * Weather the window is maximized (Note this may not be accurate if the user can resize the window, use {@link #getMaximized})
     * 
     * @since 1.0.0
     */
    private @NotNull boolean maximized;

    /**
     * Weather the window is resizable
     * 
     * @since 1.0.0
     */
    private @NotNull boolean resizable;

    /**
     * Weather the window is initialized
     * 
     * @since 1.0.0
     */
    private @NotNull boolean initialized = false;

    /**
     * The id of the window
     * 
     * @since 1.0.0
     */
    private long windowId;

    /**
     * Create a window to render to
     * 
     * @param title The title of the new window
     * 
     * @since 1.0.0
     */
    public Window(@NotNull String title) {
        this(null, title);
    }

    /**
     * Create a window to render to
     * 
     * @param renderer The renderer to use when rendering the window
     * @param title The title of the new window
     * 
     * @since 1.0.0
     */
    public Window(Renderer renderer, @NotNull String title) {
        this(renderer, title, 640, 360);
    }

    /**
     * Create a window to render to
     * 
     * @param title The title of the new window
     * @param width The width of the new window
     * @param height The height of the new window
     * 
     * @since 1.0.0
     */
    public Window(@NotNull String title, @NotNull int width, @NotNull int height) {
        this(null, title, width, height);
    }

    /**
     * Create a window to render to
     * 
     * @param renderer The renderer to use when rendering the window
     * @param title The title of the new window
     * @param width The width of the new window
     * @param height The height of the new window
     * 
     * @since 1.0.0
     */
    public Window(Renderer renderer, @NotNull String title, @NotNull int width, @NotNull int height) {
        this(renderer, title, width, height, false, false);
    }

    /**
     * Create a window to render to
     * 
     * @param title The title of the new window
     * @param width The width of the new window
     * @param height The height of the new window
     * @param maximized Weather the new window should be maxamized
     * @param resizable Weather the new window should be resizable
     * 
     * @since 1.0.0
     */
    public Window(@NotNull String title, @NotNull int width, @NotNull int height, @NotNull boolean maximized, @NotNull boolean resizable) {
        this(null, title, width, height, maximized, resizable);
    }

    /**
     * Create a window to render to
     * 
     * @param renderer The renderer to use when rendering the window
     * @param title The title of the new window
     * @param width The width of the new window
     * @param height The height of the new window
     * @param maximized Weather the new window should be maxamized
     * @param resizable Weather the new window should be resizable
     * 
     * @since 1.0.0
     */
    public Window(Renderer renderer, @NotNull String title, @NotNull int width, @NotNull int height, @NotNull boolean maximized, @NotNull boolean resizable) {
        this.renderer = renderer;

        this.title = title;
        this.width = width;
        this.height = height;

        this.maximized = maximized;
        this.resizable = resizable;

        this.init();
    }

    /**
     * Initialize the window
     * 
     * @throws RuntimeException  If the window is already initialized or glfw fails to start/create the window
     * 
     * @since 1.0.0
     */
    public void init() {
        if (this.initialized) {
            throw new RuntimeException("The window is already initialized");
        }

        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize GLFW");
        }

        this.initialized = true;

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_FOCUSED, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, maximized ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_FOCUS_ON_SHOW, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);

        windowId = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowId == NULL) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        // TODO Initialize keyboard/mouse handlers etc

        MemoryStack stack = stackPush();
        IntBuffer cWidth = stack.mallocInt(1);
        IntBuffer cHeight = stack.mallocInt(1);
        glfwGetWindowSize(windowId, cWidth, cHeight);

        GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowId, (vid.width() - cWidth.get(0)) / 2, (vid.height() - cHeight.get(0)) / 2);

        glfwMakeContextCurrent(windowId);
        glfwSwapInterval(1);

        glfwShowWindow(windowId);

        if (renderer != null) {
            renderer.init();
        }

        while (!glfwWindowShouldClose(windowId)) {
            if (renderer != null) {
                renderer.render(windowId);
            }

            glfwPollEvents();
        }

        glfwFreeCallbacks(windowId);
        glfwDestroyWindow(windowId);

        glfwTerminate();
    }

    /**
     * Get the title of the window
     * 
     * @returns The title of the window
     * 
     * @since 1.0.0
     */
    public @NotNull String getTitle() {
        return this.title;
    }

    /**
     * Change the title of the window
     * 
     * @param title The new title of the window
     * 
     * @since 1.0.0
     */
    public void setTitle(@NotNull String title) {
        this.title = title;

        glfwSetWindowTitle(windowId, title);
    }

    /**
     * Get the current width of the window
     * 
     * @return The current width of the window
     * 
     * @since 1.0.0
     */
    public @NotNull int getWidth() {
        MemoryStack stack = stackPush();
        IntBuffer cWidth = stack.mallocInt(1);
        glfwGetWindowSize(windowId, cWidth, null);

        return cWidth.get(0);
    }

    /**
     * Get the current height of the window
     * 
     * @return The current height of the window
     * 
     * @since 1.0.0
     */
    public @NotNull int getHeight() {
        MemoryStack stack = stackPush();
        IntBuffer cHeight = stack.mallocInt(1);
        glfwGetWindowSize(windowId, null, cHeight);

        return cHeight.get(0);
    }

    /**
     * Change the current size of the window
     * 
     * @param width The new width of the window
     * @param height The new height of the window
     * 
     * @since 1.0.0
     */
    public void setWindowSize(@NotNull int width, @NotNull int height) {
        this.width = width;
        this.height = height;

        glfwSetWindowSize(windowId, width, height);
    }

    /**
     * Get weather the window is maximized
     * 
     * @return Weather the window is maximized
     * 
     * @since 1.0.0
     */
    public @NotNull boolean getMaximized() {
        return glfwGetWindowAttrib(windowId, GLFW_MAXIMIZED) == GLFW_TRUE;
    }

    /**
     * Get weather the window is resizable
     * 
     * @return Weather the window is resizable
     * 
     * @since 1.0.0
     */
    public @NotNull boolean getResizable() {
        return glfwGetWindowAttrib(windowId, GLFW_RESIZABLE) == GLFW_TRUE;
    }

    /**
     * Get weather the window is initialized
     * 
     * @return Weather the window is initialized
     * 
     * @since 1.0.0
     */
    public @NotNull boolean getInitialized() {
        return this.initialized;
    }

    /**
     * Get the id of the window
     * 
     * @return The id of the window
     * 
     * @since 1.0.0
     */
    public @NotNull long getWindowId() {
        return this.windowId;
    }
}