package io.github.kale_ko.gighm.scene;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.joml.Quaterniond;
import org.joml.Vector3d;
import io.github.kale_ko.gighm.scene.components.Component;
import io.github.kale_ko.gighm.scene.components.Transform;

/**
 * An object that can hold different components
 * 
 * @since 1.0.0
 */
public class GameObject {
    /**
     * The name of the game object
     * 
     * @since 1.0.0
     */
    private @NotNull String name;

    /**
     * The objects components
     * 
     * @since 1.0.0
     */
    private @NotNull Map<Class<? extends Component>, Component> components = new HashMap<Class<? extends Component>, Component>();

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
    public GameObject(@NotNull String name) {
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
    public @NotNull String getName() {
        return this.name;
    }

    /**
     * Set the name of the game object
     * 
     * @param name The name of the game object
     * 
     * @since 1.0.0
     */
    public void setName(@NotNull String name) {
        this.name = name;
    }

    /**
     * Add a component to the object
     * 
     * @param object The object to add
     * 
     * @since 1.0.0
     */
    public <T extends Component> void addComponent(@NotNull T object) {
        this.components.put(object.getClass(), object);
    }

    /**
     * Get a component from the object
     * 
     * @param clazz The type to get
     * 
     * @returns The component requested or null
     * 
     * @since 1.0.0
     */
    public <T extends Component> T getComponent(@NotNull Class<T> clazz) {
        return (T) this.components.get(clazz);
    }

    /**
     * Remove a component from the object
     * 
     * @param clazz The type to remove
     * 
     * @since 1.0.0
     */
    public <T extends Component> void removeComponent(@NotNull Class<T> clazz) {
        this.components.remove(clazz);
    }
}