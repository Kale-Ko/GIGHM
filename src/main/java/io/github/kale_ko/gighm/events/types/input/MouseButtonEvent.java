package io.github.kale_ko.gighm.events.types.input;

import io.github.kale_ko.gighm.events.types.CancellableEvent;
import io.github.kale_ko.gighm.input.MouseAction;
import io.github.kale_ko.gighm.input.MouseButton;

/**
 * A mouse button event
 * 
 * @version 1.6.0
 * @since 1.6.0
 */
public class MouseButtonEvent extends CancellableEvent {
    /**
     * The mouse button
     * 
     * @since 1.6.0
     */
    private MouseButton button;

    /**
     * The mouse action
     * 
     * @since 1.6.0
     */
    private MouseAction action;

    /**
     * Create a mouse button event
     * 
     * @param button The mouse button
     * @param action The mouse action
     * 
     * @since 1.6.0
     */
    public MouseButtonEvent(MouseButton button, MouseAction action) {
        this.button = button;
        this.action = action;
    }

    /**
     * Get the mouse button
     * 
     * @return The mouse button
     * 
     * @since 1.6.0
     */
    public MouseButton getButton() {
        return this.button;
    }

    /**
     * Get the mouse action
     * 
     * @return The mouse action
     * 
     * @since 1.6.0
     */
    public MouseAction getAction() {
        return this.action;
    }
}