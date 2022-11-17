package io.github.kale_ko.gighm.input;

import java.util.ArrayList;
import java.util.List;
import io.github.kale_ko.gighm.events.EventManager;
import io.github.kale_ko.gighm.events.types.input.KeyEvent;
import io.github.kale_ko.gighm.events.types.input.MouseButtonEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveDeltaEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveEvent;
import io.github.kale_ko.gighm.events.types.input.MouseScrollEvent;
import io.github.kale_ko.gighm.events.types.rendering.RenderEvent;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * An input manager for getting user input
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
 * @since 1.2.0
 */
public class InputManager {
    /**
     * The event manager to listen to
     * 
     * @since 1.7.0
     */
    protected @NotNull EventManager eventManager;

    /**
     * The list of keys currently down
     * 
     * @since 1.2.0
     */
    private @NotNull List<KeyCode> keysDown = new ArrayList<KeyCode>();

    /**
     * The list of mouse buttons currently down
     * 
     * @since 1.2.0
     */
    private @NotNull List<MouseButton> buttonsDown = new ArrayList<MouseButton>();

    /**
     * The current mouse x
     * 
     * @since 1.2.0
     */
    private @NotNull Integer mouseX = -1;

    /**
     * The current mouse y
     * 
     * @since 1.2.0
     */
    private @NotNull Integer mouseY = -1;

    /**
     * The distance the mouse has moved in the last frame on the x
     * 
     * @since 1.2.0
     */
    private @NotNull Integer mouseDeltaX = 0;

    /**
     * The distance the mouse has moved in the last frame on the y
     * 
     * @since 1.2.0
     */
    private @NotNull Integer mouseDeltaY = 0;

    /**
     * The distance the scroll wheel was turned in the last frame on the x
     * 
     * @since 1.2.0
     */
    private @NotNull Integer mouseDeltaScrollX = 0;

    /**
     * The distance the scroll wheel was turned in the last frame on the y
     * 
     * @since 1.2.0
     */
    private @NotNull Integer mouseDeltaScrollY = 0;

    /**
     * Weather the delta is auto reset
     * 
     * @since 1.5.0
     */
    private @NotNull Boolean autoResetDelta = true;

    /**
     * Create an input manager
     * 
     * @param eventManager The event manager to listen to
     * 
     * @since 1.7.0
     */
    public InputManager(@NotNull EventManager eventManager) {
        NullUtils.checkNulls(eventManager, "eventManager");

        this.eventManager = eventManager;

        this.eventManager.addEventListener(KeyEvent.class, (event) -> {
            if (event.getAction() == KeyAction.DOWN) {
                if (!keysDown.contains(event.getCode())) {
                    keysDown.add(event.getCode());
                }
            } else if (event.getAction() == KeyAction.UP) {
                if (keysDown.contains(event.getCode())) {
                    keysDown.remove(event.getCode());
                }
            }
        });

        this.eventManager.addEventListener(MouseButtonEvent.class, (event) -> {
            if (event.getAction() == MouseButtonAction.DOWN) {
                if (!buttonsDown.contains(event.getButton())) {
                    buttonsDown.add(event.getButton());
                }
            } else if (event.getAction() == MouseButtonAction.UP) {
                if (buttonsDown.contains(event.getButton())) {
                    buttonsDown.remove(event.getButton());
                }
            }
        });

        this.eventManager.addEventListener(MouseMoveEvent.class, (event) -> {
            if (this.mouseX != -1 && this.mouseY != -1) {
                this.mouseDeltaX += event.getX() - this.mouseX;
                this.mouseDeltaY += event.getY() - this.mouseY;
            }

            this.mouseX = event.getX();
            this.mouseY = event.getY();
        });

        this.eventManager.addEventListener(MouseScrollEvent.class, (event) -> {
            this.mouseDeltaScrollX -= event.getX();
            this.mouseDeltaScrollY -= event.getY();
        });

        this.eventManager.addEventListener(RenderEvent.class, (event) -> {
            if (this.getAutoResetDelta()) {
                this.resetDelta();
            }
        });
    }

    /**
     * Get weather a key is currently pressed down
     * 
     * @param key The key to check
     * 
     * @return Weather a key is currently pressed down
     * 
     * @since 1.2.0
     */
    public @NotNull Boolean getKeyDown(@NotNull KeyCode key) {
        NullUtils.checkNulls(key, "key");

        return this.keysDown.contains(key);
    }

    /**
     * Get weather a mouse button is currently pressed down
     * 
     * @param button The mouse button to check
     * 
     * @return Weather a mouse button is currently pressed down
     * 
     * @since 1.2.0
     */
    public @NotNull Boolean getButtonDown(@NotNull MouseButton button) {
        NullUtils.checkNulls(button, "button");

        return this.buttonsDown.contains(button);
    }

    /**
     * Get the current mouse x
     * 
     * @return The current mouse x
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getMouseX() {
        return this.mouseX;
    }

    /**
     * Get the current mouse y
     * 
     * @return The current mouse y
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getMouseY() {
        return this.mouseY;
    }

    /**
     * Get the distance the mouse has moved in the last frame on the x
     * 
     * @return The distance the mouse has moved in the last frame on the x
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getMouseDeltaX() {
        return this.mouseDeltaX;
    }

    /**
     * Get the distance the mouse has moved in the last frame on the y
     * 
     * @return The distance the mouse has moved in the last frame on the y
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getMouseDeltaY() {
        return this.mouseDeltaY;
    }

    /**
     * Get the distance the scroll wheel was turned in the last frame on the x
     * 
     * @return The distance the scroll wheel was turned in the last frame on the x
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getScrollDeltaX() {
        return this.mouseDeltaScrollX;
    }

    /**
     * Get the distance the scroll wheel was turned in the last frame on the y
     * 
     * @return The distance the scroll wheel was turned in the last frame on the y
     * 
     * @since 1.2.0
     */
    public @NotNull Integer getScrollDeltaY() {
        return this.mouseDeltaScrollY;
    }

    /**
     * Get weather the delta is auto reset
     * 
     * @return Weather the delta is auto reset
     * 
     * @since 1.5.0
     */
    public @NotNull Boolean getAutoResetDelta() {
        return this.autoResetDelta;
    }

    /**
     * Set weather the delta is auto reset
     * (Note if you set this to false you must call {@link #resetDelta} yourself each update)
     * 
     * @param flag Weather the delta is auto reset
     * 
     * @since 1.5.0
     */
    public void setAutoResetDelta(@NotNull Boolean flag) {
        NullUtils.checkNulls(flag, "flag");

        this.autoResetDelta = flag;
    }

    /**
     * Reset the delta variables
     * 
     * @since 1.2.0
     */
    public void resetDelta() {
        this.eventManager.emit(new MouseMoveDeltaEvent(this.mouseX, this.mouseY, this.mouseDeltaX, this.mouseDeltaY));

        this.mouseDeltaX = 0;
        this.mouseDeltaY = 0;

        this.mouseDeltaScrollX = 0;
        this.mouseDeltaScrollY = 0;
    }
}