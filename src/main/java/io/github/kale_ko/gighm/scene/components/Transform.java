package io.github.kale_ko.gighm.scene.components;

import javax.validation.constraints.NotNull;
import org.joml.Quaterniond;
import org.joml.Vector3d;

/**
 * An object transform
 * 
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
     * Create an object transform
     * 
     * @param position The position of the object
     * @param rotation The rotation of the object
     * @param scale The scale of the object
     * 
     * @since 1.0.0
     */
    public Transform(@NotNull Vector3d position, @NotNull Quaterniond rotation, @NotNull Vector3d scale) {
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
        this.position = position;
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
        this.rotation = rotation;
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
        this.scale = scale;
    }

    /**
     * Get the name of the component
     * 
     * @return The name of the component
     * 
     * @since 1.0.0
     */
    public static @NotNull String getName() {
        return "Transform";
    }
}