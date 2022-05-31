package io.github.kale_ko.gighm.util;

/**
 * A utility for checking if paramiters are null
 * 
 * @author Kale Ko
 * 
 * @version 1.8.0
 * @since 1.8.0
 */
public class NullUtils {
    /**
     * Check if an object is null without throwing an error
     * 
     * @param object The object to check
     * 
     * @return If the object is null or not
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
     * @param name The name of the paramitter
     * 
     * @return If the object is null or not
     * 
     * @throws NullPointerException If the object is null
     * 
     * @since 1.8.0
     */
    public static @NotNull Boolean checkNulls(@Nullable Object object, @NotNull String name) {
        if (!silentCheckNulls(object)) {
            throw new NullPointerException("Paramitter \"" + name + "\" can not be null");
        } else {
            return true;
        }
    }
}
