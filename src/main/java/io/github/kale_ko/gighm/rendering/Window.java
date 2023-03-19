package io.github.kale_ko.gighm.rendering;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33C.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import java.nio.IntBuffer;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.joml.Vector2f;
import org.joml.Vector2i;
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
import io.github.kale_ko.gighm.exception.InvalidDataException;
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
     * Weather the window should auto show
     * 
     * @since 2.5.0
     */
    private @NotNull Boolean autoShow;

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
        this(renderer, title, width, height, maximized, resizable, true);
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
     * @param autoShow Weather the window should automatically show
     * 
     * @throws ThreadPauseException If the main thread can't be paused
     * 
     * @since 1.7.0
     */
    public Window(@NotNull Renderer renderer, @NotNull String title, @NotNull Integer width, @NotNull Integer height, @NotNull Boolean maximized, @NotNull Boolean resizable, @NotNull Boolean autoShow) throws ThreadPauseException {
        NullUtils.checkNulls(renderer, "renderer");
        NullUtils.checkNulls(title, "title");
        NullUtils.checkNulls(width, "width");
        NullUtils.checkNulls(height, "height");
        NullUtils.checkNulls(maximized, "maximized");
        NullUtils.checkNulls(resizable, "resizable");
        NullUtils.checkNulls(autoShow, "autoShow");

        this.renderer = renderer;

        this.title = title;
        this.width = width;
        this.height = height;

        if (this.width < 640 || this.height < 480) {
            throw new InvalidDataException("Width or height is too small. Must be greater than (640, 480)");
        }

        this.maximized = maximized;
        this.resizable = resizable;
        this.autoShow = autoShow;

        this.mainThread = Thread.currentThread();

        this.thread = new Thread(new Runnable() {
            @Override
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

        windowId = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowId.equals(NULL)) {
            throw new GLInitializeException("Failed to initialize the GLFW window");
        }

        this.thread.setName("GIGHM-" + windowId);

        synchronized (this.mainThread) {
            this.mainThread.notify();
        }

        glfwSetKeyCallback(windowId, (window, key, scanCode, action, mods) -> {
            if (glfwGetWindowAttrib(windowId, GLFW_FOCUSED) == GLFW_TRUE) {
                this.eventManager.emit(new KeyEvent(KeyCode.valueOfGLFWKey(key, KeyMod.isPressed(KeyMod.SHIFT, mods)), KeyAction.valueOfGLFWEvent(action), KeyMod.getPressed(mods)));
            }
        });

        glfwSetMouseButtonCallback(windowId, (window, button, action, mods) -> {
            if (glfwGetWindowAttrib(windowId, GLFW_FOCUSED) == GLFW_TRUE) {
                this.eventManager.emit(new MouseButtonEvent(MouseButton.valueOfGLFWButton(button), MouseButtonAction.valueOfGLFWEvent(action), KeyMod.getPressed(mods)));
            }
        });

        glfwSetCursorPosCallback(windowId, (window, x, y) -> {
            if (glfwGetWindowAttrib(windowId, GLFW_FOCUSED) == GLFW_TRUE) {
                if (x >= 0 && x <= this.width && y >= 0 && y <= this.height) {
                    this.eventManager.emit(new MouseMoveEvent((int) x, (int) y));
                }
            }
        });

        glfwSetScrollCallback(windowId, (window, x, y) -> {
            if (glfwGetWindowAttrib(windowId, GLFW_FOCUSED) == GLFW_TRUE) {
                this.eventManager.emit(new MouseScrollEvent((int) x, (int) y));
            }
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

            glViewport(0, 0, this.width, this.height);

            renderer.render();

            glfwSwapBuffers(windowId);
        });

        glfwSetWindowMaximizeCallback(windowId, (window, maximized) -> {
            this.maximized = maximized;
        });

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(new Runnable() {
            private Integer tickNumber = 1;

            @Override
            public void run() {
                eventManager.emit(new TickEvent(tickNumber));

                for (GameObject object : renderer.getScene().getObjects()) {
                    for (Component component : object.getComponents()) {
                        component.tick(tickNumber);
                    }
                }

                tickNumber++;
            }
        }, 1, 40, TimeUnit.MILLISECONDS);

        glfwSetWindowSizeLimits(windowId, 640, 480, GLFW_DONT_CARE, GLFW_DONT_CARE);
        glfwSetWindowAspectRatio(windowId, this.width, this.height);

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

        if (this.autoShow) {
            glfwShowWindow(windowId);
            glfwFocusWindow(windowId);
        }

        renderer.init();

        Instant lastRender = Instant.now();
        while (!glfwWindowShouldClose(windowId)) {
            glViewport(0, 0, this.width, this.height);

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
     * Set the current position of the window
     * 
     * @param position The new position of the window
     * 
     * @since 2.2.0
     */
    public void setPosition(@NotNull Vector2i position) {
        NullUtils.checkNulls(position, "position");

        glfwSetWindowPos(this.windowId, position.x, position.y);
    }

    /**
     * Set the current position of the window
     * 
     * @param x The new x of the window
     * @param y The new y of the window
     * 
     * @since 2.2.0
     */
    public void setPosition(@NotNull Integer x, @NotNull Integer y) {
        this.setPosition(new Vector2i(x, y));
    }

    /**
     * Get the current size of the window
     * 
     * @return The current size of the window
     * 
     * @since 2.2.0
     */
    public @NotNull Vector2i getSize() {
        return new Vector2i(this.width, this.height);
    }

    /**
     * Set the current size of the window
     * 
     * @param size The new size of the window
     * 
     * @since 2.2.0
     */
    public void setSize(@NotNull Vector2i size) {
        NullUtils.checkNulls(size, "size");

        this.width = size.x;
        this.height = size.y;

        glfwSetWindowSize(this.windowId, size.x, size.y);
        glfwSetWindowAspectRatio(windowId, size.x, size.y);
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
        this.setSize(new Vector2i(width, height));
    }

    /**
     * Get the current aspect ratio of the window
     * 
     * @return The current aspect ratio of the window
     * 
     * @since 2.2.0
     */
    public @NotNull Vector2f getAspect() {
        Float aspectX = ((float) this.width) / ((float) this.height);
        Float aspectY = ((float) this.height) / ((float) this.width);

        if (aspectX < aspectY) {
            aspectY *= 1 / aspectX;
            aspectX *= 1 / aspectX;
        } else {
            aspectY *= 1 / aspectY;
            aspectX *= 1 / aspectY;
        }

        return new Vector2f(aspectX, aspectY);
    }

    /**
     * Set the current aspect ratio of the window
     * 
     * @param aspect The new aspect of the window
     * 
     * @since 2.2.0
     */
    public void setAspect(@NotNull Vector2i aspect) {
        NullUtils.checkNulls(aspect, "aspect");

        glfwSetWindowAspectRatio(windowId, aspect.x, aspect.y);
    }

    /**
     * Set the current aspect ratio of the window
     * 
     * @param aspectX The new aspect of the window
     * @param aspectY The new aspect of the window
     * 
     * @since 2.2.0
     */
    public void setAspect(@NotNull Integer aspectX, @NotNull Integer aspectY) {
        this.setAspect(new Vector2i(aspectX, aspectY));
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
     * Show the window
     * 
     * @since 2.5.0
     */
    public void show() {
        glfwShowWindow(windowId);
    }

    /**
     * Hide the window
     * 
     * @since 2.5.0
     */
    public void hide() {
        glfwHideWindow(windowId);
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