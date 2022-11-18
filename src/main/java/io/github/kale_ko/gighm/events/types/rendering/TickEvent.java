package io.github.kale_ko.gighm.events.types.rendering;

import io.github.kale_ko.gighm.events.types.Event;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A tick event
 * Fires every 40 milliseconds (25 times a second)
 * 
 * @author Kale Ko
 * 
 * @version 2.4.0
 * @since 1.7.0
 */
public class TickEvent extends Event {
    /**
     * The tick number (Times ticked)
     * 
     * @since 2.4.0
     */
    protected @NotNull Integer tickNumber;

    /**
     * Create a tick event
     * 
     * @param tickNumber The tick number (Times ticked)
     * 
     * @since 2.4.0
     */
    public TickEvent(@NotNull Integer tickNumber) {
        NullUtils.checkNulls(tickNumber, "tickNumber");

        this.tickNumber = tickNumber;
    }

    /**
     * Get the tick number (Times ticked)
     * 
     * @return The tick number (Times ticked)
     * 
     * @since 2.4.0
     */
    public @NotNull Integer getTickNumber() {
        return this.tickNumber;
    }
}