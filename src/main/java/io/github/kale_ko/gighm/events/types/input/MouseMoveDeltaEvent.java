package io.github.kale_ko.gighm.events.types.input;

import io.github.kale_ko.gighm.events.types.Event;
import io.github.kale_ko.gighm.input.InputManager;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A mouse move event that includes the delta movement
 * Fires whenever the mouse is moved inside the window (Only if there is an {@link InputManager})
 * 
 * @author Kale Ko
 * 
 * @version 2.4.0
 * @since 2.2.0
 */
public class MouseMoveDeltaEvent extends Event {
    /**
     * The x position of the mouse inside the window
     * 
     * @since 1.6.0
     */
    protected @NotNull Integer x;

    /**
     * The y position of the mouse inside the window
     * 
     * @since 1.6.0
     */
    protected @NotNull Integer y;

    /**
     * The amount the mouse has moved on the x axis
     * 
     * @since 2.2.0
     */
    protected @NotNull Integer deltaX;

    /**
     * The amount the mouse has moved on the y axis
     * 
     * @since 2.2.0
     */
    protected @NotNull Integer deltaY;

    /**
     * Create a mouse move delta event
     * 
     * @param x The x position of the mouse inside the window
     * @param y The y position of the mouse inside the window
     * @param deltaX The amount the mouse has moved on the x axis
     * @param deltaY The amount the mouse has moved on the y axis
     * 
     * @since 2.2.0
     */
    public MouseMoveDeltaEvent(@NotNull Integer x, @NotNull Integer y, @NotNull Integer deltaX, @NotNull Integer deltaY) {
        NullUtils.checkNulls(x, "x");
        NullUtils.checkNulls(y, "y");
        NullUtils.checkNulls(deltaX, "deltaX");
        NullUtils.checkNulls(deltaY, "deltaY");

        this.x = x;
        this.y = y;

        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Get the x position of the mouse inside the window
     * 
     * @return The x position of the mouse inside the window
     * 
     * @since 1.6.0
     */
    public @NotNull Integer getX() {
        return this.x;
    }

    /**
     * Get the y position of the mouse inside the window
     * 
     * @return The y position of the mouse inside the window
     * 
     * @since 1.6.0
     */
    public @NotNull Integer getY() {
        return this.y;
    }

    /**
     * Get the amount the mouse has moved on the x axis
     * 
     * @return The amount the mouse has moved on the x axis
     * 
     * @since 2.2.0
     */
    public @NotNull Integer getDeltaX() {
        return this.deltaX;
    }

    /**
     * Get the amount the mouse has moved on the y axis
     * 
     * @return The amount the mouse has moved on the y axis
     * 
     * @since 2.2.0
     */
    public @NotNull Integer getDeltaY() {
        return this.deltaY;
    }
}