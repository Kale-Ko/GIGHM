package io.github.kale_ko.gighm.events.types.rendering;

import io.github.kale_ko.gighm.events.types.Event;

/**
 * A render event (Fires every time the window is rendered)
 * 
 * @version 1.6.0
 * @since 1.6.0
 */
public class RenderEvent extends Event {
    /**
     * The time since the last render
     * 
     * @since 1.6.0
     */
    private double delta;

    /**
     * Create a render event
     * 
     * @param delta The time since the last render
     * 
     * @since 1.6.0
     */
    public RenderEvent(double delta) {
        this.delta = delta;
    }

    /**
     * Get the time since the last render
     * 
     * @return The time since the last render
     * 
     * @since 1.6.0
     */
    public double getDelta() {
        return this.delta;
    }
}