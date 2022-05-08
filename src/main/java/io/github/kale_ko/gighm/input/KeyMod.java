package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * A key modifier
 * 
 * @version 1.2.0
 * @since 1.2.0
 */
public enum KeyMod {
    SHIFT(GLFW_MOD_SHIFT),
    CONTROL(GLFW_MOD_CONTROL),
    ALT(GLFW_MOD_ALT),
    META(GLFW_MOD_SUPER),

    CAPS_LOCK(GLFW_MOD_CAPS_LOCK),
    NUM_LOCK(GLFW_MOD_NUM_LOCK);

    /**
     * The id of the glfw mod corresponding to the mod
     * 
     * @since 1.2.0
     */
    private final @NotNull int glfwModId;

    /**
     * Create a {@link KeyMod}
     * 
     * @param glfwModId The id of the glfw mod corresponding to the mod
     * 
     * @since 1.2.0
     */
    private KeyMod(@NotNull int glfwModId) {
        this.glfwModId = glfwModId;
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
    public static KeyMod valueOfGLFWEvent(@NotNull int id) {
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
    public static boolean isPressed(@NotNull KeyMod mod, @NotNull int mods) {
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
    public static List<KeyMod> getPressed(@NotNull int mods) {
        List<KeyMod> pressed = new ArrayList<KeyMod>();

        for (KeyMod mod : values()) {
            if (isPressed(mod, mods)) {
                pressed.add(mod);
            }
        }

        return pressed;
    }
}
