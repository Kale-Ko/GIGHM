package io.github.kale_ko.gighm.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * The modifier keys that can be pressed
 * 
 * @author Kale Ko
 * 
 * @version 1.7.0
 * @since 1.2.0
 */
public class KeyMod {
    /**
     * The left and right shift keys
     * 
     * @since 1.2.0
     */
    public static final KeyMod SHIFT = new KeyMod(GLFW.GLFW_MOD_SHIFT);

    /**
     * The left and right control keys
     * 
     * @since 1.2.0
     */
    public static final KeyMod CONTROL = new KeyMod(GLFW.GLFW_MOD_CONTROL);

    /**
     * The left and right alt keys
     * 
     * @since 1.2.0
     */
    public static final KeyMod ALT = new KeyMod(GLFW.GLFW_MOD_ALT);

    /**
     * The left and right meta keys (Usually the windows key on windows or command on mac)
     * 
     * @since 1.2.0
     */
    public static final KeyMod META = new KeyMod(GLFW.GLFW_MOD_SUPER);

    /**
     * The caps lock key (If activated, not held down)
     * 
     * @since 1.2.0
     */
    public static final KeyMod CAPS_LOCK = new KeyMod(GLFW.GLFW_MOD_CAPS_LOCK);

    /**
     * The num lock key (If activated, not held down)
     * 
     * @since 1.2.0
     */
    public static final KeyMod NUM_LOCK = new KeyMod(GLFW.GLFW_MOD_NUM_LOCK);

    /**
     * The id of the glfw mod corresponding to the mod
     * 
     * @since 1.2.0
     */
    private final @NotNull Integer glfwModId;

    /**
     * Create a key mod
     * 
     * @param glfwModId The id of the glfw mod corresponding to the mod
     * 
     * @since 1.2.0
     */
    private KeyMod(@NotNull Integer glfwModId) {
        NullUtils.checkNulls(glfwModId, "glfwModId");

        this.glfwModId = glfwModId;
    }

    /**
     * Get weather the mod is pressed in a passed mods value
     * 
     * @param mods The mods to check
     * 
     * @return Weather the mod is pressed in the passed mods value
     * 
     * @since 1.2.0
     */
    public Boolean isPressed(Integer mods) {
        return (mods & this.glfwModId) > 0;
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
        for (Field field : KeyMod.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((KeyMod) field.get(null)).equals(this)) {
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
     * Get all of the mods defined
     * 
     * @return All of the mods defined
     * 
     * @since 1.5.0
     */
    public static @NotNull KeyMod[] values() {
        List<KeyMod> mods = new ArrayList<KeyMod>();

        for (Field field : KeyMod.class.getFields()) {
            try {
                field.setAccessible(true);

                mods.add((KeyMod) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                continue;
            }
        }

        return mods.toArray(new KeyMod[] {});
    }

    /**
     * Get the mod corresponding to the inputted glfw mod
     * 
     * @param id The id of the glfw mod
     * 
     * @return The mod corresponding to the inputted glfw mod
     * 
     * @since 1.2.0
     */
    public static @Nullable KeyMod valueOfGLFWMod(@NotNull Integer id) {
        NullUtils.checkNulls(id, "id");

        for (KeyMod action : values()) {
            if (action.glfwModId.equals(id)) {
                return action;
            }
        }

        return null;
    }

    /**
     * Get weather a mod is pressed in a passed mods value
     * 
     * @param mod The mod to check for
     * @param mods The mods to check
     * 
     * @return Weather a mod is pressed in the passed mods value
     * 
     * @since 1.2.0
     */
    public static Boolean isPressed(KeyMod mod, Integer mods) {
        return mod.isPressed(mods);
    }

    /**
     * Get all pressed mods in a passed mods value
     * 
     * @param mods The mods to check
     * 
     * @return All pressed mods in a passed mods value
     * 
     * @since 1.2.0
     */
    public static List<KeyMod> getPressed(Integer mods) {
        List<KeyMod> pressed = new ArrayList<KeyMod>();

        for (KeyMod mod : values()) {
            if (mod.isPressed(mods)) {
                pressed.add(mod);
            }
        }

        return pressed;
    }
}