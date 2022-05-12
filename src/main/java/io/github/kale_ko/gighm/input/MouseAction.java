package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A mouse action
 * 
 * @version 1.5.0
 * @since 1.2.0
 */
public class MouseAction {
    /**
     * Mouse button pressed
     * 
     * @since 1.2.0
     */
    public static final MouseAction DOWN = new MouseAction(GLFW_PRESS);

    /**
     * Mouse button released
     * 
     * @since 1.2.0
     */
    public static final MouseAction UP = new MouseAction(GLFW_RELEASE);

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
     * Get a string representing the object
     * 
     * @return A string representing the object
     * 
     * @since 1.6.0
     */
    @Override
    public String toString() {
        for (Field field : MouseAction.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((MouseAction) field.get(null)).equals(this)) {
                    return field.getName();
                }

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return "MouseAction";
    }

    /**
     * Get all of the mods defined
     * 
     * @return All of the mods defined
     * 
     * @since 1.5.0
     */
    public static MouseAction[] values() {
        List<MouseAction> mods = new ArrayList<MouseAction>();

        for (Field field : MouseAction.class.getFields()) {
            try {
                field.setAccessible(true);

                mods.add((MouseAction) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return mods.toArray(new MouseAction[] {});
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