package io.github.kale_ko.gighm.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * The actions a key can have
 * 
 * @author Kale Ko
 * 
 * @version 1.6.0
 * @since 1.2.0
 */
public class KeyAction {
    /**
     * A key is held down
     * 
     * @since 1.2.0
     */
    public static final KeyAction DOWN = new KeyAction(GLFW.GLFW_PRESS);

    /**
     * A key is released
     * 
     * @since 1.2.0
     */
    public static final KeyAction UP = new KeyAction(GLFW.GLFW_RELEASE);

    /**
     * A key repeats (Is still held down)
     * 
     * @since 1.2.0
     */
    public static final KeyAction REPEAT = new KeyAction(GLFW.GLFW_REPEAT);

    /**
     * The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private final @NotNull Integer glfwEventId;

    /**
     * Create a key action
     * 
     * @param glfwEventId The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private KeyAction(@NotNull Integer glfwEventId) {
        NullUtils.checkNulls(glfwEventId, "glfwEventId");

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
    public @NotNull String toString() {
        for (Field field : KeyAction.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((KeyAction) field.get(null)).equals(this)) {
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
     * Get all of the actions defined
     * 
     * @return All of the actions defined
     * 
     * @since 1.5.0
     */
    public static @NotNull KeyAction[] values() {
        List<KeyAction> mods = new ArrayList<KeyAction>();

        for (Field field : KeyAction.class.getFields()) {
            try {
                field.setAccessible(true);

                mods.add((KeyAction) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                continue;
            }
        }

        return mods.toArray(new KeyAction[] {});
    }

    /**
     * Get the key action corresponding to the inputted glfw event
     * 
     * @param id The id of the glfw event
     * 
     * @return The key action corresponding to the inputted glfw event
     * 
     * @since 1.2.0
     */
    public static @Nullable KeyAction valueOfGLFWEvent(@NotNull Integer id) {
        NullUtils.checkNulls(id, "id");

        for (KeyAction action : values()) {
            if (action.glfwEventId.equals(id)) {
                return action;
            }
        }

        return null;
    }
}