package io.github.kale_ko.gighm.scene.components;

import io.github.kale_ko.gighm.scene.GameObject;

/**
 * A base component
 * 
 * @version 1.0.0
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
     * @param gameObject The gameobject this component should be a part of
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
     * 
     * @since 1.0.0
     */
    public static String getName() {
        return "Component";
    }
}