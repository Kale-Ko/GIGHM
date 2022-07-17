package io.github.kale_ko.gighm.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * The keys that can be pressed
 * 
 * @author Kale Ko
 * 
 * @version 1.8.0
 * @since 1.2.0
 */
public class KeyCode {
    /**
     * Keys A-Z
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        A = new KeyCode(GLFW.GLFW_KEY_A, null, 'a', 'A'),
        B = new KeyCode(GLFW.GLFW_KEY_B, null, 'b', 'B'),
        C = new KeyCode(GLFW.GLFW_KEY_C, null, 'c', 'C'),
        D = new KeyCode(GLFW.GLFW_KEY_D, null, 'd', 'D'),
        E = new KeyCode(GLFW.GLFW_KEY_E, null, 'e', 'E'),
        F = new KeyCode(GLFW.GLFW_KEY_F, null, 'f', 'F'),
        G = new KeyCode(GLFW.GLFW_KEY_G, null, 'g', 'G'),
        H = new KeyCode(GLFW.GLFW_KEY_H, null, 'h', 'H'),
        I = new KeyCode(GLFW.GLFW_KEY_I, null, 'i', 'I'),
        J = new KeyCode(GLFW.GLFW_KEY_J, null, 'j', 'J'),
        K = new KeyCode(GLFW.GLFW_KEY_K, null, 'k', 'K'),
        L = new KeyCode(GLFW.GLFW_KEY_L, null, 'l', 'L'),
        M = new KeyCode(GLFW.GLFW_KEY_M, null, 'm', 'M'),
        N = new KeyCode(GLFW.GLFW_KEY_N, null, 'n', 'N'),
        O = new KeyCode(GLFW.GLFW_KEY_O, null, 'o', 'O'),
        P = new KeyCode(GLFW.GLFW_KEY_P, null, 'p', 'P'),
        Q = new KeyCode(GLFW.GLFW_KEY_Q, null, 'q', 'Q'),
        R = new KeyCode(GLFW.GLFW_KEY_R, null, 'r', 'R'),
        S = new KeyCode(GLFW.GLFW_KEY_S, null, 's', 'S'),
        T = new KeyCode(GLFW.GLFW_KEY_T, null, 't', 'T'),
        U = new KeyCode(GLFW.GLFW_KEY_U, null, 'u', 'U'),
        V = new KeyCode(GLFW.GLFW_KEY_V, null, 'v', 'V'),
        W = new KeyCode(GLFW.GLFW_KEY_W, null, 'w', 'W'),
        X = new KeyCode(GLFW.GLFW_KEY_X, null, 'x', 'X'),
        Y = new KeyCode(GLFW.GLFW_KEY_Y, null, 'y', 'Y'),
        Z = new KeyCode(GLFW.GLFW_KEY_Z, null, 'z', 'Z');

    /**
     * Keys 0-9
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        N1 = new KeyCode(GLFW.GLFW_KEY_1, false, '1'),
        N2 = new KeyCode(GLFW.GLFW_KEY_2, false, '2'),
        N3 = new KeyCode(GLFW.GLFW_KEY_3, false, '3'),
        N4 = new KeyCode(GLFW.GLFW_KEY_4, false, '4'),
        N5 = new KeyCode(GLFW.GLFW_KEY_5, false, '5'),
        N6 = new KeyCode(GLFW.GLFW_KEY_6, false, '6'),
        N7 = new KeyCode(GLFW.GLFW_KEY_7, false, '7'),
        N8 = new KeyCode(GLFW.GLFW_KEY_8, false, '8'),
        N9 = new KeyCode(GLFW.GLFW_KEY_9, false, '9'),
        N0 = new KeyCode(GLFW.GLFW_KEY_0, false, '0');

    /**
     * Numpad 0-9
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        NP1 = new KeyCode(GLFW.GLFW_KEY_KP_1, false, '1'),
        NP2 = new KeyCode(GLFW.GLFW_KEY_KP_2, false, '2'),
        NP3 = new KeyCode(GLFW.GLFW_KEY_KP_3, false, '3'),
        NP4 = new KeyCode(GLFW.GLFW_KEY_KP_4, false, '4'),
        NP5 = new KeyCode(GLFW.GLFW_KEY_KP_5, false, '5'),
        NP6 = new KeyCode(GLFW.GLFW_KEY_KP_6, false, '6'),
        NP7 = new KeyCode(GLFW.GLFW_KEY_KP_7, false, '7'),
        NP8 = new KeyCode(GLFW.GLFW_KEY_KP_8, false, '8'),
        NP9 = new KeyCode(GLFW.GLFW_KEY_KP_9, false, '9'),
        NP0 = new KeyCode(GLFW.GLFW_KEY_KP_0, false, '0');

    /**
     * Numpad Symbols
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        NP_DECIMAL = new KeyCode(GLFW.GLFW_KEY_KP_DECIMAL, null, '.'),
        NP_PLUS = new KeyCode(GLFW.GLFW_KEY_KP_ADD, null, '+'),
        NP_MINUS = new KeyCode(GLFW.GLFW_KEY_KP_SUBTRACT, null, '-'),
        NP_TIMES = new KeyCode(GLFW.GLFW_KEY_KP_MULTIPLY, null, '*'),
        NP_DIVIDE = new KeyCode(GLFW.GLFW_KEY_KP_DIVIDE, null, '/'),
        NP_EQUALS = new KeyCode(GLFW.GLFW_KEY_KP_EQUAL, null, '=');

    /**
     * Numpad Actions
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        NP_ENTER = new KeyCode(GLFW.GLFW_KEY_KP_ENTER, null, '\r');

    /**
     * Punctuation
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        COMMA = new KeyCode(GLFW.GLFW_KEY_COMMA, false, ','),
        PERIOD = new KeyCode(GLFW.GLFW_KEY_PERIOD, false, '.'),
        QUESTION = new KeyCode(GLFW.GLFW_KEY_SLASH, true, '?'),
        SLASH = new KeyCode(GLFW.GLFW_KEY_SLASH, false, '/'),
        COLON = new KeyCode(GLFW.GLFW_KEY_SEMICOLON, true, ':'),
        SEMICOLON = new KeyCode(GLFW.GLFW_KEY_SEMICOLON, false, ';'),
        APOSTROPHE = new KeyCode(GLFW.GLFW_KEY_APOSTROPHE, false, '\''),
        QUOTE = new KeyCode(GLFW.GLFW_KEY_APOSTROPHE, true, '"');

    /**
     * Symbols
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        LESS_THAN = new KeyCode(GLFW.GLFW_KEY_COMMA, true, '<'),
        GREATER_THAN = new KeyCode(GLFW.GLFW_KEY_PERIOD, true, '>'),
        LEFT_BRACKET = new KeyCode(GLFW.GLFW_KEY_LEFT_BRACKET, false, '['),
        RIGHT_BRACKET = new KeyCode(GLFW.GLFW_KEY_RIGHT_BRACKET, false, ']'),
        LEFT_CURLY_BRACKET = new KeyCode(GLFW.GLFW_KEY_LEFT_BRACKET, true, '{'),
        RIGHT_CURLY_BRACKET = new KeyCode(GLFW.GLFW_KEY_RIGHT_BRACKET, true, '}'),
        BACK_SLASH = new KeyCode(GLFW.GLFW_KEY_BACKSLASH, false, '\\'),
        VERTICAL_BAR = new KeyCode(GLFW.GLFW_KEY_BACKSLASH, true, '|'),
        PLUS = new KeyCode(GLFW.GLFW_KEY_EQUAL, true, '+'),
        MINUS = new KeyCode(GLFW.GLFW_KEY_MINUS, false, '-'),
        EQUALS = new KeyCode(GLFW.GLFW_KEY_EQUAL, false, '='),
        UNDERSCORE = new KeyCode(GLFW.GLFW_KEY_MINUS, true, '_'),
        BACK_TICK = new KeyCode(GLFW.GLFW_KEY_GRAVE_ACCENT, false, '`'),
        TILDE = new KeyCode(GLFW.GLFW_KEY_GRAVE_ACCENT, true, '~'),
        EXCLAMATION = new KeyCode(GLFW.GLFW_KEY_1, true, '!'),
        AT = new KeyCode(GLFW.GLFW_KEY_2, true, '@'),
        POUND = new KeyCode(GLFW.GLFW_KEY_3, true, '#'),
        DOLLAR = new KeyCode(GLFW.GLFW_KEY_4, true, '$'),
        PERCENT = new KeyCode(GLFW.GLFW_KEY_5, true, '%'),
        CARET = new KeyCode(GLFW.GLFW_KEY_6, true, '^'),
        AMPERSAND = new KeyCode(GLFW.GLFW_KEY_7, true, '&'),
        ASTERISK = new KeyCode(GLFW.GLFW_KEY_8, true, '*'),
        LEFT_PARENTHESES = new KeyCode(GLFW.GLFW_KEY_9, true, '('),
        RIGHT_PARENTHESES = new KeyCode(GLFW.GLFW_KEY_0, true, ')');

    /**
     * Special Characters
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        SPACE = new KeyCode(GLFW.GLFW_KEY_SPACE, null, ' '),
        BACKSPACE = new KeyCode(GLFW.GLFW_KEY_BACKSPACE, null, '\b'),
        ENTER = new KeyCode(GLFW.GLFW_KEY_ENTER, null, '\r'),
        TAB = new KeyCode(GLFW.GLFW_KEY_TAB, null, '\t');

    /**
     * Arrow Keys
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        LEFT_ARROW = new KeyCode(GLFW.GLFW_KEY_LEFT),
        RIGHT_ARROW = new KeyCode(GLFW.GLFW_KEY_RIGHT),
        UP_ARROW = new KeyCode(GLFW.GLFW_KEY_UP),
        DOWN_ARROW = new KeyCode(GLFW.GLFW_KEY_DOWN);

    /**
     * Navigation Keys
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        PAGE_UP = new KeyCode(GLFW.GLFW_KEY_PAGE_UP),
        PAGE_DOWN = new KeyCode(GLFW.GLFW_KEY_PAGE_DOWN),
        HOME = new KeyCode(GLFW.GLFW_KEY_HOME),
        END = new KeyCode(GLFW.GLFW_KEY_END);

    /**
     * Action Keys
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        ESCAPE = new KeyCode(GLFW.GLFW_KEY_ESCAPE),
        INSERT = new KeyCode(GLFW.GLFW_KEY_INSERT),
        DELETE = new KeyCode(GLFW.GLFW_KEY_DELETE),
        PRINT = new KeyCode(GLFW.GLFW_KEY_PRINT_SCREEN),
        PAUSE = new KeyCode(GLFW.GLFW_KEY_PAUSE),
        MENU = new KeyCode(GLFW.GLFW_KEY_MENU);

    /**
     * Function keys
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        F1 = new KeyCode(GLFW.GLFW_KEY_F1),
        F2 = new KeyCode(GLFW.GLFW_KEY_F2),
        F3 = new KeyCode(GLFW.GLFW_KEY_F3),
        F4 = new KeyCode(GLFW.GLFW_KEY_F4),
        F5 = new KeyCode(GLFW.GLFW_KEY_F5),
        F6 = new KeyCode(GLFW.GLFW_KEY_F6),
        F7 = new KeyCode(GLFW.GLFW_KEY_F7),
        F8 = new KeyCode(GLFW.GLFW_KEY_F8),
        F9 = new KeyCode(GLFW.GLFW_KEY_F9),
        F10 = new KeyCode(GLFW.GLFW_KEY_F10),
        F11 = new KeyCode(GLFW.GLFW_KEY_F11),
        F12 = new KeyCode(GLFW.GLFW_KEY_F12),
        F13 = new KeyCode(GLFW.GLFW_KEY_F13),
        F14 = new KeyCode(GLFW.GLFW_KEY_F14),
        F15 = new KeyCode(GLFW.GLFW_KEY_F15),
        F16 = new KeyCode(GLFW.GLFW_KEY_F16),
        F17 = new KeyCode(GLFW.GLFW_KEY_F17),
        F18 = new KeyCode(GLFW.GLFW_KEY_F18),
        F19 = new KeyCode(GLFW.GLFW_KEY_F19),
        F20 = new KeyCode(GLFW.GLFW_KEY_F20),
        F21 = new KeyCode(GLFW.GLFW_KEY_F21),
        F22 = new KeyCode(GLFW.GLFW_KEY_F22),
        F23 = new KeyCode(GLFW.GLFW_KEY_F23),
        F24 = new KeyCode(GLFW.GLFW_KEY_F24),
        F25 = new KeyCode(GLFW.GLFW_KEY_F25);

    /**
     * Mod Keys
     * 
     * @since 1.2.0
     */
    public static final KeyCode
        LEFT_SHIFT = new KeyCode(GLFW.GLFW_KEY_LEFT_SHIFT),
        RIGHT_SHIFT = new KeyCode(GLFW.GLFW_KEY_RIGHT_SHIFT),
        LEFT_CONTROL = new KeyCode(GLFW.GLFW_KEY_LEFT_CONTROL),
        RIGHT_CONTROL = new KeyCode(GLFW.GLFW_KEY_RIGHT_CONTROL),
        LEFT_ALT = new KeyCode(GLFW.GLFW_KEY_LEFT_ALT),
        RIGHT_ALT = new KeyCode(GLFW.GLFW_KEY_RIGHT_ALT),
        LEFT_META = new KeyCode(GLFW.GLFW_KEY_LEFT_SUPER),
        RIGHT_META = new KeyCode(GLFW.GLFW_KEY_RIGHT_SUPER),
        CAPS_LOCK = new KeyCode(GLFW.GLFW_KEY_CAPS_LOCK),
        NUM_LOCK = new KeyCode(GLFW.GLFW_KEY_NUM_LOCK),
        SCROLL_LOCK = new KeyCode(GLFW.GLFW_KEY_SCROLL_LOCK);

    /**
     * The id of the glfw key corresponding with the key code
     * 
     * @since 1.2.0
     */
    private final @NotNull Integer glfwKeyId;

    /**
     * Weather it is the uppercase version
     * 
     * @since 1.2.0
     */
    private final @Nullable Boolean shift;

    /**
     * The typed character of the key code
     * 
     * @since 1.2.0
     */
    private final @Nullable Character character;

    /**
     * The uppercase typed character of the key code
     * 
     * @since 1.2.0
     */
    private final @Nullable Character upperCharacter;

    /**
     * Create a key code
     * 
     * @param glfwKeyId The id of the glfw key corresponding with the key code
     * 
     * @since 1.2.0
     */
    private KeyCode(@NotNull Integer glfwKeyId) {
        this(glfwKeyId, null, null, null);
    }

    /**
     * Create a key code
     * 
     * @param glfwKeyId The id of the glfw key corresponding with the key code
     * @param shift Weather it is the uppercase version
     * @param character The typed character of the key code
     * 
     * @since 1.2.0
     */
    private KeyCode(@NotNull Integer glfwKeyId, @Nullable Boolean shift, @Nullable Character character) {
        this(glfwKeyId, shift, character, null);
    }

    /**
     * Create a key code
     * 
     * @param glfwKeyId The id of the glfw key corresponding with the key code
     * @param shift Weather it is the uppercase version
     * @param character The typed character of the key code
     * @param upperCharacter The uppercase typed character of the key code
     * 
     * @since 1.2.0
     */
    private KeyCode(@NotNull Integer glfwKeyId, @Nullable Boolean shift, @Nullable Character character, @Nullable Character upperCharacter) {
        NullUtils.checkNulls(glfwKeyId, "glfwKeyId");

        this.glfwKeyId = glfwKeyId;
        this.shift = shift;

        this.character = character;
        this.upperCharacter = upperCharacter;
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
        for (Field field : KeyCode.class.getFields()) {
            try {
                field.setAccessible(true);

                if (((KeyCode) field.get(null)).equals(this)) {
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
     * Get all of the keys defined
     * 
     * @return All of the keys defined
     * 
     * @since 1.5.0
     */
    public static @NotNull KeyCode[] values() {
        List<KeyCode> keys = new ArrayList<KeyCode>();

        for (Field field : KeyCode.class.getFields()) {
            try {
                field.setAccessible(true);

                keys.add((KeyCode) field.get(null));

                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                continue;
            }
        }

        return keys.toArray(new KeyCode[] {});
    }

    /**
     * Get the key code corresponding to the inputted character
     * 
     * @param character The character
     * 
     * @return The key code corresponding to the inputted character
     * 
     * @since 1.2.0
     */
    public static @Nullable KeyCode valueOfChar(@NotNull Character character) {
        NullUtils.checkNulls(character, "character");

        for (KeyCode code : values()) {
            if (code.character.equals(character) || code.upperCharacter.equals(character)) {
                return code;
            }
        }

        return null;
    }

    /**
     * Get the key corresponding to the inputted glfw key code
     * 
     * @param id The id of the glfw key
     * @param shift Weather it is the uppercase version
     * 
     * @return The key corresponding to the inputted glfw key code
     * 
     * @since 1.2.0
     */
    public static KeyCode valueOfGLFWKey(@NotNull Integer id, @NotNull Boolean shift) {
        NullUtils.checkNulls(id, "id");
        NullUtils.checkNulls(shift, "shift");

        for (KeyCode code : values()) {
            if (code.glfwKeyId.equals(id) && (code.shift == null || code.shift.equals(shift))) {
                return code;
            }
        }

        return null;
    }
}