package io.github.kale_ko.gighm.events.types.input;

import io.github.kale_ko.gighm.events.types.Event;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A mouse move event
 * Fires whenever the mouse is moved inside the window
 * 
 * @author Kale Ko
 * 
 * @version 1.6.0
 * @since 1.6.0
 */
public class MouseMoveEvent extends Event {
    /**
     * The x position of the mouse (Relative to the window position)
     * 
     * @since 1.6.0
     */
    private @NotNull Integer x;

    /**
     * The y position of the mouse (Relative to the window position)
     * 
     * @since 1.6.0
     */
    private @NotNull Integer y;

    /**
     * Create a mouse move event
     * 
     * @param x The x position of the mouse (Relative to the window position)
     * @param y The y position of the mouse (Relative to the window position)
     * 
     * @since 1.6.0
     */
    public MouseMoveEvent(@NotNull Integer x, @NotNull Integer y) {
        NullUtils.checkNulls(x, "x");
        NullUtils.checkNulls(y, "y");

        this.x = x;
        this.y = y;
    }

    /**
     * Get the x position of the mouse (Relative to the window position)
     * 
     * @return The x position of the mouse (Relative to the window position)
     * 
     * @since 1.6.0
     */
    public @NotNull Integer getX() {
        return this.x;
    }

    /**
     * Get the y position of the mouse (Relative to the window position)
     * 
     * @return The y position of the mouse (Relative to the window position)
     * 
     * @since 1.6.0
     */
    public @NotNull Integer getY() {
        return this.y;
    }
}