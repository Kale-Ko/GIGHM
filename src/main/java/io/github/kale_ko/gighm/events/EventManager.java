package io.github.kale_ko.gighm.events;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import io.github.kale_ko.gighm.events.types.Event;
import io.github.kale_ko.gighm.exception.InvalidDataException;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * An event manager for listening to and broadcasting events
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
 * @since 1.6.0
 */
public class EventManager {
    /**
     * An event listener object (Only used Internally)
     * 
     * @param <T> The type of event that is being listened to
     * 
     * @author Kale Ko
     * 
     * @version 2.1.0
     * @since 1.6.0
     */
    protected class EventLister<T extends Event> {
        /**
         * The type of the listener
         * 
         * @since 1.6.0
         */
        public @NotNull Class<T> type;

        /**
         * The consumer that runs when the event is called
         * 
         * @since 1.6.0
         */
        public @NotNull Consumer<T> consumer;

        /**
         * Create an event listener object (Only used Internally)
         * 
         * @param type The type of the listener
         * @param consumer The consumer that runs when the event is called
         * 
         * @since 1.6.0
         */
        protected EventLister(@NotNull Class<T> type, @NotNull Consumer<T> consumer) {
            NullUtils.checkNulls(type, "type");
            NullUtils.checkNulls(consumer, "consumer");

            this.type = type;
            this.consumer = consumer;
        }

        /**
         * Call the consumer with the passed event
         * 
         * @param event The event to pass
         * 
         * @throws InvalidDataException If the event is of an unacceptable type
         * 
         * @since 1.6.0
         */
        @SuppressWarnings("unchecked")
        public void accept(@NotNull Event event) throws InvalidDataException {
            NullUtils.checkNulls(event, "event");

            if (this.canAccept(event)) {
                this.consumer.accept((T) event);
            } else {
                throw new InvalidDataException("Can't pass event of type \"" + event.getClass().getName() + "\" to listener of type \"" + type.getName() + "\"");
            }
        }

        /**
         * Check if this event listener can accept an event
         * 
         * @param event The event to check
         * 
         * @return Weather the passed event is acceptable
         * 
         * @since 1.6.0
         */
        public Boolean canAccept(@NotNull Event event) {
            NullUtils.checkNulls(event, "event");

            return this.type.isInstance(event);
        }
    }

    /**
     * Create an event manager
     * 
     * @since 1.6.0
     */
    public EventManager() {}

    /**
     * The amount of listener ids already in use
     * 
     * @since 1.6.0
     */
    protected @NotNull Integer usedIds = 0;

    /**
     * The map of registered event listeners
     * 
     * @since 1.6.0
     */
    protected @NotNull Map<Integer, EventLister<? extends Event>> listeners = new HashMap<Integer, EventLister<? extends Event>>();

    /**
     * Emit an event to all listeners of the event type
     * 
     * @param event The event to emit
     * 
     * @since 1.6.0
     */
    public void emit(@NotNull Event event) {
        NullUtils.checkNulls(event, "event");

        for (EventLister<? extends Event> listener : this.listeners.values()) {
            if (listener.canAccept(event)) {
                listener.accept(event);
            }
        }
    }

    /**
     * Register an event listeners
     * 
     * @param <T> The type of the listener
     * @param type The type of the listener
     * @param consumer The event consumer
     * 
     * @return The id of the event listener (Needed for unregistering)
     * 
     * @since 1.6.0
     */
    public @NotNull <T extends Event> Integer addEventListener(@NotNull Class<T> type, @NotNull Consumer<T> consumer) {
        NullUtils.checkNulls(type, "type");
        NullUtils.checkNulls(consumer, "consumer");

        this.usedIds++;

        this.listeners.put(this.usedIds, new EventLister<T>(type, consumer));

        return this.usedIds;
    }

    /**
     * Unregister an event listeners
     * 
     * @param id The id of the listener
     * 
     * @since 1.6.0
     */
    public void removeEventListener(@NotNull Integer id) {
        NullUtils.checkNulls(id, "id");

        this.listeners.remove(id);
    }
}