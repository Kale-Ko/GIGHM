package io.github.kale_ko.gighm.events.types.input;

import java.util.List;
import io.github.kale_ko.gighm.events.types.CancellableEvent;
import io.github.kale_ko.gighm.input.KeyMod;
import io.github.kale_ko.gighm.input.MouseButton;
import io.github.kale_ko.gighm.input.MouseButtonAction;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A mouse button event
 * Fires whenever a mouse button is pressed or released
 * 
 * @author Kale Ko
 * 
 * @version 2.4.0
 * @since 1.6.0
 */
public class MouseButtonEvent extends CancellableEvent {
    /**
     * The code of the button pressed
     * 
     * @since 1.6.0
     */
    protected @NotNull MouseButton button;

    /**
     * The action being performed (Press, Release)
     * 
     * @since 1.6.0
     */
    protected @NotNull MouseButtonAction action;

    /**
     * The key mods being held
     * 
     * @since 1.8.0
     */
    protected @NotNull List<KeyMod> mods;

    /**
     * Create a mouse button event
     * 
     * @param button The code of the button pressed
     * @param action The action being performed (Press, Release)
     * @param mods The key mods being held
     * 
     * @since 1.6.0
     */
    public MouseButtonEvent(@NotNull MouseButton button, @NotNull MouseButtonAction action, @NotNull List<KeyMod> mods) {
        NullUtils.checkNulls(button, "button");
        NullUtils.checkNulls(action, "action");
        NullUtils.checkNulls(mods, "mods");

        this.button = button;
        this.action = action;
        this.mods = mods;
    }

    /**
     * Get the code of the button pressed
     * 
     * @return The code of the button pressed
     * 
     * @since 1.6.0
     */
    public @NotNull MouseButton getButton() {
        return this.button;
    }

    /**
     * Get the action being performed (Press, Release)
     * 
     * @return The action being performed (Press, Release)
     * 
     * @since 1.6.0
     */
    public @NotNull MouseButtonAction getAction() {
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