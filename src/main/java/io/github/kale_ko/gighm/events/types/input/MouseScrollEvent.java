package io.github.kale_ko.gighm.events.types.input;

import io.github.kale_ko.gighm.events.types.Event;

/**
 * A mouse scroll event
 * 
 * @version 1.6.0
 * @since 1.6.0
 */
public class MouseScrollEvent extends Event {
    /**
     * The mouse x
     * 
     * @since 1.6.0
     */
    private int x;

    /**
     * The mouse y
     * 
     * @since 1.6.0
     */
    private int y;

    /**
     * Create a mouse scroll event
     * 
     * @param x The mouse x
     * @param y The mouse y
     * 
     * @since 1.6.0
     */
    public MouseScrollEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the mouse x
     * 
     * @return The mouse x
     * 
     * @since 1.6.0
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the mouse y
     * 
     * @return The mouse y
     * 
     * @since 1.6.0
     */
    public int getY() {
        return this.y;
    }
}