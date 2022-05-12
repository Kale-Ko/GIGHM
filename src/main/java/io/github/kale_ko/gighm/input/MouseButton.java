package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * A mouse button
 * 
 * @version 1.2.0
 * @since 1.2.0
 */
public enum MouseButton {
    // Main buttons
    LEFT(GLFW_MOUSE_BUTTON_1),
    RIGHT(GLFW_MOUSE_BUTTON_2),
    MIDDLE(GLFW_MOUSE_BUTTON_3),

    // Extra buttons
    EXTRA1(GLFW_MOUSE_BUTTON_4),
    EXTRA2(GLFW_MOUSE_BUTTON_5),
    EXTRA3(GLFW_MOUSE_BUTTON_6),
    EXTRA4(GLFW_MOUSE_BUTTON_7),
    EXTRA5(GLFW_MOUSE_BUTTON_8);

    /**
     * The id of the glfw button corresponding with the mouse button
     * 
     * @since 1.2.0
     */
    private final int glfwButtonId;

    /**
     * Create a {@link MouseButton}
     * 
     * @param glfwButtonId The id of the glfw button corresponding with the mouse button
     * 
     * @since 1.2.0
     */
    private MouseButton(int glfwButtonId) {
        this.glfwButtonId = glfwButtonId;
    }

    /**
     * Get the {@link MouseButton} associated with the inputted glfw button
     * 
     * @param id The id of the glfw button
     * 
     * @return The {@link MouseButton} corresponding with the inputted glfw button
     * 
     * @since 1.2.0
     */
    public static MouseButton valueOfGLFWButton(int id) {
        for (MouseButton code : values()) {
            if (code.glfwButtonId == id) {
                return code;
            }
        }

        return null;
    }
}