package io.github.kale_ko.gighm.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * The actions a mouse button can have
 * 
 * @author Kale Ko
 * 
 * @version 1.5.0
 * @since 1.2.0
 */
public class MouseButtonAction {
    /**
     * A mouse button is held down
     * 
     * @since 1.2.0
     */
    public static final MouseButtonAction DOWN = new MouseButtonAction(GLFW.GLFW_PRESS);

    /**
     * A mouse button is released
     * 
     * @since 1.2.0
     */
    public static final MouseButtonAction UP = new MouseButtonAction(GLFW.GLFW_RELEASE);

    /**
     * The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private final @NotNull Integer glfwEventId;

    /**
     * Create a mouse action
     * 
     * @param glfwEventId The id of the glfw event corresponding with the action
     * 
     * @since 1.2.0
     */
    private MouseButtonAction(@NotNull Integer glfwEventId) {
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
        for (Field field : MouseButtonAction.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((MouseButtonAction) field.get(null)).equals(this)) {
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
    public static @NotNull MouseButtonAction[] values() {
        List<MouseButtonAction> mods = new ArrayList<MouseButtonAction>();

        for (Field field : MouseButtonAction.class.getFields()) {
            try {
                field.setAccessible(true);

                mods.add((MouseButtonAction) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                continue;
            }
        }

        return mods.toArray(new MouseButtonAction[] {});
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
    public static @Nullable MouseButtonAction valueOfGLFWEvent(@NotNull Integer id) {
        NullUtils.checkNulls(id, "id");

        for (MouseButtonAction action : values()) {
            if (action.glfwEventId.equals(id)) {
                return action;
            }
        }

        return null;
    }
}