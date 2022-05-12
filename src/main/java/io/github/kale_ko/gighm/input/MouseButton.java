package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A mouse button
 * 
 * @version 1.5.0
 * @since 1.2.0
 */
public class MouseButton {
    /**
     * Left mouse button
     * 
     * @since 1.2.0
     */
    public static final MouseButton LEFT = new MouseButton(GLFW_MOUSE_BUTTON_1);

    /**
     * Right mouse button
     * 
     * @since 1.2.0
     */
    public static final MouseButton RIGHT = new MouseButton(GLFW_MOUSE_BUTTON_2);

    /**
     * Middle mouse button
     * 
     * @since 1.2.0
     */
    public static final MouseButton MIDDLE = new MouseButton(GLFW_MOUSE_BUTTON_3);

    /**
     * Extra mouse button 1 (Usually the front side button (Navigate forwards))
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA1 = new MouseButton(GLFW_MOUSE_BUTTON_4);

    /**
     * Extra mouse button 2 (Usually the back side button (Navigate back))
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA2 = new MouseButton(GLFW_MOUSE_BUTTON_5);

    /**
     * Extra mouse button 3
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA3 = new MouseButton(GLFW_MOUSE_BUTTON_6);

    /**
     * Extra mouse button 4
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA4 = new MouseButton(GLFW_MOUSE_BUTTON_7);

    /**
     * Extra mouse button 5
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA5 = new MouseButton(GLFW_MOUSE_BUTTON_8);

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
     * Get all of the buttons defined
     * 
     * @return All of the buttons defined
     * 
     * @since 1.5.0
     */
    public static MouseButton[] values() {
        List<MouseButton> buttons = new ArrayList<MouseButton>();

        for (Field field : MouseButton.class.getFields()) {
            try {
                field.setAccessible(true);

                buttons.add((MouseButton) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return buttons.toArray(new MouseButton[] {});
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