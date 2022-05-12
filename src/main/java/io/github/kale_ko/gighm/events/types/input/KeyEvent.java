package io.github.kale_ko.gighm.events.types.input;

import java.util.List;
import io.github.kale_ko.gighm.events.types.CancellableEvent;
import io.github.kale_ko.gighm.input.KeyAction;
import io.github.kale_ko.gighm.input.KeyCode;
import io.github.kale_ko.gighm.input.KeyMod;

/**
 * A key event
 * 
 * @version 1.6.0
 * @since 1.6.0
 */
public class KeyEvent extends CancellableEvent {
    /**
     * The key code
     * 
     * @since 1.6.0
     */
    private KeyCode code;

    /**
     * The key action
     * 
     * @since 1.6.0
     */
    private KeyAction action;

    /**
     * The key mods
     * 
     * @since 1.6.0
     */
    private List<KeyMod> mods;

    /**
     * Create a key event
     * 
     * @param code The key code
     * @param action The key action
     * @param mods The key mods
     * 
     * @since 1.6.0
     */
    public KeyEvent(KeyCode code, KeyAction action, List<KeyMod> mods) {
        this.code = code;
        this.action = action;
        this.mods = mods;
    }

    /**
     * Get the key code
     * 
     * @return The key code
     * 
     * @since 1.6.0
     */
    public KeyCode getCode() {
        return this.code;
    }

    /**
     * Get the key action
     * 
     * @return The key action
     * 
     * @since 1.6.0
     */
    public KeyAction getAction() {
        return this.action;
    }

    /**
     * Get the key mods
     * 
     * @return The key mods
     * 
     * @since 1.6.0
     */
    public List<KeyMod> getMods() {
        return this.mods;
    }
}