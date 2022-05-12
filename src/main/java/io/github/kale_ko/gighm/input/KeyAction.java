package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A key action
 * 
 * @version 1.6.0
 * @since 1.2.0
 */
public class KeyAction {
    /**
     * Key pressed
     * 
     * @since 1.2.0
     */
    public static final KeyAction DOWN = new KeyAction(GLFW_PRESS);

    /**
     * Key released
     * 
     * @since 1.2.0
     */
    public static final KeyAction UP = new KeyAction(GLFW_RELEASE);

    /**
     * Key repeated (Still held down)
     * 
     * @since 1.2.0
     */
    public static final KeyAction REPEAT = new KeyAction(GLFW_REPEAT);

    /**
     * The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private final int glfwEventId;

    /**
     * Create a {@link KeyAction}
     * 
     * @param glfwEventId The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private KeyAction(int glfwEventId) {
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
        for (Field field : KeyAction.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((KeyAction) field.get(null)).equals(this)) {
                    return field.getName();
                }

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return "KeyAction";
    }

    /**
     * Get all of the mods defined
     * 
     * @return All of the mods defined
     * 
     * @since 1.5.0
     */
    public static KeyAction[] values() {
        List<KeyAction> mods = new ArrayList<KeyAction>();

        for (Field field : KeyAction.class.getFields()) {
            try {
                field.setAccessible(true);

                mods.add((KeyAction) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return mods.toArray(new KeyAction[] {});
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
    public static KeyAction valueOfGLFWEvent(int id) {
        for (KeyAction action : values()) {
            if (action.glfwEventId == id) {
                return action;
            }
        }

        return null;
    }
}