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
     * The fov of the camera
     * 
     * @since 1.0.0
     */
    private float fov;
    
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
     * The near plane of the camera
     * 
     * @since 1.0.0
     */
    private double near;
    
    /**
     * The far plane of the camera
     * 
     * @since 1.0.0
     */
    private double far;
    
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
     * @param far The far plane of the camera
     * 
     * @since 1.0.0
     */
    public Camera(CameraType type, double near, double far) {
        this.type = type;

        this.near = near;
        this.far = far;
    }

    public static Camera createOrthographic(int width, int height, double near, double far) {
        Camera camera = new Camera(CameraType.ORTHAGRAPHIC, near, far);
        camera.width = width;
        camera.height = height;
        camera.projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);
        return camera;
    }

    public static Camera createPerspective(int fov, double near, double far) {
        Camera camera = new Camera(CameraType.PERSPECTIVE, near, far);
        camera.fov = fov;
        // camera.projection = new Matrix4f().setPerspective(fov, aspect, near, far);
        return camera;
    }

    /**
     * Get the type of the camera
     * 
     * @return The type of the camera
     */
    public CameraType gettype() {
        return this.type;
    }

    /**
     * Set the type of the camera
     * 
     * @param type The type of the camera
     */
    public void settype(CameraType type) {
        this.type = type;
    }

    /**
     * Get the fov of the camera
     * 
     * @return The fov of the camera
     */
    public float getFOV() {
        return this.fov;
    }

    /**
     * Set the fov of the camera
     * 
     * @param fov The fov of the camera
     */
    public void setFOV(float fov) {
        this.fov = fov;
    }

    /**
     * Get the fov of the camera
     * 
     * @return The fov of the camera
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Set the width of the camera
     * 
     * @param width The width of the camera
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Get the fov of the camera
     * 
     * @return The fov of the camera
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Set the height of the camera
     * 
     * @param height The height of the camera
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Get the near plane of the camera
     * 
     * @return The near plane of the camera
     */
    public double getNear() {
        return this.near;
    }

    /**
     * Set the near plane of the camera
     * 
     * @param near The near plane of the camera
     */
    public void setNear(double near) {
        this.near = near;
    }

    /**
     * Get the far plane of the camera
     * 
     * @return The far plane of the camera
     */
    public double getFar() {
        return this.far;
    }

    /**
     * Set the far plane of the camera
     * 
     * @param far The far plane of the camera
     */
    public void setFar(double far) {
        this.far = far;
    }

    /**
     * Get the projection of the camera
     * 
     * @return The projection of the camera
     */
    public Matrix4f getProjection() {
        Matrix4f target = new Matrix4f();

        Matrix4f position = new Matrix4f().setTranslation(this.getGameObject().getComponent(Transform.class).getPosition().get(new Vector3f()));
        Matrix4f rotation = new Matrix4f().set(this.getGameObject().getComponent(Transform.class).getRotation());

        target = projection.mul(position, target).mul(rotation);

        return target;
    }
}