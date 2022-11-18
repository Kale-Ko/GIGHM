package io.github.kale_ko.gighm.events.types;

import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * An abstract cancellable event object
 * 
 * @author Kale Ko
 * 
 * @version 2.4.0
 * @since 1.6.0
 */
public abstract class CancellableEvent extends Event {
    /**
     * Weather the event is cancelled or not
     * 
     * @since 1.6.0
     */
    protected @NotNull Boolean cancelled;

    /**
     * Create an event
     * 
     * @since 2.4.0
     */
    protected CancellableEvent() {
        super();
    }

    /**
     * Get weather the event is cancelled or not
     * 
     * @return Weather the event is cancelled or not
     * 
     * @since 1.6.0
     */
    public @NotNull Boolean getCancelled() {
        return this.cancelled;
    }

    /**
     * Set weather the event is cancelled or not
     * 
     * @param cancelled Weather the event is cancelled or not
     * 
     * @since 1.6.0
     */
    public void setCancelled(@NotNull Boolean cancelled) {
        NullUtils.checkNulls(cancelled, "cancelled");

        this.cancelled = cancelled;
    }
}