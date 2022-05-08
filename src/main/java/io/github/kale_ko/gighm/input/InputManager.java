package io.github.kale_ko.gighm.input;

import java.util.List;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;

/**
 * An input manager
 * 
 * @version 1.2.0
 * @since 1.2.0
 */
public class InputManager {
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
    private @NotNull int mouseX = -1;

    /**
     * The current mouse y
     * 
     * @since 1.2.0
     */
    private @NotNull int mouseY = -1;

    /**
     * The distance the mouse has moved in the last frame on the x
     * 
     * @since 1.2.0
     */
    private @NotNull int mouseDeltaX = 0;

    /**
     * The distance the mouse has moved in the last frame on the y
     * 
     * @since 1.2.0
     */
    private @NotNull int mouseDeltaY = 0;

    /**
     * The distance the scroll wheel was turned in the last frame on the x
     * 
     * @since 1.2.0
     */
    private @NotNull int mouseDeltaScrollX = 0;

    /**
     * The distance the scroll wheel was turned in the last frame on the y
     * 
     * @since 1.2.0
     */
    private @NotNull int mouseDeltaScrollY = 0;

    /**
     * Called for a keyboard event
     * 
     * @param key The key the event is about
     * @param action The action that was run
     * @param mods The modifiers currently down
     * 
     * @since 1.2.0
     */
    public void onKeyboardKey(@NotNull KeyCode key, @NotNull KeyAction action, @NotNull List<KeyMod> mods) {
        if (action == KeyAction.DOWN) {
            if (!keysDown.contains(key)) {
                keysDown.add(key);
            }
        } else if (action == KeyAction.UP) {
            if (keysDown.contains(key)) {
                keysDown.remove(key);
            }
        }
    }

    /**
     * Called for a mouse button event
     * 
     * @param button The button the event is about
     * @param action The action that was run
     * 
     * @since 1.2.0
     */
    public void onMouseButton(@NotNull MouseButton button, @NotNull MouseAction action) {
        if (action == MouseAction.DOWN) {
            if (!buttonsDown.contains(button)) {
                buttonsDown.add(button);
            }
        } else if (action == MouseAction.UP) {
            if (buttonsDown.contains(button)) {
                buttonsDown.remove(button);
            }
        }
    }

    /**
     * Called for a mouse move event
     * 
     * @param x The new mouse x
     * @param y The new mouse y
     * 
     * @since 1.2.0
     */
    public void onMouseMove(@NotNull int x, @NotNull int y) {
        this.mouseDeltaX += x - mouseX;
        this.mouseDeltaY += y - mouseY;

        this.mouseX = x;
        this.mouseY = y;
    }

    /**
     * Called for a mouse scroll event
     * 
     * @param x The distance scrolled on the x
     * @param y The distance scrolled on the y
     * 
     * @since 1.2.0
     */
    public void onMouseScroll(@NotNull int x, @NotNull int y) {
        this.mouseDeltaScrollX -= x;
        this.mouseDeltaScrollY -= y;
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

    /**
     * Get weather a key is down or not
     * 
     * @param key The key to check
     * 
     * @return Weather the key is down or not
     * 
     * @since 1.2.0
     */
    public @NotNull boolean getKeyDown(KeyCode key) {
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
    public @NotNull boolean getButtonDown(MouseButton button) {
        return this.buttonsDown.contains(button);
    }

    /**
     * Get the current mouse x
     * 
     * @return The current mouse x
     * 
     * @since 1.2.0
     */
    public @NotNull int getMouseX() {
        return this.mouseX;
    }

    /**
     * Get the current mouse y
     * 
     * @return The current mouse y
     * 
     * @since 1.2.0
     */
    public @NotNull int getMouseY() {
        return this.mouseY;
    }

    /**
     * Get the distance the mouse has moved in the last frame on the x
     * 
     * @return The distance the mouse has moved in the last frame on the x
     * 
     * @since 1.2.0
     */
    public @NotNull int getMouseDeltaX() {
        return this.mouseDeltaX;
    }

    /**
     * Get the distance the mouse has moved in the last frame on the y
     * 
     * @return The distance the mouse has moved in the last frame on the y
     * 
     * @since 1.2.0
     */
    public @NotNull int getMouseDeltaY() {
        return this.mouseDeltaY;
    }

    /**
     * Get the distance the scroll wheel was turned in the last frame on the x
     * 
     * @return The distance the scroll wheel was turned in the last frame on the x
     * 
     * @since 1.2.0
     */
    public @NotNull int getScrollDeltaX() {
        return this.mouseDeltaScrollX;
    }

    /**
     * Get the distance the scroll wheel was turned in the last frame on the y
     * 
     * @return The distance the scroll wheel was turned in the last frame on the y
     * 
     * @since 1.2.0
     */
    public @NotNull int getScrollDeltaY() {
        return this.mouseDeltaScrollY;
    }
}