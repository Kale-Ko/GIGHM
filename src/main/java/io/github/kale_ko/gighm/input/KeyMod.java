package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A key modifier
 * 
 * @version 1.6.0
 * @since 1.2.0
 */
public class KeyMod {
    /**
     * The left and right shift keys
     * 
     * @since 1.2.0
     */
    public static final KeyMod SHIFT = new KeyMod(GLFW_MOD_SHIFT);

    /**
     * The left and right control keys
     * 
     * @since 1.2.0
     */
    public static final KeyMod CONTROL = new KeyMod(GLFW_MOD_CONTROL);

    /**
     * The left and right alt keys
     * 
     * @since 1.2.0
     */
    public static final KeyMod ALT = new KeyMod(GLFW_MOD_ALT);

    /**
     * The left and right meta keys (Usually the windows key on windows or command on mac)
     * 
     * @since 1.2.0
     */
    public static final KeyMod META = new KeyMod(GLFW_MOD_SUPER);

    /**
     * The caps lock key (If activated, not help down)
     * 
     * @since 1.2.0
     */
    public static final KeyMod CAPS_LOCK = new KeyMod(GLFW_MOD_CAPS_LOCK);

    /**
     * The num lock key (If activated, not help down)
     * 
     * @since 1.2.0
     */
    public static final KeyMod NUM_LOCK = new KeyMod(GLFW_MOD_NUM_LOCK);

    /**
     * The id of the glfw mod corresponding to the mod
     * 
     * @since 1.2.0
     */
    private final int glfwModId;

    /**
     * Create a {@link KeyMod}
     * 
     * @param glfwModId The id of the glfw mod corresponding to the mod
     * 
     * @since 1.2.0
     */
    private KeyMod(int glfwModId) {
        this.glfwModId = glfwModId;
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
        for (Field field : KeyMod.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((KeyMod) field.get(null)).equals(this)) {
                    return field.getName();
                }

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return "KeyMod";
    }

    /**
     * Get all of the mods defined
     * 
     * @return All of the mods defined
     * 
     * @since 1.5.0
     */
    public static KeyMod[] values() {
        List<KeyMod> mods = new ArrayList<KeyMod>();

        for (Field field : KeyMod.class.getFields()) {
            try {
                field.setAccessible(true);

                mods.add((KeyMod) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return mods.toArray(new KeyMod[] {});
    }

    /**
     * Get the {@link KeyMod} associated with the inputted glfw mod
     * 
     * @param id The id of the glfw mod
     * 
     * @return The {@link KeyMod} corresponding with the inputted glfw mod
     * 
     * @since 1.2.0
     */
    public static KeyMod valueOfGLFWEvent(int id) {
        for (KeyMod action : values()) {
            if (action.glfwModId == id) {
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
    public static boolean isPressed(KeyMod mod, int mods) {
        return (mods & mod.glfwModId) > 0;
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
    public static List<KeyMod> getPressed(int mods) {
        List<KeyMod> pressed = new ArrayList<KeyMod>();

        for (KeyMod mod : values()) {
            if (isPressed(mod, mods)) {
                pressed.add(mod);
            }
        }

        return pressed;
    }
}