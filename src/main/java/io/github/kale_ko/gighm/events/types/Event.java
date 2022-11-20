package io.github.kale_ko.gighm.events.types;

/**
 * An abstract event object
 * 
 * @author Kale Ko
 * 
 * @version 2.4.0
 * @since 1.6.0
 */
public abstract class Event {
    /** @hidden */
    private static Long events = 0l;

    /**
     * The unique id of the event
     * 
     * @since 2.4.0
     */
    protected Long eventId;

    /**
     * Create an event
     * 
     * @since 2.4.0
     */
    protected Event() {
        this.eventId = events++;
    }

    /**
     * Get the unique id of the event
     * 
     * @return The unique id of the event
     * 
     * @since 2.4.0
     */
    public Long getEventId() {
        return this.eventId;
    }
}