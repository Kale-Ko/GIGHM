package io.github.kale_ko.gighm.scene.components;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * A camera to render from
 * 
 * @author Kale Ko
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class Camera extends Component {
    /**
     * The types of cameras
     * 
     * @author Kale Ko
     * 
     * @version 1.0.0
     * @since 1.0.0
     */
    public enum CameraType {
        /**
         * An orthagraphic (2D) camera
         * 
         * @since 1.0.0
         */
        ORTHAGRAPHIC,

        /**
         * An perspective (3D) camera
         * 
         * @since 1.0.0
         */
        PERSPECTIVE
    }

    /**
     * The type of the camera
     * 
     * @since 1.0.0
     */
    private @NotNull CameraType type;

    /**
     * The width of the camera
     * 
     * @since 1.0.0
     */
    private Float width;

    /**
     * The height of the camera
     * 
     * @since 1.0.0
     */
    private Float height;

    /**
     * The field of view of the camera
     * 
     * @since 1.0.0
     */
    private Float fov;

    /**
     * The aspect ratio of the camera (Generally width over height)
     * 
     * @since 1.0.0
     */
    private Float aspect;

    /**
     * The near plane of the camera (Closest thing that will render)
     * 
     * @since 1.0.0
     */
    private Float near;

    /**
     * The far plane of the camera (Farthest thing that will render)
     * 
     * @since 1.0.0
     */
    private Float far;

    /**
     * The projection matrix of the camera
     * 
     * @since 1.0.0
     */
    private @NotNull Matrix4f projection;

    /**
     * Create a camera
     * 
     * @param type The type of the camera
     * 
     * @since 1.0.0
     */
    protected Camera(@NotNull CameraType type) {
        NullUtils.checkNulls(type, "type");

        this.type = type;
    }

    /**
     * Create a orthagraphic camera
     * 
     * @param width The width of the camera
     * @param height The height of the camera
     * @param far The far plane of the camera (Farthest thing that will render)
     * 
     * @return A new orthagraphic camera with the passed paramiters
     */
    public static Camera createOrthagraphic(Integer width, Integer height, Float far) {
        NullUtils.checkNulls(width, "width");
        NullUtils.checkNulls(height, "height");
        NullUtils.checkNulls(far, "far");

        Camera camera = new Camera(CameraType.ORTHAGRAPHIC);

        camera.width = (float) width;
        camera.height = (float) height;
        camera.far = far;

        camera.recalculateProjection();

        return camera;
    }

    /**
     * Create a perspective camera
     * 
     * @param fov The fov of the camera
     * @param aspect The aspect of the camera
     * @param near The near plane of the camera
     * @param far The far plane of the camera
     * 
     * @return A new {@link Camera} with the passed paramiters
     */
    public static Camera createPerspective(Float fov, Float aspect, Float near, Float far) {
        NullUtils.checkNulls(fov, "fov");
        NullUtils.checkNulls(aspect, "aspect");
        NullUtils.checkNulls(near, "near");
        NullUtils.checkNulls(far, "far");

        Camera camera = new Camera(CameraType.PERSPECTIVE);

        camera.fov = fov;
        camera.aspect = aspect;
        camera.near = near;
        camera.far = far;

        camera.recalculateProjection();

        return camera;
    }

    /**
     * Get the type of the camera
     * 
     * @return The type of the camera
     * 
     * @since 1.0.0
     */
    public @NotNull CameraType getType() {
        return this.type;
    }

    /**
     * Get the width of the camera
     * 
     * @return The width of the camera
     * 
     * @since 1.0.0
     */
    public Float getWidth() {
        return this.width;
    }

    /**
     * Set the width of the camera
     * 
     * @param width The width of the camera
     * 
     * @since 1.0.0
     */
    public void setWidth(@NotNull Float width) {
        NullUtils.checkNulls(width, "width");

        this.width = width;

        this.recalculateProjection();
    }

    /**
     * Get the height of the camera
     * 
     * @return The height of the camera
     * 
     * @since 1.0.0
     */
    public Float getHeight() {
        return this.height;
    }

    /**
     * Set the height of the camera
     * 
     * @param height The height of the camera
     * 
     * @since 1.0.0
     */
    public void setHeight(@NotNull Float height) {
        NullUtils.checkNulls(height, "height");

        this.height = height;

        this.recalculateProjection();
    }

    /**
     * Get the field of view of the camera
     * 
     * @return The field of view of the camera
     * 
     * @since 1.0.0
     */
    public Float getFOV() {
        return this.fov;
    }

    /**
     * Set the field of view of the camera
     * 
     * @param fov The field of view of the camera
     * 
     * @since 1.0.0
     */
    public void setFOV(@NotNull Float fov) {
        NullUtils.checkNulls(fov, "fov");

        this.fov = fov;

        this.recalculateProjection();
    }

    /**
     * Get the aspect ratio of the camera (Generally width over height)
     * 
     * @return The aspect ratio of the camera (Generally width over height)
     * 
     * @since 1.0.0
     */
    public Float getAspect() {
        return this.aspect;
    }

    /**
     * Set the aspect ratio of the camera
     * 
     * @param aspect The aspect ratio of the camera
     * 
     * @since 1.0.0
     */
    public void setAspect(@NotNull Float aspect) {
        NullUtils.checkNulls(aspect, "aspect");

        this.aspect = aspect;

        this.recalculateProjection();
    }

    /**
     * Get the near plane of the camera (Closest thing that will render)
     * 
     * @return The near plane of the camera (Closest thing that will render)
     * 
     * @since 1.0.0
     */
    public Float getNear() {
        return this.near;
    }

    /**
     * Set the near plane of the camera (Closest thing that will render)
     * 
     * @param near The near plane of the camera (Closest thing that will render)
     * 
     * @since 1.0.0
     */
    public void setNear(Float near) {
        NullUtils.checkNulls(near, "near");

        this.near = near;

        this.recalculateProjection();
    }

    /**
     * Get the far plane of the camera (Farthest thing that will render)
     * 
     * @return The far plane of the camera (Farthest thing that will render)
     * 
     * @since 1.0.0
     */
    public Float getFar() {
        return this.far;
    }

    /**
     * Set the far plane of the camera (Farthest thing that will render)
     * 
     * @param far The far plane of the camera (Farthest thing that will render)
     * 
     * @since 1.0.0
     */
    public void setFar(Float far) {
        NullUtils.checkNulls(far, "far");

        this.far = far;

        this.recalculateProjection();
    }

    /**
     * Get the projection matrix of the camera
     * 
     * @return The projection matrix of the camera
     * 
     * @since 1.0.0
     */
    public @NotNull Matrix4f getProjection() {
        Matrix4f target = new Matrix4f();

        Matrix4f position = new Matrix4f().setTranslation(this.getGameObject().getComponent(Transform.class).getPosition().get(new Vector3f()));
        Matrix4f rotation = new Matrix4f().set(this.getGameObject().getComponent(Transform.class).getRotation());

        target = projection.mul(position, target).mul(rotation);

        return target;
    }

    /**
     * Recalculate the projection matrix of the camera
     * (This is done automatically when using set{x} functions)
     * 
     * @since 1.0.0
     */
    public void recalculateProjection() {
        if (this.type == CameraType.ORTHAGRAPHIC) {
            this.projection = new Matrix4f().setOrtho(-this.width / 2, this.width / 2, -this.height / 2, this.height / 2, -this.far, this.far);
        } else if (this.type == CameraType.PERSPECTIVE) {
            this.projection = new Matrix4f().setPerspective(this.fov * ((float) Math.PI / 180f), this.aspect, this.near, this.far);
        }
    }
}