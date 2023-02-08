package io.github.kale_ko.gighm.util;

/**
 * A utility for converting arrays to from primitive/wrapper types
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 1.8.0
 */
public class ArrayUtils {
    /**
     * Create an array utils
     * 
     * @since 1.8.0
     */
    private ArrayUtils() {}

    /**
     * Transform an array from a primitive type to a wrapper type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull Byte[] toWrapper(@NotNull byte[] arr) {
        NullUtils.checkNulls(arr, "arr");

        Byte[] narr = new Byte[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (Byte) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a primitive type to a wrapper type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull Short[] toWrapper(@NotNull short[] arr) {
        NullUtils.checkNulls(arr, "arr");

        Short[] narr = new Short[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (Short) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a primitive type to a wrapper type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull Integer[] toWrapper(@NotNull int[] arr) {
        NullUtils.checkNulls(arr, "arr");

        Integer[] narr = new Integer[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (Integer) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a primitive type to a wrapper type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull Double[] toWrapper(@NotNull double[] arr) {
        NullUtils.checkNulls(arr, "arr");

        Double[] narr = new Double[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (Double) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a primitive type to a wrapper type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull Float[] toWrapper(@NotNull float[] arr) {
        NullUtils.checkNulls(arr, "arr");

        Float[] narr = new Float[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (Float) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a primitive type to a wrapper type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull Boolean[] toWrapper(@NotNull boolean[] arr) {
        NullUtils.checkNulls(arr, "arr");

        Boolean[] narr = new Boolean[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (Boolean) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a primitive type to a wrapper type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull Character[] toWrapper(@NotNull char[] arr) {
        NullUtils.checkNulls(arr, "arr");

        Character[] narr = new Character[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (Character) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a wrapper type to a primitive type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull byte[] toPrimitive(@NotNull Byte[] arr) {
        NullUtils.checkNulls(arr, "arr");

        byte[] narr = new byte[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (byte) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a wrapper type to a primitive type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull short[] toPrimitive(@NotNull Short[] arr) {
        NullUtils.checkNulls(arr, "arr");

        short[] narr = new short[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (short) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a wrapper type to a primitive type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull int[] toPrimitive(@NotNull Integer[] arr) {
        NullUtils.checkNulls(arr, "arr");

        int[] narr = new int[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (int) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a wrapper type to a primitive type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull double[] toPrimitive(@NotNull Double[] arr) {
        NullUtils.checkNulls(arr, "arr");

        double[] narr = new double[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (double) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a wrapper type to a primitive type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull float[] toPrimitive(@NotNull Float[] arr) {
        NullUtils.checkNulls(arr, "arr");

        float[] narr = new float[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (float) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a wrapper type to a primitive type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull boolean[] toPrimitive(@NotNull Boolean[] arr) {
        NullUtils.checkNulls(arr, "arr");

        boolean[] narr = new boolean[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (boolean) arr[i];
        }

        return narr;
    }

    /**
     * Transform an array from a wrapper type to a primitive type
     * 
     * @param arr The array to transform
     * 
     * @return The transformed array
     * 
     * @since 1.8.0
     */
    public static @NotNull char[] toPrimitive(@NotNull Character[] arr) {
        NullUtils.checkNulls(arr, "arr");

        char[] narr = new char[arr.length];

        for (Integer i = 0; i < arr.length; i++) {
            narr[i] = (char) arr[i];
        }

        return narr;
    }
}