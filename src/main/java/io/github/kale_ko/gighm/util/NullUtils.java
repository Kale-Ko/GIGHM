package io.github.kale_ko.gighm.util;

/**
 * A utility for checking if parameters are null
 * 
 * @author Kale Ko
 * 
 * @version 1.8.0
 * @since 1.8.0
 */
public class NullUtils {
    /**
     * Create an array utils
     * 
     * @since 1.8.0
     */
    private NullUtils() {}

    /**
     * Check if an object is null without throwing an error
     * 
     * @param object The object to check
     * 
     * @return False is the object is null true otherwise
     * 
     * @since 1.8.0
     */
    public static @NotNull Boolean silentCheckNulls(@Nullable Object object) {
        return object != null;
    }

    /**
     * Check if an object is null
     * 
     * @param object The object to check
     * @param name The name of the parameter
     * 
     * @return True if the object is not null 
     * 
     * @throws NullPointerException If the object is null
     * 
     * @since 1.8.0
     */
    public static @NotNull Boolean checkNulls(@Nullable Object object, @NotNull String name) throws NullPointerException {
        if (!silentCheckNulls(object)) {
            throw new NullPointerException("Parameter \"" + name + "\" can not be null");
        } else {
            return true;
        }
    }
}