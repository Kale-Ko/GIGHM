package io.github.kale_ko.gighm.events.types;

/**
 * An abstract cancellable event object
 * 
 * @version 1.7.0
 * @since 1.6.0
 */
public abstract class CancellableEvent extends Event {
    /**
     * Weather the event is cancelled yet or not
     * 
     * @since 1.6.0
     */
    private boolean cancelled;

    /**
     * Get weather the event is cancelled yet or not
     * 
     * @return Weather the event is cancelled yet or not
     * 
     * @since 1.6.0
     */
    public boolean getCancelled() {
        return this.cancelled;
    }

    /**
     * Set weather the event is cancelled yet or not
     * 
     * @param flag Weather the event is cancelled yet or not
     * 
     * @since 1.6.0
     */
    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }
}