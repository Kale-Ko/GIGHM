package io.github.kale_ko.gighm.scene.components;

import javax.validation.constraints.NotNull;

/**
 * A base component
 * 
 * @since 1.0.0
 */
public abstract class Component {
    /**
     * Create a base component
     */
    protected Component() {}

    /**
     * Get the name of the component
     * 
     * @return The name of the component
     * 
     * @since 1.0.0
     */
    public static @NotNull String getName() {
        return "Component";
    }
}