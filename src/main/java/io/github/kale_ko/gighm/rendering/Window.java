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
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import io.github.kale_ko.gighm.events.EventManager;
import io.github.kale_ko.gighm.events.types.input.KeyEvent;
import io.github.kale_ko.gighm.events.types.input.MouseButtonEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveEvent;
import io.github.kale_ko.gighm.events.types.input.MouseScrollEvent;
import io.github.kale_ko.gighm.events.types.rendering.RenderEvent;
import io.github.kale_ko.gighm.events.types.rendering.TickEvent;
import io.github.kale_ko.gighm.input.InputManager;
import io.github.kale_ko.gighm.input.KeyAction;
import io.github.kale_ko.gighm.input.KeyCode;
import io.github.kale_ko.gighm.input.KeyMod;
import io.github.kale_ko.gighm.input.MouseButton;
import io.github.kale_ko.gighm.input.MouseButtonAction;
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
 * @version 1.8.0
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
     * @since 1.0.0
     */
    public Window(@NotNull Renderer renderer, @NotNull String title, @NotNull Integer width, @NotNull Integer height) {
        this(renderer, title, width, height, false, true);
    }

    /**
     * Create a window
     * 
     * @param renderer The renderer being used by the window
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     * @param maximized Weather the window should be maxamized
     * @param resizable Weather the window should be resizable
     * 
     * @throws RuntimeException If the main thread can't be paused
     * 
     * @since 1.7.0
     */
    public Window(@NotNull Renderer renderer, @NotNull String title, @NotNull Integer width, @NotNull Integer height, @NotNull Boolean maximized, @NotNull Boolean resizable) {
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

            throw new RuntimeException("The main thread could not be properly paused");
        }
    }

    /**
     * Initialize the window (Must be called from a GIGHM thread)
     * 
     * @throws RuntimeException If the window is already initialized, the method is not called from a GIGHM thread, or glfw fails to create the window
     * 
     * @since 1.0.0
     */
    private void init() {
        if (this.initialized) {
            throw new RuntimeException("The window is already initialized");
        }

        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize OpenGl and GLFW");
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
            throw new RuntimeException("Failed to initialize the GLFW window");
        }

        this.thread.setName("GIGHM-" + windowId);

        synchronized (this.mainThread) {
            this.mainThread.notify();
        }

        glfwSetKeyCallback(windowId, (window, key, scancode, action, mods) -> {
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

        glfwSetWindowSizeCallback(windowId, (window, newwidth, newheight) -> {
            this.width = newwidth;
            this.height = newheight;

            renderer.getCamera().setWidth((float) this.width);
            renderer.getCamera().setHeight((float) this.height);
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

        MemoryStack stack = stackPush();
        IntBuffer cWidth = stack.mallocInt(1);
        IntBuffer cHeight = stack.mallocInt(1);
        glfwGetWindowSize(windowId, cWidth, cHeight);

        GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowId, (vid.width() - cWidth.get(0)) / 2, (vid.height() - cHeight.get(0)) / 2);

        glfwMakeContextCurrent(windowId);
        glfwSwapInterval(1);

        glfwShowWindow(windowId);

        renderer.init();

        Instant lastRender = Instant.now();
        while (!glfwWindowShouldClose(windowId)) {
            renderer.render();

            Instant now = Instant.now();
            Float delta = (float) ((now.getEpochSecond() + ((double) now.getNano() / 1000000000)) - (lastRender.getEpochSecond() + ((double) lastRender.getNano() / 1000000000)));

            this.eventManager.emit(new RenderEvent(delta));

            for (GameObject object : renderer.getScene().getObjects()) {
                for (Component component : object.getComponents()) {
                    component.render(delta);
                }
            }

            lastRender = now;

            glfwSwapBuffers(windowId);

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
    public Renderer getRenderer() {
        return this.renderer;
    }

    /**
     * Set the renderer being used by the window
     * 
     * @param renderer The renderer being used by the window
     * 
     * @since 1.5.0
     */
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Get the event manager used for listening to and broadcasting events
     * 
     * @return The event manager used for listening to and broadcasting events
     * 
     * @since 1.6.0
     */
    public EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Set the event manager used for listening to and broadcasting events
     * 
     * @param eventManager The event manager used for listening to and broadcasting events
     * 
     * @since 1.6.0
     */
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Get the input manager used for getting user input
     * 
     * @return The input manager used for getting user input
     * 
     * @since 1.5.0
     */
    public InputManager getInputManager() {
        return this.inputManager;
    }

    /**
     * Set the input manager used for getting user input
     * 
     * @param inputManager The input manager used for getting user input
     * 
     * @since 1.5.0
     */
    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    /**
     * Get the title of the window
     * 
     * @return The title of the window
     * 
     * @since 1.0.0
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title of the window
     * 
     * @param title The title of the window
     * 
     * @since 1.0.0
     */
    public void setTitle(String title) {
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
    public Integer getWidth() {
        return this.width;
    }

    /**
     * Get the current height of the window
     * 
     * @return The current height of the window
     * 
     * @since 1.0.0
     */
    public Integer getHeight() {
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
    public void setSize(Integer width, Integer height) {
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
    public Boolean getMaximized() {
        return this.maximized;
    }

    /**
     * Set weather the window is maximized
     * 
     * @param maximized Weather the window is maximized
     * 
     * @since 1.8.0
     */
    public void setMaximized(Boolean maximized) {
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
    public Boolean getResizable() {
        return this.resizable;
    }

    /**
     * Set weather the window is resizable
     * 
     * @param resizable Weather the window is resizable
     * 
     * @since 1.8.0
     */
    public void setResizable(Boolean resizable) {
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
    public Boolean getInitialized() {
        return this.initialized;
    }

    /**
     * Get the id of the window
     * 
     * @return The id of the window
     * 
     * @since 1.0.0
     */
    public Long getWindowId() {
        return this.windowId;
    }
}