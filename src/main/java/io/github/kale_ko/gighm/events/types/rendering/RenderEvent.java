package io.github.kale_ko.gighm.events.types.rendering;

import io.github.kale_ko.gighm.events.types.Event;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A render event
 * Fires every time the window is rendered
 * 
 * @author Kale Ko
 * 
 * @version 2.4.0
 * @since 1.6.0
 */
public class RenderEvent extends Event {
    /**
     * The time since the last render
     * 
     * @since 1.6.0
     */
    protected @NotNull Float delta;

    /**
     * Create a render event
     * 
     * @param delta The time since the last render
     * 
     * @since 1.6.0
     */
    public RenderEvent(@NotNull Float delta) {
        NullUtils.checkNulls(delta, "delta");

        this.delta = delta;
    }

    /**
     * Get the time since the last render
     * 
     * @return The time since the last render
     * 
     * @since 1.6.0
     */
    public @NotNull Float getDelta() {
        return this.delta;
    }
}