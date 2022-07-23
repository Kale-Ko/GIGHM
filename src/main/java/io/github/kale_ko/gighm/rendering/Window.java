package io.github.kale_ko.gighm.rendering;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import java.nio.IntBuffer;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import io.github.kale_ko.gighm.events.EventManager;
import io.github.kale_ko.gighm.events.types.input.KeyEvent;
import io.github.kale_ko.gighm.events.types.input.MouseButtonEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveEvent;
import io.github.kale_ko.gighm.events.types.input.MouseScrollEvent;
import io.github.kale_ko.gighm.events.types.rendering.RenderEvent;
import io.github.kale_ko.gighm.events.types.rendering.TickEvent;
import io.github.kale_ko.gighm.exception.AlreadyInitializedException;
import io.github.kale_ko.gighm.exception.GLInitializeException;
import io.github.kale_ko.gighm.exception.ThreadPauseException;
import io.github.kale_ko.gighm.input.InputManager;
import io.github.kale_ko.gighm.input.KeyAction;
import io.github.kale_ko.gighm.input.KeyCode;
import io.github.kale_ko.gighm.input.KeyMod;
import io.github.kale_ko.gighm.input.MouseButton;
import io.github.kale_ko.gighm.input.MouseButtonAction;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.scene.components.Component;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * A window for rendering to and managing input
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
 * @since 1.0.0
 */
public class Window {
    /**
     * The renderer being used by the window
     * 
     * @since 1.0.0
     */
    private @NotNull Renderer renderer;

    /**
     * The event manager used for listening to and broadcasting events
     * 
     * @since 1.6.0
     */
    private @NotNull EventManager eventManager = new EventManager();

    /**
     * The input manager used for getting user input
     * 
     * @since 1.2.0
     */
    private @NotNull InputManager inputManager = new InputManager(eventManager);

    /**
     * The title of the window
     * 
     * @since 1.0.0
     */
    private @NotNull String title;

    /**
     * The icon of the window
     * 
     * @since 2.1.0
     */
    private @Nullable Texture2D icon;

    /**
     * The width of the window
     * 
     * @since 1.0.0
     */
    private @NotNull Integer width;

    /**
     * The height of the window
     * 
     * @since 1.0.0
     */
    private @NotNull Integer height;

    /**
     * Weather the window is maximized
     * 
     * @since 1.0.0
     */
    private @NotNull Boolean maximized;

    /**
     * Weather the window is resizable
     * 
     * @since 1.0.0
     */
    private @NotNull Boolean resizable;

    /**
     * Weather the window is initialized
     * 
     * @since 1.0.0
     */
    private @NotNull Boolean initialized = false;

    /**
     * The glfw id of the window
     * 
     * @since 1.0.0
     */
    private @Nullable Long windowId;

    /**
     * The thread this window is running on
     * 
     * @since 1.7.0
     */
    private @Nullable Thread thread;

    /**
     * The thread that created this window
     * 
     * @since 1.8.0
     */
    private @Nullable Thread mainThread;

    /**
     * Create a window
     * 
     * @param renderer The renderer being used by the window
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     * 
     * @throws ThreadPauseException If the main thread can't be paused
     * 
     * @since 1.0.0
     */
    public Window(@NotNull Renderer renderer, @NotNull String title, @NotNull Integer width, @NotNull Integer height) throws ThreadPauseException {
        this(renderer, title, width, height, false, true);
    }

    /**
     * Create a window
     * 
     * @param renderer The renderer being used by the window
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     * @param maximized Weather the window should be maximized
     * @param resizable Weather the window should be resizable
     * 
     * @throws ThreadPauseException If the main thread can't be paused
     * 
     * @since 1.7.0
     */
    public Window(@NotNull Renderer renderer, @NotNull String title, @NotNull Integer width, @NotNull Integer height, @NotNull Boolean maximized, @NotNull Boolean resizable) throws ThreadPauseException {
        NullUtils.checkNulls(renderer, "renderer");
        NullUtils.checkNulls(title, "title");
        NullUtils.checkNulls(width, "width");
        NullUtils.checkNulls(height, "height");
        NullUtils.checkNulls(maximized, "maximized");
        NullUtils.checkNulls(resizable, "resizable");

        this.renderer = renderer;

        this.title = title;
        this.width = width;
        this.height = height;

        this.maximized = maximized;
        this.resizable = resizable;

        this.mainThread = Thread.currentThread();

        this.thread = new Thread(new Runnable() {
            public void run() {
                init();
            }
        });

        this.thread.start();

        try {
            synchronized (this.mainThread) {
                this.mainThread.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

            throw new ThreadPauseException("The main thread could not be properly paused");
        }
    }

    /**
     * Initialize the window (Must be called from a GIGHM thread)
     * 
     * @throws AlreadyInitializedException If the window is already initialized
     * @throws GLInitializeException If glfw fails to initialize or create the window
     * 
     * @since 1.0.0
     */
    private void init() throws AlreadyInitializedException, GLInitializeException {
        if (this.initialized) {
            throw new AlreadyInitializedException("The window is already initialized");
        }

        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        if (!glfwInit()) {
            throw new GLInitializeException("Failed to initialize OpenGl and GLFW");
        }

        this.initialized = true;

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_FOCUSED, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, maximized ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_FOCUS_ON_SHOW, GLFW_TRUE);

        windowId = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowId.equals(NULL)) {
            throw new GLInitializeException("Failed to initialize the GLFW window");
        }

        this.thread.setName("GIGHM-" + windowId);

        synchronized (this.mainThread) {
            this.mainThread.notify();
        }

        glfwSetKeyCallback(windowId, (window, key, scanCode, action, mods) -> {
            this.eventManager.emit(new KeyEvent(KeyCode.valueOfGLFWKey(key, KeyMod.isPressed(KeyMod.SHIFT, mods)), KeyAction.valueOfGLFWEvent(action), KeyMod.getPressed(mods)));
        });

        glfwSetMouseButtonCallback(windowId, (window, button, action, mods) -> {
            this.eventManager.emit(new MouseButtonEvent(MouseButton.valueOfGLFWButton(button), MouseButtonAction.valueOfGLFWEvent(action), KeyMod.getPressed(mods)));
        });

        glfwSetCursorPosCallback(windowId, (window, x, y) -> {
            this.eventManager.emit(new MouseMoveEvent((int) x, (int) y));
        });

        glfwSetScrollCallback(windowId, (window, x, y) -> {
            this.eventManager.emit(new MouseScrollEvent((int) x, (int) y));
        });

        glfwSetWindowSizeCallback(windowId, (window, newWidth, newHeight) -> {
            if (newWidth == 0 || newHeight == 0) {
                glfwSetWindowShouldClose(windowId, true);
            }

            this.width = newWidth;
            this.height = newHeight;

            renderer.getCamera().setWidth(this.width);
            renderer.getCamera().setHeight(this.height);
            renderer.getCamera().setAspect((float) (this.width / this.height));

            renderer.getCamera().recalculateProjection();
        });

        glfwSetWindowMaximizeCallback(windowId, (window, maximized) -> {
            this.maximized = maximized;
        });

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                eventManager.emit(new TickEvent());

                for (GameObject object : renderer.getScene().getObjects()) {
                    for (Component component : object.getComponents()) {
                        component.tick();
                    }
                }
            }
        }, 1, 20, TimeUnit.MILLISECONDS);

        if (!this.maximized) {
            MemoryStack stack = stackPush();
            IntBuffer cWidth = stack.mallocInt(1);
            IntBuffer cHeight = stack.mallocInt(1);
            glfwGetWindowSize(windowId, cWidth, cHeight);

            GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(windowId, (vid.width() - cWidth.get(0)) / 2, (vid.height() - cHeight.get(0)) / 2);
        }

        glfwMakeContextCurrent(windowId);
        glfwSwapInterval(1);

        glfwShowWindow(windowId);

        renderer.init();

        Instant lastRender = Instant.now();
        while (!glfwWindowShouldClose(windowId)) {
            renderer.render();

            glfwSwapBuffers(windowId);

            Instant now = Instant.now();
            Float delta = (float) ((now.getEpochSecond() + ((double) now.getNano() / 1000000000)) - (lastRender.getEpochSecond() + ((double) lastRender.getNano() / 1000000000)));

            for (GameObject object : renderer.getScene().getObjects()) {
                for (Component component : object.getComponents()) {
                    component.render(delta);
                }
            }

            this.eventManager.emit(new RenderEvent(delta));

            lastRender = now;

            glfwPollEvents();
        }

        handle.cancel(true);
        scheduler.shutdown();

        glfwFreeCallbacks(windowId);
        glfwDestroyWindow(windowId);

        glfwTerminate();
    }

    /**
     * Get the renderer being used by the window
     * 
     * @return The renderer being used by the window
     * 
     * @since 1.5.0
     */
    public @NotNull Renderer getRenderer() {
        return this.renderer;
    }

    /**
     * Set the renderer being used by the window
     * 
     * @param renderer The renderer being used by the window
     * 
     * @since 1.5.0
     */
    public void setRenderer(@NotNull Renderer renderer) {
        NullUtils.checkNulls(renderer, "renderer");

        this.renderer = renderer;
    }

    /**
     * Get the event manager used for listening to and broadcasting events
     * 
     * @return The event manager used for listening to and broadcasting events
     * 
     * @since 1.6.0
     */
    public @NotNull EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Get the input manager used for getting user input
     * 
     * @return The input manager used for getting user input
     * 
     * @since 1.5.0
     */
    public @NotNull InputManager getInputManager() {
        return this.inputManager;
    }

    /**
     * Get the title of the window
     * 
     * @return The title of the window
     * 
     * @since 1.0.0
     */
    public @NotNull String getTitle() {
        return this.title;
    }

    /**
     * Set the title of the window
     * 
     * @param title The title of the window
     * 
     * @since 1.0.0
     */
    public void setTitle(@NotNull String title) {
        NullUtils.checkNulls(title, "title");

        this.title = title;

        glfwSetWindowTitle(windowId, title);
    }

    /**
     * Get the icon of the window
     * 
     * @return The icon of the window
     * 
     * @since 2.1.0
     */
    public @Nullable Texture2D getIcon() {
        return this.icon;
    }

    /**
     * Set the icon of the window
     * 
     * @param icon The icon of the window
     * 
     * @since 2.0.0
     */
    public void setIcon(@NotNull Texture2D icon) {
        NullUtils.checkNulls(icon, "icon");

        this.icon = icon;

        GLFWImage image = GLFWImage.malloc();
        image.set(icon.getWidth(), icon.getHeight(), icon.getRawData());
        GLFWImage.Buffer images = GLFWImage.malloc(1);
        images.put(0, image);

        glfwSetWindowIcon(windowId, images);
    }

    /**
     * Get the current width of the window
     * 
     * @return The current width of the window
     * 
     * @since 1.0.0
     */
    public @NotNull Integer getWidth() {
        return this.width;
    }

    /**
     * Get the current height of the window
     * 
     * @return The current height of the window
     * 
     * @since 1.0.0
     */
    public @NotNull Integer getHeight() {
        return this.height;
    }

    /**
     * Set the current size of the window
     * 
     * @param width The new width of the window
     * @param height The new height of the window
     * 
     * @since 1.0.0
     */
    public void setSize(@NotNull Integer width, @NotNull Integer height) {
        NullUtils.checkNulls(width, "width");
        NullUtils.checkNulls(height, "height");

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
    public @NotNull Boolean getMaximized() {
        return this.maximized;
    }

    /**
     * Set weather the window is maximized
     * 
     * @param maximized Weather the window is maximized
     * 
     * @since 1.8.0
     */
    public void setMaximized(@NotNull Boolean maximized) {
        NullUtils.checkNulls(maximized, "maximized");

        this.maximized = maximized;

        glfwSetWindowAttrib(windowId, GLFW_MAXIMIZED, maximized ? GLFW_TRUE : GLFW_FALSE);
    }

    /**
     * Get weather the window is resizable
     * 
     * @return Weather the window is resizable
     * 
     * @since 1.0.0
     */
    public @NotNull Boolean getResizable() {
        return this.resizable;
    }

    /**
     * Set weather the window is resizable
     * 
     * @param resizable Weather the window is resizable
     * 
     * @since 1.8.0
     */
    public void setResizable(@NotNull Boolean resizable) {
        NullUtils.checkNulls(resizable, "resizable");

        this.resizable = resizable;

        glfwSetWindowAttrib(windowId, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
    }

    /**
     * Get weather the window is initialized
     * 
     * @return Weather the window is initialized
     * 
     * @since 1.0.0
     */
    public @NotNull Boolean getInitialized() {
        return this.initialized;
    }

    /**
     * Get the id of the window
     * 
     * @return The id of the window
     * 
     * @since 1.0.0
     */
    public @Nullable Long getWindowId() {
        return this.windowId;
    }
}