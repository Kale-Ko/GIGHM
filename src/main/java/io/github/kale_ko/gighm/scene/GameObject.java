package io.github.kale_ko.gighm.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joml.Quaterniond;
import org.joml.Vector3d;
import io.github.kale_ko.gighm.scene.components.Component;
import io.github.kale_ko.gighm.scene.components.Transform;

/**
 * An object that can hold different components
 * 
 * @version 1.7.0
 * @since 1.0.0
 */
public class GameObject {
    /**
     * The scene this gameobject is a part of
     * 
     * @since 1.7.0
     */
    private Scene scene = null;

    /**
     * The name of the game object
     * 
     * @since 1.0.0
     */
    private String name;

    /**
     * The objects components
     * 
     * @since 1.0.0
     */
    private Map<Class<? extends Component>, Component> components = new HashMap<Class<? extends Component>, Component>();

    /**
     * Create an object that can hold different components
     * 
     * @since 1.0.0
     */
    public GameObject() {
        this("GameObject");
    }

    /**
     * Create an object that can hold different components
     * 
     * @param name The name of the object
     * 
     * @since 1.0.0
     */
    public GameObject(String name) {
        this.name = name;

        this.addComponent(new Transform(new Vector3d(0, 0, 0), new Quaterniond(0, 0, 0, 1), new Vector3d(1, 1, 1)));
    }

    /**
     * Get the name of the game object
     * 
     * @return The name of the game object
     * 
     * @since 1.0.0
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the game object
     * 
     * @param name The name of the game object
     * 
     * @since 1.0.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get all the components from the object
     * 
     * @return All the components from the object
     * 
     * @since 1.7.0
     */
    public List<Component> getComponents() {
        return new ArrayList<>(this.components.values());
    }

    /**
     * Add a component to the object
     * 
     * @param object The object to add
     * @param <T> The The type of the object to add
     * 
     * @since 1.0.0
     */
    public <T extends Component> void addComponent(T object) {
        if (object.getGameObject() == null) {
            object._setGameObject(this);

            this.components.put(object.getClass(), object);
        } else {
            throw new RuntimeException("You can't add a component to multiple gameobjects");
        }
    }

    /**
     * Get weather a component exists on the object
     * 
     * @param clazz The type to check
     * @param <T> The type to check
     * 
     * @return Weather a component exists on the object
     * 
     * @since 1.7.0
     */
    public <T extends Component> boolean hasComponent(Class<T> clazz) {
        return getComponent(clazz) != null;
    }

    /**
     * Get a component from the object
     * 
     * @param clazz The type to get
     * @param <T> The type to get
     * 
     * @return The component requested or null
     * 
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<T> clazz) {
        return (T) this.components.get(clazz);
    }

    /**
     * Remove a component from the object
     * 
     * @param clazz The type to remove
     * @param <T> The type to remove
     * 
     * @since 1.0.0
     */
    public <T extends Component> void removeComponent(Class<T> clazz) {
        this.components.remove(clazz);
    }

    /**
     * Get the scene this component is a part of
     * 
     * @return The scene this component is a part of
     * 
     * @since 1.0.0
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Set the scene this component is a part of (Only used internally)
     * 
     * @param scene The scene this component should be a part of
     * 
     * @since 1.0.0
     */
    public void _setScene(Scene scene) {
        this.scene = scene;
    }
}