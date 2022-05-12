package io.github.kale_ko.gighm.events;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import io.github.kale_ko.gighm.events.types.Event;

/**
 * An event manager
 * 
 * @version 1.6.0
 * @since 1.6.0
 */
public class EventManager {
    /**
     * An event listener (Only used internally)
     * 
     * @since 1.6.0
     */
    private class EventLister<T extends Event> {
        public Class<T> type;
        public Consumer<T> consumer;

        private EventLister(Class<T> type, Consumer<T> consumer) {
            this.type = type;
            this.consumer = consumer;
        }

        @SuppressWarnings("unchecked")
        public void accept(Event event) {
            if (isAccepted(event)) {
                consumer.accept((T) event);
            }
        }

        public boolean isAccepted(Event event) {
            return type.isInstance(event);
        }
    }

    /**
     * The amount of listener ids used
     * 
     * @since 1.6.0
     */
    private Integer usedIds = 0;

    /**
     * The map of registered listeners
     * 
     * @since 1.6.0
     */
    private Map<Integer, EventLister<? extends Event>> listeners = new HashMap<Integer, EventLister<? extends Event>>();

    /**
     * Emit an event to all event listeners of the event type
     * 
     * @param event The event to emit
     * 
     * @since 1.6.0
     */
    public void emit(Event event) {
        for (EventLister<? extends Event> listener : listeners.values()) {
            if (listener.isAccepted(event)) {
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
     * @return The id of the listener (Needed for unregistering)
     * 
     * @since 1.6.0
     */
    public <T extends Event> int addEventListener(Class<T> type, Consumer<T> consumer) {
        usedIds++;

        listeners.put(usedIds, new EventLister<T>(type, consumer));

        return usedIds;
    }

    /**
     * Unregister an event listeners
     * 
     * @param id The id of the listener
     * 
     * @since 1.6.0
     */
    public void removeEventListener(int id) {
        listeners.remove(id);
    }
}