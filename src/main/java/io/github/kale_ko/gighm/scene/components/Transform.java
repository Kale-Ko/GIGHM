package io.github.kale_ko.gighm.scene.components;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * An objects transform (Position, rotation, and scale)
 * 
 * @author Kale Ko
 * 
 * @version 1.9.0
 * @since 1.0.0
 */
public class Transform extends Component {
    /**
     * The position of the object
     * 
     * @since 1.0.0
     */
    private @NotNull Vector3f position;

    /**
     * The rotation of the object
     * 
     * @since 1.0.0
     */
    private @NotNull Quaternionf rotation;

    /**
     * The scale of the object
     * 
     * @since 1.0.0
     */
    private @NotNull Vector3f scale;

    /**
     * Create a transform
     * 
     * @param position The position of the object
     * @param rotation The rotation of the object
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public Transform(@NotNull Vector2f position, @NotNull Float rotation, @NotNull Vector2f scale) {
        this(new Vector3f(position, 0), new Quaternionf().rotateXYZ(rotation, 0, 0), new Vector3f(scale, 0));
    }

    /**
     * Create a transform
     * 
     * @param position The position of the object
     * @param rotation The rotation of the object
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public Transform(@NotNull Vector3f position, @NotNull Vector3f rotation, @NotNull Vector3f scale) {
        this(position, new Quaternionf().rotateXYZ(rotation.x, rotation.y, rotation.z), scale);
    }

    /**
     * Create a transform
     * 
     * @param position The position of the object
     * @param rotation The rotation of the object
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public Transform(@NotNull Vector3f position, @NotNull Quaternionf rotation, @NotNull Vector3f scale) {
        NullUtils.checkNulls(position, "position");
        NullUtils.checkNulls(rotation, "rotation");
        NullUtils.checkNulls(scale, "scale");

        this.position = position;

        this.rotation = rotation;

        this.scale = scale;
    }

    /**
     * Get the position of the object
     * 
     * @return The position of the object
     * 
     * @since 1.0.0
     */
    public @NotNull Vector3f getPosition() {
        return this.position;
    }

    /**
     * Set the position of the object
     * 
     * @param position The position of the object
     * 
     * @since 1.0.0
     */
    public void setPosition(@NotNull Vector3f position) {
        NullUtils.checkNulls(position, "position");

        this.position = position;
    }

    /**
     * Set the position of the object
     * 
     * @param position The position of the object
     * 
     * @since 1.0.0
     */
    public void setPosition(@NotNull Vector2f position) {
        NullUtils.checkNulls(position, "position");

        this.setPosition(new Vector3f(position, 0));
    }

    /**
     * Get the rotation of the object
     * 
     * @return The rotation of the object
     * 
     * @since 1.0.0
     */
    public @NotNull Quaternionf getRotation() {
        return this.rotation;
    }

    /**
     * Set the rotation of the object
     * 
     * @param rotation The rotation of the object
     * 
     * @since 1.0.0
     */
    public void setRotation(@NotNull Quaternionf rotation) {
        NullUtils.checkNulls(rotation, "rotation");

        this.rotation = rotation;
    }

    /**
     * Set the rotation of the object
     * 
     * @param rotation The rotation of the object
     * 
     * @since 1.3.0
     */
    public void setRotation(@NotNull Vector3f rotation) {
        NullUtils.checkNulls(rotation, "rotation");

        this.setRotation(new Quaternionf().rotateXYZ(rotation.x, rotation.y, rotation.z));
    }

    /**
     * Set the rotation of the object
     * 
     * @param rotation The rotation of the object
     * 
     * @since 1.3.0
     */
    public void setRotation(@NotNull Float rotation) {
        NullUtils.checkNulls(rotation, "rotation");

        this.setRotation(new Quaternionf().rotateXYZ(rotation, 0, 0));
    }

    /**
     * Get the scale of the object
     * 
     * @return The scale of the object
     * 
     * @since 1.0.0
     */
    public @NotNull Vector3f getScale() {
        return this.scale;
    }

    /**
     * Set the scale of the object
     * 
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public void setScale(@NotNull Vector3f scale) {
        NullUtils.checkNulls(scale, "scale");

        this.scale = scale;
    }

    /**
     * Set the scale of the object
     * 
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public void setScale(@NotNull Vector2f scale) {
        NullUtils.checkNulls(scale, "scale");

        this.setScale(new Vector3f(scale, 1));
    }

    /**
     * Get the complete matrix of the transform
     * 
     * @return The complete matrix of the transform
     * 
     * @since 1.0.0
     */
    public @NotNull Matrix4f getMatrix() {
        return new Matrix4f().translate(this.position).rotate(this.rotation).scale(this.scale);
    }
}