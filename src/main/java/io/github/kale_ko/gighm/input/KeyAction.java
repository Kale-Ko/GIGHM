package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;
import javax.validation.constraints.NotNull;

/**
 * A key action
 * 
 * @version 1.2.0
 * @since 1.2.0
 */
public enum KeyAction {
    /**
     * Key pressed
     * 
     * @since 1.2.0
     */
    DOWN(GLFW_PRESS),

    /**
     * Key released
     * 
     * @since 1.2.0
     */
    UP(GLFW_RELEASE),

    /**
     * Key repeated (Still held down)
     * 
     * @since 1.2.0
     */
    REPEAT(GLFW_REPEAT);

    /**
     * The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private final @NotNull int glfwEventId;

    /**
     * Create a {@link KeyAction}
     * 
     * @param glfwEventId The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private KeyAction(@NotNull int glfwEventId) {
        this.glfwEventId = glfwEventId;
    }

    /**
     * Get the {@link KeyAction} associated with the inputted glfw event
     * 
     * @param id The id of the glfw event
     * 
     * @return The {@link KeyAction} corresponding with the inputted glfw event
     * 
     * @since 1.2.0
     */
    public static KeyAction valueOfGLFWEvent(@NotNull int id) {
        for (KeyAction action : values()) {
            if (action.glfwEventId == id) {
                return action;
            }
        }

        return null;
    }
}