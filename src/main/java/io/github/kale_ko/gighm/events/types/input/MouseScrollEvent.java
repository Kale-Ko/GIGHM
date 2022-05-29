package io.github.kale_ko.gighm.events.types.input;

import io.github.kale_ko.gighm.events.types.CancellableEvent;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A mouse scroll event
 * Fires whenever the mouse wheel is scrolled
 * 
 * @author Kale Ko
 * 
 * @version 1.7.0
 * @since 1.6.0
 */
public class MouseScrollEvent extends CancellableEvent {
    /**
     * The delta x of the scroll
     * 
     * @since 1.6.0
     */
    private @NotNull Integer x;

    /**
     * The delta y of the scroll
     * 
     * @since 1.6.0
     */
    private @NotNull Integer y;

    /**
     * Create a mouse scroll event
     * 
     * @param x The delta x of the scroll
     * @param y The delta y of the scroll
     * 
     * @since 1.6.0
     */
    public MouseScrollEvent(@NotNull Integer x, @NotNull Integer y) {
        NullUtils.checkNulls(x, "x");
        NullUtils.checkNulls(y, "y");

        this.x = x;
        this.y = y;
    }

    /**
     * Get The delta x of the scroll
     * 
     * @return The delta x of the scroll
     * 
     * @since 1.6.0
     */
    public @NotNull Integer getX() {
        return this.x;
    }

    /**
     * Get The delta y of the scroll
     * 
     * @return The delta y of the scroll
     * 
     * @since 1.6.0
     */
    public @NotNull Integer getY() {
        return this.y;
    }
}