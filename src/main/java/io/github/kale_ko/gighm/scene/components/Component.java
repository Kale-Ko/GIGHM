package io.github.kale_ko.gighm.scene.components;

import io.github.kale_ko.gighm.events.types.rendering.RenderEvent;
import io.github.kale_ko.gighm.events.types.rendering.TickEvent;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * A abstract component object
 * 
 * @author Kale Ko
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Component {
    /**
     * The game object this component is a part of
     * 
     * @since 1.0.0
     */
    private @Nullable GameObject gameObject = null;

    /**
     * Create a abstract component object
     * 
     * @since 1.0.0
     */
    protected Component() {}

    /**
     * Get the game object this component is a part of
     * 
     * @return The game object this component is a part of
     * 
     * @since 1.0.0
     */
    public @Nullable GameObject getGameObject() {
        return this.gameObject;
    }

    /**
     * Set the game object this component is a part of (Only used Internally)
     * 
     * @param gameObject The game object this component should be a part of
     * 
     * @since 1.0.0
     */
    public void _setGameObject(@NotNull GameObject gameObject) {
        NullUtils.checkNulls(gameObject, "gameObject");

        this.gameObject = gameObject;
    }

    /**
     * Called at the same time as {@link RenderEvent}
     * 
     * @param delta The time since the last render
     * 
     * @since 1.7.0
     */
    public void render(@NotNull Double delta) {
        NullUtils.checkNulls(delta, "delta");
    }

    /**
     * Called at the same time as {@link TickEvent}
     * 
     * @since 1.7.0
     */
    public void tick() {}
}