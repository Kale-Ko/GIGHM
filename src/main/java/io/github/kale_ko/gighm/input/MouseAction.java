package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * A mouse action
 * 
 * @version 1.2.0
 * @since 1.2.0
 */
public enum MouseAction {
    /**
     * Mouse button pressed
     * 
     * @since 1.2.0
     */
    DOWN(GLFW_PRESS),

    /**
     * Mouse button released
     * 
     * @since 1.2.0
     */
    UP(GLFW_RELEASE);

    /**
     * The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private final int glfwEventId;

    /**
     * Create a {@link MouseAction}
     * 
     * @param glfwEventId The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private MouseAction(int glfwEventId) {
        this.glfwEventId = glfwEventId;
    }

    /**
     * Get the {@link MouseAction} associated with the inputted glfw event
     * 
     * @param id The id of the glfw event
     * 
     * @return The {@link MouseAction} corresponding with the inputted glfw event
     * 
     * @since 1.2.0
     */
    public static MouseAction valueOfGLFWEvent(int id) {
        for (MouseAction action : values()) {
            if (action.glfwEventId == id) {
                return action;
            }
        }

        return null;
    }
}