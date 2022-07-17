package io.github.kale_ko.gighm.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A mouse button
 * 
 * @author Kale Ko
 * 
 * @version 1.5.0
 * @since 1.2.0
 */
public class MouseButton {
    /**
     * The left mouse button
     * 
     * @since 1.2.0
     */
    public static final MouseButton LEFT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_1);

    /**
     * The right mouse button
     * 
     * @since 1.2.0
     */
    public static final MouseButton RIGHT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_2);

    /**
     * THe middle mouse button
     * 
     * @since 1.2.0
     */
    public static final MouseButton MIDDLE = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_3);

    /**
     * The extra mouse button 1
     * (Often the back side button (Navigate back))
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA1 = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_4);

    /**
     * The extra mouse button 2
     * (Often the front side button (Navigate forwards))
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA2 = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_5);

    /**
     * The extra mouse button 3
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA3 = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_6);

    /**
     * The extra mouse button 4
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA4 = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_7);

    /**
     * The extra mouse button 5
     * 
     * @since 1.2.0
     */
    public static final MouseButton EXTRA5 = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_8);

    /**
     * The id of the glfw button corresponding with the mouse button
     * 
     * @since 1.2.0
     */
    private final Integer glfwButtonId;

    /**
     * Create a mouse button
     * 
     * @param glfwButtonId The id of the glfw button corresponding with the mouse button
     * 
     * @since 1.2.0
     */
    private MouseButton(@NotNull Integer glfwButtonId) {
        NullUtils.checkNulls(glfwButtonId, "glfwButtonId");

        this.glfwButtonId = glfwButtonId;
    }

    /**
     * Get a string representing the object
     * 
     * @return A string representing the object
     * 
     * @since 1.6.0
     */
    @Override
    public @NotNull String toString() {
        for (Field field : MouseButton.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((MouseButton) field.get(null)).equals(this)) {
                    return field.getName();
                }

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                continue;
            }
        }

        return getClass().getName();
    }

    /**
     * Get all of the buttons defined
     * 
     * @return All of the buttons defined
     * 
     * @since 1.5.0
     */
    public static @NotNull MouseButton[] values() {
        List<MouseButton> buttons = new ArrayList<MouseButton>();

        for (Field field : MouseButton.class.getFields()) {
            try {
                field.setAccessible(true);

                buttons.add((MouseButton) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                continue;
            }
        }

        return buttons.toArray(new MouseButton[] {});
    }

    /**
     * Get the mouse button corresponding to the inputted glfw button
     * 
     * @param id The id of the glfw button
     * 
     * @return The mouse button corresponding to the inputted glfw button
     * 
     * @since 1.2.0
     */
    public static @NotNull MouseButton valueOfGLFWButton(@NotNull Integer id) {
        NullUtils.checkNulls(id, "id");

        for (MouseButton code : values()) {
            if (code.glfwButtonId.equals(id)) {
                return code;
            }
        }

        return null;
    }
}