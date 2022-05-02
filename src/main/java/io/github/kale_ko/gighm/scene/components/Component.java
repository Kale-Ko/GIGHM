package io.github.kale_ko.gighm.scene.components;

import javax.validation.constraints.NotNull;
import io.github.kale_ko.gighm.scene.GameObject;

/**
 * A base component
 * 
 * @since 1.0.0
 */
public abstract class Component {
    /**
     * The gameobject this component is a part of
     * 
     * @since 1.0.0
     */
    private GameObject gameObject;

    /**
     * Create a base component
     * 
     * @since 1.0.0
     */
    protected Component() {}

    /**
     * Get the gameobject this component is a part of
     * 
     * @return The gameobject this component is a part of
     * 
     * @since 1.0.0
     */
    public GameObject getGameObject() {
        return this.gameObject;
    }

    /**
     * Set the gameobject this component is a part of (Only used internally)
     * 
     * @since 1.0.0
     */
    public void _setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Get the name of the component
     * 
     * @return The name of the component
     * @since 1.0.0
     */
    public static @NotNull String getName() {
        return "Component";
    }
}