package io.github.kale_ko.gighm.rendering.objects;

import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A skybox object
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
 * @since 2.1.0
 */
public class Skybox {
    /**
     * The front texture of the skybox
     * 
     * @since 2.1.0
     */
    public Texture2D front;

    /**
     * The back texture of the skybox
     * 
     * @since 2.1.0
     */
    public Texture2D back;

    /**
     * The left texture of the skybox
     * 
     * @since 2.1.0
     */
    public Texture2D left;

    /**
     * The right texture of the skybox
     * 
     * @since 2.1.0
     */
    public Texture2D right;

    /**
     * The top texture of the skybox
     * 
     * @since 2.1.0
     */
    public Texture2D top;

    /**
     * The bottom texture of the skybox
     * 
     * @since 2.1.0
     */
    public Texture2D bottom;

    /**
     * Create a skybox object
     * 
     * @param front The front texture of the skybox
     * @param back The back texture of the skybox
     * @param left The left texture of the skybox
     * @param right The right texture of the skybox
     * @param top The top texture of the skybox
     * @param bottom The bottom texture of the skybox
     * 
     * @since 2.1.0
     */
    public Skybox(@NotNull Texture2D front, @NotNull Texture2D back, @NotNull Texture2D left, @NotNull Texture2D right, @NotNull Texture2D top, @NotNull Texture2D bottom) {
        NullUtils.checkNulls(front, "front");
        NullUtils.checkNulls(back, "back");
        NullUtils.checkNulls(left, "left");
        NullUtils.checkNulls(right, "right");
        NullUtils.checkNulls(top, "top");
        NullUtils.checkNulls(bottom, "bottom");

        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
}