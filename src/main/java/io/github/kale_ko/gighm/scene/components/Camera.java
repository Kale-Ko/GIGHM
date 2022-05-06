package io.github.kale_ko.gighm.scene.components;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A camera to render from
 * 
 * @since 1.0.0
 */
public class Camera extends Component {
    /**
     * The type of a camera
     * 
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
    private CameraType type;

    /**
     * The width of the camera
     * 
     * @since 1.0.0
     */
    private float width;

    /**
     * The height of the camera
     * 
     * @since 1.0.0
     */
    private float height;

    /**
     * The fov of the camera
     * 
     * @since 1.0.0
     */
    private float fov;

    /**
     * The aspect of the camera
     * 
     * @since 1.0.0
     */
    private float aspect;

    /**
     * The near plane of the camera
     * 
     * @since 1.0.0
     */
    private float near;

    /**
     * The far plane of the camera
     * 
     * @since 1.0.0
     */
    private float far;

    /**
     * The projection of the camera
     * 
     * @since 1.0.0
     */
    private Matrix4f projection;

    /**
     * Create a camera to render from
     * 
     * @param type The type of the camera
    
     * @param near The near plane of the camera
     * 
     * @param far The far plane of the camera
     * 
     * @since 1.0.0
     */
    protected Camera(CameraType type) {
        this.type = type;
    }

    /**
     * Create a orthagraphic camera
     * 
     * @param width The width of the camera
     * @param height The height of the camera
     * @param far The far plane of the camera
     * 
     * @return A new {@link Camera} with the passed paramiters
     */
    public static Camera createOrthagraphic(int width, int height, float far) {
        Camera camera = new Camera(CameraType.ORTHAGRAPHIC);
        camera.width = width;
        camera.height = height;
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
    public static Camera createPerspective(float fov, float aspect, float near, float far) {
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
    public CameraType gettype() {
        return this.type;
    }

    /**
     * Get the fov of the camera
     * 
     * @return The fov of the camera
     * 
     * @since 1.0.0
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Set the width of the camera
     * 
     * @param width The width of the camera
     * 
     * @since 1.0.0
     */
    public void setWidth(float width) {
        this.width = width;

        this.recalculateProjection();
    }

    /**
     * Get the fov of the camera
     * 
     * @return The fov of the camera
     * 
     * @since 1.0.0
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Set the height of the camera
     * 
     * @param height The height of the camera
     * 
     * @since 1.0.0
     */
    public void setHeight(float height) {
        this.height = height;

        this.recalculateProjection();
    }

    /**
     * Get the fov of the camera
     * 
     * @return The fov of the camera
     * 
     * @since 1.0.0
     */
    public float getFOV() {
        return this.fov;
    }

    /**
     * Set the fov of the camera
     * 
     * @param fov The fov of the camera
     * 
     * @since 1.0.0
     */
    public void setFOV(float fov) {
        this.fov = fov;

        this.recalculateProjection();
    }

    /**
     * Get the aspect of the camera
     * 
     * @return The aspect of the camera
     * 
     * @since 1.0.0
     */
    public float getAspect() {
        return this.aspect;
    }

    /**
     * Set the aspect of the camera
     * 
     * @param aspect The aspect of the camera
     * 
     * @since 1.0.0
     */
    public void setAspect(float aspect) {
        this.aspect = aspect;

        this.recalculateProjection();
    }

    /**
     * Get the near plane of the camera
     * 
     * @return The near plane of the camera
     * 
     * @since 1.0.0
     */
    public float getNear() {
        return this.near;
    }

    /**
     * Set the near plane of the camera
     * 
     * @param near The near plane of the camera
     * 
     * @since 1.0.0
     */
    public void setNear(float near) {
        this.near = near;

        this.recalculateProjection();
    }

    /**
     * Get the far plane of the camera
     * 
     * @return The far plane of the camera
     * 
     * @since 1.0.0
     */
    public float getFar() {
        return this.far;
    }

    /**
     * Set the far plane of the camera
     * 
     * @param far The far plane of the camera
     * 
     * @since 1.0.0
     */
    public void setFar(float far) {
        this.far = far;

        this.recalculateProjection();
    }

    public void recalculateProjection() {
        if (this.type == CameraType.ORTHAGRAPHIC) {
            this.projection = new Matrix4f().setOrtho(-this.width / 2, this.width / 2, -this.height / 2, this.height / 2, -this.far, this.far);
        } else if (this.type == CameraType.PERSPECTIVE) {
            this.projection = new Matrix4f().setPerspective(this.fov, this.aspect, this.near, this.far);
        }
    }

    /**
     * Get the projection of the camera
     * 
     * @return The projection of the camera
     * 
     * @since 1.0.0
     */
    public Matrix4f getProjection() {
        Matrix4f target = new Matrix4f();

        Matrix4f position = new Matrix4f().setTranslation(this.getGameObject().getComponent(Transform.class).getPosition().get(new Vector3f()));
        Matrix4f rotation = new Matrix4f().set(this.getGameObject().getComponent(Transform.class).getRotation());

        target = projection.mul(position, target).mul(rotation);

        return target;
    }
}