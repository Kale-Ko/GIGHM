package io.github.kale_ko.gighm.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * A key code
 * 
 * @version 1.2.0
 * @since 1.2.0
 */
public enum KeyCode {
    // Keys A-Z
    A(GLFW_KEY_A, null, 'a', 'A'),
    B(GLFW_KEY_B, null, 'b', 'B'),
    C(GLFW_KEY_C, null, 'c', 'C'),
    D(GLFW_KEY_D, null, 'd', 'D'),
    E(GLFW_KEY_E, null, 'e', 'E'),
    F(GLFW_KEY_F, null, 'f', 'F'),
    G(GLFW_KEY_G, null, 'g', 'G'),
    H(GLFW_KEY_H, null, 'h', 'H'),
    I(GLFW_KEY_I, null, 'i', 'I'),
    J(GLFW_KEY_J, null, 'j', 'J'),
    K(GLFW_KEY_K, null, 'k', 'K'),
    L(GLFW_KEY_L, null, 'l', 'L'),
    M(GLFW_KEY_M, null, 'm', 'M'),
    N(GLFW_KEY_N, null, 'n', 'N'),
    O(GLFW_KEY_O, null, 'o', 'O'),
    P(GLFW_KEY_P, null, 'p', 'P'),
    Q(GLFW_KEY_Q, null, 'q', 'Q'),
    R(GLFW_KEY_R, null, 'r', 'R'),
    S(GLFW_KEY_S, null, 's', 'S'),
    T(GLFW_KEY_T, null, 't', 'T'),
    U(GLFW_KEY_U, null, 'u', 'U'),
    V(GLFW_KEY_V, null, 'v', 'V'),
    W(GLFW_KEY_W, null, 'w', 'W'),
    X(GLFW_KEY_X, null, 'x', 'X'),
    Y(GLFW_KEY_Y, null, 'y', 'Y'),
    Z(GLFW_KEY_Z, null, 'z', 'Z'),

    // Keys 0-9
    N1(GLFW_KEY_1, false, '1'),
    N2(GLFW_KEY_2, false, '2'),
    N3(GLFW_KEY_3, false, '3'),
    N4(GLFW_KEY_4, false, '4'),
    N5(GLFW_KEY_5, false, '5'),
    N6(GLFW_KEY_6, false, '6'),
    N7(GLFW_KEY_7, false, '7'),
    N8(GLFW_KEY_8, false, '8'),
    N9(GLFW_KEY_9, false, '9'),
    N0(GLFW_KEY_0, false, '0'),

    // Numpad 0-9
    NP1(GLFW_KEY_KP_1, false, '1'),
    NP2(GLFW_KEY_KP_2, false, '2'),
    NP3(GLFW_KEY_KP_3, false, '3'),
    NP4(GLFW_KEY_KP_4, false, '4'),
    NP5(GLFW_KEY_KP_5, false, '5'),
    NP6(GLFW_KEY_KP_6, false, '6'),
    NP7(GLFW_KEY_KP_7, false, '7'),
    NP8(GLFW_KEY_KP_8, false, '8'),
    NP9(GLFW_KEY_KP_9, false, '9'),
    NP0(GLFW_KEY_KP_0, false, '0'),

    // Numpad Symbols
    NP_DECIMAL(GLFW_KEY_KP_DECIMAL, null, '.'),
    NP_PLUS(GLFW_KEY_KP_ADD, null, '+'),
    NP_MINUS(GLFW_KEY_KP_SUBTRACT, null, '-'),
    NP_TIMES(GLFW_KEY_KP_MULTIPLY, null, '*'),
    NP_DIVIDE(GLFW_KEY_KP_DIVIDE, null, '/'),
    NP_EQUALS(GLFW_KEY_KP_EQUAL, null, '='),

    // Numpad Actions
    NP_ENTER(GLFW_KEY_KP_ENTER, null, '\r'),

    // Punctuation
    COMMA(GLFW_KEY_COMMA, false, ','),
    PERIOD(GLFW_KEY_PERIOD, false, '.'),
    QUESTION(GLFW_KEY_SLASH, true, '?'),
    SLASH(GLFW_KEY_SLASH, false, '/'),
    COLON(GLFW_KEY_SEMICOLON, true, ':'),
    SEMICOLON(GLFW_KEY_SEMICOLON, false, ';'),
    APOSTROPHE(GLFW_KEY_APOSTROPHE, false, '\''),
    QUOTE(GLFW_KEY_APOSTROPHE, true, '"'),

    // Symbols
    LESS_THAN(GLFW_KEY_COMMA, true, '<'),
    GREATER_THAN(GLFW_KEY_PERIOD, true, '>'),
    LEFT_BRACKET(GLFW_KEY_LEFT_BRACKET, false, '['),
    RIGHT_BRACKET(GLFW_KEY_RIGHT_BRACKET, false, ']'),
    LEFT_CURLY_BRACKET(GLFW_KEY_LEFT_BRACKET, true, '{'),
    RIGHT_CURLY_BRACKET(GLFW_KEY_RIGHT_BRACKET, true, '}'),
    BACK_SLASH(GLFW_KEY_BACKSLASH, false, '\\'),
    VERTICLE_BAR(GLFW_KEY_BACKSLASH, true, '|'),
    PLUS(GLFW_KEY_EQUAL, true, '+'),
    MINUS(GLFW_KEY_MINUS, false, '-'),
    EQUALS(GLFW_KEY_EQUAL, false, '='),
    UNDERSCORE(GLFW_KEY_MINUS, true, '_'),
    BACK_TICK(GLFW_KEY_GRAVE_ACCENT, false, '`'),
    TILDE(GLFW_KEY_GRAVE_ACCENT, true, '~'),
    EXCLAMATION(GLFW_KEY_1, true, '!'),
    AT(GLFW_KEY_2, true, '@'),
    POUND(GLFW_KEY_3, true, '#'),
    DOLLAR(GLFW_KEY_4, true, '$'),
    PERCENT(GLFW_KEY_5, true, '%'),
    CARET(GLFW_KEY_6, true, '^'),
    AMPERSAND(GLFW_KEY_7, true, '&'),
    ASTERISK(GLFW_KEY_8, true, '*'),
    LEFT_PARENTHESES(GLFW_KEY_9, true, '('),
    RIGHT_PARENTHESES(GLFW_KEY_0, true, ')'),

    // Special Charecters
    SPACE(GLFW_KEY_SPACE, null, ' '),
    BACKSPACE(GLFW_KEY_BACKSPACE, null, '\b'),
    ENTER(GLFW_KEY_ENTER, null, '\r'),
    TAB(GLFW_KEY_TAB, null, '\t'),

    // Arrow Keys
    LEFT_ARROW(GLFW_KEY_LEFT),
    RIGHT_ARROW(GLFW_KEY_RIGHT),
    UP_ARROW(GLFW_KEY_UP),
    DOWN_ARROW(GLFW_KEY_DOWN),

    PAGE_UP(GLFW_KEY_PAGE_UP),
    PAGE_DOWN(GLFW_KEY_PAGE_DOWN),
    HOME(GLFW_KEY_HOME),
    END(GLFW_KEY_END),

    // Action Keys
    ESCAPE(GLFW_KEY_ESCAPE),
    INSERT(GLFW_KEY_INSERT),
    DELETE(GLFW_KEY_DELETE),

    PRINT(GLFW_KEY_PRINT_SCREEN),

    PAUSE(GLFW_KEY_PAUSE),

    F1(GLFW_KEY_F1),
    F2(GLFW_KEY_F2),
    F3(GLFW_KEY_F3),
    F4(GLFW_KEY_F4),
    F5(GLFW_KEY_F5),
    F6(GLFW_KEY_F6),
    F7(GLFW_KEY_F7),
    F8(GLFW_KEY_F8),
    F9(GLFW_KEY_F9),
    F10(GLFW_KEY_F10),
    F11(GLFW_KEY_F11),
    F12(GLFW_KEY_F12),
    F13(GLFW_KEY_F13),
    F14(GLFW_KEY_F14),
    F15(GLFW_KEY_F15),
    F16(GLFW_KEY_F16),
    F17(GLFW_KEY_F17),
    F18(GLFW_KEY_F18),
    F19(GLFW_KEY_F19),
    F20(GLFW_KEY_F20),
    F21(GLFW_KEY_F21),
    F22(GLFW_KEY_F22),
    F23(GLFW_KEY_F23),
    F24(GLFW_KEY_F24),
    F25(GLFW_KEY_F25),

    CAPS_LOCK(GLFW_KEY_CAPS_LOCK),
    NUM_LOCK(GLFW_KEY_NUM_LOCK),
    SCROLL_LOCK(GLFW_KEY_SCROLL_LOCK),

    MENU(GLFW_KEY_MENU),

    // Mod Keys
    LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT),
    RIGHT_SHIFT(GLFW_KEY_RIGHT_SHIFT),
    LEFT_CONTROL(GLFW_KEY_LEFT_CONTROL),
    RIGHT_CONTROL(GLFW_KEY_RIGHT_CONTROL),
    LEFT_ALT(GLFW_KEY_LEFT_ALT),
    RIGHT_ALT(GLFW_KEY_RIGHT_ALT),
    LEFT_META(GLFW_KEY_LEFT_SUPER),
    RIGHT_META(GLFW_KEY_RIGHT_SUPER),

    // Unknown
    UNKNOWN(GLFW_KEY_UNKNOWN);

    /**
     * The id of the glfw key corresponding with the key code
     * 
     * @since 1.2.0
     */
    private final int glfwKeyId;

    /**
     * Weather it is the uppercase version
     * 
     * @since 1.2.0
     */
    private final Boolean shift;

    /**
     * The typed charecter of the key code
     * 
     * @since 1.2.0
     */
    private final char charecter;

    /**
     * The typed uppercase charecter of the key code
     * 
     * @since 1.2.0
     */
    private final char upperCharecter;

    /**
     * Create a {@link KeyCode}
     * 
     * @param glfwKeyId The id of the glfw key corresponding with the key code
     * 
     * @since 1.2.0
     */
    private KeyCode(int glfwKeyId) {
        this.glfwKeyId = glfwKeyId;
        this.shift = null;

        this.charecter = '\u0000';
        this.upperCharecter = '\u0000';
    }

    /**
     * Create a {@link KeyCode}
     * 
     * @param glfwKeyId The id of the glfw key corresponding with the key code
     * @param shift Weather it is the uppercase version
     * @param charecter The typed charecter of the key code
     * 
     * @since 1.2.0
     */
    private KeyCode(int glfwKeyId, Boolean shift, char charecter) {
        this.glfwKeyId = glfwKeyId;
        this.shift = shift;

        this.charecter = charecter;
        this.upperCharecter = '\u0000';
    }

    /**
     * Create a {@link KeyCode}
     * 
     * @param glfwKeyId The id of the glfw key corresponding with the key code
     * @param shift Weather it is the uppercase version
     * @param charecter The typed charecter of the key code
     * @param upperCharecter The typed uppercase charecter of the key code
     * 
     * @since 1.2.0
     */
    private KeyCode(int glfwKeyId, Boolean shift, char charecter, char upperCharecter) {
        this.glfwKeyId = glfwKeyId;
        this.shift = shift;

        this.charecter = charecter;
        this.upperCharecter = upperCharecter;
    }

    /**
     * Get the {@link KeyCode} associated with the inputted charrecter
     * 
     * @param charecter The charecter
     * 
     * @return The {@link KeyCode} corresponding with the inputted charecter
     * 
     * @since 1.2.0
     */
    public static KeyCode valueOfChar(char charecter) {
        for (KeyCode code : values()) {
            if (code.charecter == charecter || code.upperCharecter == charecter) {
                return code;
            }
        }

        return KeyCode.UNKNOWN;
    }

    /**
     * Get the {@link KeyCode} associated with the inputted glfw key
     * 
     * @param id The id of the glfw key
     * @param shift Weather it is uppercase
     * 
     * @return The {@link KeyCode} corresponding with the inputted glfw key
     * 
     * @since 1.2.0
     */
    public static KeyCode valueOfGLFWKey(int id, boolean shift) {
        for (KeyCode code : values()) {
            if (code.glfwKeyId == id && (code.shift == null || code.shift == shift)) {
                return code;
            }
        }

        return KeyCode.UNKNOWN;
    }
}