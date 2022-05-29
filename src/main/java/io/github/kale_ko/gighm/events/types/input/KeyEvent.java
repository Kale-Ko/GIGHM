package io.github.kale_ko.gighm.events.types.input;

import java.util.List;
import io.github.kale_ko.gighm.events.types.CancellableEvent;
import io.github.kale_ko.gighm.input.KeyAction;
import io.github.kale_ko.gighm.input.KeyCode;
import io.github.kale_ko.gighm.input.KeyMod;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A key event
 * Fires whenever a keyboard key is pressed or released
 * 
 * @author Kale Ko
 * 
 * @version 1.6.0
 * @since 1.6.0
 */
public class KeyEvent extends CancellableEvent {
    /**
     * The code of the key pressed
     * 
     * @since 1.6.0
     */
    private @NotNull KeyCode code;

    /**
     * The action being performed (Press, Release, Repeat)
     * 
     * @since 1.6.0
     */
    private @NotNull KeyAction action;

    /**
     * The key mods being held
     * 
     * @since 1.6.0
     */
    private @NotNull List<KeyMod> mods;

    /**
     * Create a key event
     * 
     * @param code The code of the key pressed
     * @param action The action being performed (Press, Release, Repeat)
     * @param mods The key mods being held
     * 
     * @since 1.6.0
     */
    public KeyEvent(@NotNull KeyCode code, @NotNull KeyAction action, @NotNull List<KeyMod> mods) {
        NullUtils.checkNulls(code, "code");
        NullUtils.checkNulls(action, "action");
        NullUtils.checkNulls(mods, "mods");

        this.code = code;
        this.action = action;
        this.mods = mods;
    }

    /**
     * Get the code of the key pressed
     * 
     * @return The code of the key pressed
     * 
     * @since 1.6.0
     */
    public @NotNull KeyCode getCode() {
        return this.code;
    }

    /**
     * Get the action being performed (Press, Release, Repeat)
     * 
     * @return The action being performed (Press, Release, Repeat)
     * 
     * @since 1.6.0
     */
    public @NotNull KeyAction getAction() {
        return this.action;
    }

    /**
     * Get the key mods being held
     * 
     * @return The key mods being held
     * 
     * @since 1.6.0
     */
    public @NotNull List<KeyMod> getMods() {
        return this.mods;
    }
}