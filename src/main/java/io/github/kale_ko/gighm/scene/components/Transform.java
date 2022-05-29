package io.github.kale_ko.gighm.scene.components;

import org.joml.Matrix4f;
import org.joml.Quaterniond;
import org.joml.Quaternionf;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector3f;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * An objects transform (Position, rotation, and scale)
 * 
 * @author Kale Ko
 * 
 * @version 1.3.0
 * @since 1.0.0
 */
public class Transform extends Component {
    /**
     * The position of the object
     * 
     * @since 1.0.0
     */
    private @NotNull Vector3d position;

    /**
     * The rotation of the object
     * 
     * @since 1.0.0
     */
    private @NotNull Quaterniond rotation;

    /**
     * The scale of the object
     * 
     * @since 1.0.0
     */
    private @NotNull Vector3d scale;

    /**
     * Create a transform
     * 
     * @param position The position of the object
     * @param rotation The rotation of the object
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public Transform(@NotNull Vector2d position, @NotNull Double rotation, @NotNull Vector2d scale) {
        this(new Vector3d(position, 0), new Quaterniond().rotateXYZ(rotation, 0, 0), new Vector3d(scale, 0));
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
    public Transform(@NotNull Vector3d position, @NotNull Vector3d rotation, @NotNull Vector3d scale) {
        this(position, new Quaterniond().rotateXYZ(rotation.x, rotation.y, rotation.z), scale);
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
    public Transform(@NotNull Vector3d position, @NotNull Quaterniond rotation, @NotNull Vector3d scale) {
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
    public @NotNull Vector3d getPosition() {
        return this.position;
    }

    /**
     * Set the position of the object
     * 
     * @param position The position of the object
     * 
     * @since 1.0.0
     */
    public void setPosition(@NotNull Vector3d position) {
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
    public void setPosition(@NotNull Vector2d position) {
        NullUtils.checkNulls(position, "position");

        this.setPosition(new Vector3d(position, 0));
    }

    /**
     * Get the rotation of the object
     * 
     * @return The rotation of the object
     * 
     * @since 1.0.0
     */
    public @NotNull Quaterniond getRotation() {
        return this.rotation;
    }

    /**
     * Set the rotation of the object
     * 
     * @param rotation The rotation of the object
     * 
     * @since 1.0.0
     */
    public void setRotation(@NotNull Quaterniond rotation) {
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
    public void setRotation(@NotNull Vector3d rotation) {
        NullUtils.checkNulls(rotation, "rotation");

        this.setRotation(new Quaterniond().rotateXYZ(rotation.x, rotation.y, rotation.z));
    }

    /**
     * Set the rotation of the object
     * 
     * @param rotation The rotation of the object
     * 
     * @since 1.3.0
     */
    public void setRotation(@NotNull Double rotation) {
        NullUtils.checkNulls(rotation, "rotation");

        this.setRotation(new Quaterniond().rotateXYZ(rotation, 0, 0));
    }

    /**
     * Get the scale of the object
     * 
     * @return The scale of the object
     * 
     * @since 1.0.0
     */
    public @NotNull Vector3d getScale() {
        return this.scale;
    }

    /**
     * Set the scale of the object
     * 
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public void setScale(@NotNull Vector3d scale) {
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
    public void setScale(@NotNull Vector2d scale) {
        NullUtils.checkNulls(scale, "scale");

        this.setScale(new Vector3d(scale, 1));
    }

    /**
     * Get the complete matrix of the transform
     * 
     * @return The complete matrix of the transform
     * 
     * @since 1.0.0
     */
    public @NotNull Matrix4f getMatrix() {
        return new Matrix4f().translate(this.position.get(new Vector3f())).rotate(this.rotation.get(new Quaternionf())).scale(this.scale.get(new Vector3f()));
    }
}