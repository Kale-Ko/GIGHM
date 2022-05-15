package io.github.kale_ko.gighm.input;

import java.util.List;
import io.github.kale_ko.gighm.events.EventManager;
import io.github.kale_ko.gighm.events.types.input.KeyEvent;
import io.github.kale_ko.gighm.events.types.input.MouseButtonEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveEvent;
import io.github.kale_ko.gighm.events.types.input.MouseScrollEvent;
import io.github.kale_ko.gighm.events.types.rendering.RenderEvent;
import java.util.ArrayList;

/**
 * An input manager
 * 
 * @version 1.7.0
 * @since 1.2.0
 */
public class InputManager {
    /**
     * The event manager to listen to
     * 
     * @since 1.7.0
     */
    private EventManager eventManager;

    /**
     * The list of keys currently down
     * 
     * @since 1.2.0
     */
    private List<KeyCode> keysDown = new ArrayList<KeyCode>();

    /**
     * The list of buttons currently down
     * 
     * @since 1.2.0
     */
    private List<MouseButton> buttonsDown = new ArrayList<MouseButton>();

    /**
     * The current mouse x
     * 
     * @since 1.2.0
     */
    private int mouseX = -1;

    /**
     * The current mouse y
     * 
     * @since 1.2.0
     */
    private int mouseY = -1;

    /**
     * The distance the mouse has moved in the last frame on the x
     * 
     * @since 1.2.0
     */
    private int mouseDeltaX = 0;

    /**
     * The distance the mouse has moved in the last frame on the y
     * 
     * @since 1.2.0
     */
    private int mouseDeltaY = 0;

    /**
     * The distance the scroll wheel was turned in the last frame on the x
     * 
     * @since 1.2.0
     */
    private int mouseDeltaScrollX = 0;

    /**
     * The distance the scroll wheel was turned in the last frame on the y
     * 
     * @since 1.2.0
     */
    private int mouseDeltaScrollY = 0;

    /**
     * Weather the delta is auto reset
     * 
     * @since 1.5.0
     */
    private boolean autoResetDelta = true;

    /**
     * Create an input manager
     * 
     * @param eventManager The event manager to listen to
     * 
     * @since 1.7.0
     */
    public InputManager(EventManager eventManager) {
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
            if (event.getAction() == MouseAction.DOWN) {
                if (!buttonsDown.contains(event.getButton())) {
                    buttonsDown.add(event.getButton());
                }
            } else if (event.getAction() == MouseAction.UP) {
                if (buttonsDown.contains(event.getButton())) {
                    buttonsDown.remove(event.getButton());
                }
            }
        });

        this.eventManager.addEventListener(MouseMoveEvent.class, (event) -> {
            this.mouseDeltaX += event.getX() - mouseX;
            this.mouseDeltaY += event.getY() - mouseY;

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
     * Get weather a key is down or not
     * 
     * @param key The key to check
     * 
     * @return Weather the key is down or not
     * 
     * @since 1.2.0
     */
    public boolean getKeyDown(KeyCode key) {
        return this.keysDown.contains(key);
    }

    /**
     * Get weather a button is down or not
     * 
     * @param button The button to check
     * 
     * @return Weather the button is down or not
     * 
     * @since 1.2.0
     */
    public boolean getButtonDown(MouseButton button) {
        return this.buttonsDown.contains(button);
    }

    /**
     * Get the current mouse x
     * 
     * @return The current mouse x
     * 
     * @since 1.2.0
     */
    public int getMouseX() {
        return this.mouseX;
    }

    /**
     * Get the current mouse y
     * 
     * @return The current mouse y
     * 
     * @since 1.2.0
     */
    public int getMouseY() {
        return this.mouseY;
    }

    /**
     * Get the distance the mouse has moved in the last frame on the x
     * 
     * @return The distance the mouse has moved in the last frame on the x
     * 
     * @since 1.2.0
     */
    public int getMouseDeltaX() {
        return this.mouseDeltaX;
    }

    /**
     * Get the distance the mouse has moved in the last frame on the y
     * 
     * @return The distance the mouse has moved in the last frame on the y
     * 
     * @since 1.2.0
     */
    public int getMouseDeltaY() {
        return this.mouseDeltaY;
    }

    /**
     * Get the distance the scroll wheel was turned in the last frame on the x
     * 
     * @return The distance the scroll wheel was turned in the last frame on the x
     * 
     * @since 1.2.0
     */
    public int getScrollDeltaX() {
        return this.mouseDeltaScrollX;
    }

    /**
     * Get the distance the scroll wheel was turned in the last frame on the y
     * 
     * @return The distance the scroll wheel was turned in the last frame on the y
     * 
     * @since 1.2.0
     */
    public int getScrollDeltaY() {
        return this.mouseDeltaScrollY;
    }

    /**
     * Get weather the delta is auto reset
     * 
     * @return Weather the delta is auto reset
     * 
     * @since 1.5.0
     */
    public boolean getAutoResetDelta() {
        return this.autoResetDelta;
    }

    /**
     * Set weather the delta is auto reset (Note if you set this to false you must call {@link #resetDelta} yourself each update)
     * 
     * @param flag Weather the delta is auto reset
     * 
     * @since 1.5.0
     */
    public void setAutoResetDelta(boolean flag) {
        this.autoResetDelta = flag;
    }

    /**
     * Reset the delta variables
     * 
     * @since 1.2.0
     */
    public void resetDelta() {
        this.mouseDeltaX = 0;
        this.mouseDeltaY = 0;

        this.mouseDeltaScrollX = 0;
        this.mouseDeltaScrollY = 0;
    }
}