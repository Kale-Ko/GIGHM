package io.github.kale_ko.gighm.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import io.github.kale_ko.gighm.exception.InvalidModificationException;
import io.github.kale_ko.gighm.scene.components.Component;
import io.github.kale_ko.gighm.scene.components.Transform;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * An game object that can hold different components
 * 
 * @author Kale Ko
 * 
 * @version 2.0.0
 * @since 1.0.0
 */
public class GameObject {
    /**
     * The scene this game object is a part of
     * 
     * @since 1.7.0
     */
    private @Nullable Scene scene = null;

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
        NullUtils.checkNulls(name, "name");

        this.name = name;

        this.addComponent(new Transform(new Vector3f(0, 0, 0), new Quaternionf(0, 0, 0, 1), new Vector3f(1, 1, 1)));
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
        NullUtils.checkNulls(name, "name");

        this.name = name;
    }

    /**
     * Get all the components from the object
     * 
     * @return All the components from the object
     * 
     * @since 1.7.0
     */
    public @NotNull List<Component> getComponents() {
        return new ArrayList<Component>(this.components.values());
    }

    /**
     * Add a component to the object
     * 
     * @param object The object to add
     * @param <T> The The type of the object to add
     * 
     * @throws InvalidModificationException If the component is already part of another game object
     * 
     * @since 1.0.0
     */
    public <T extends Component> void addComponent(@NotNull T object) throws InvalidModificationException {
        NullUtils.checkNulls(object, "object");

        if (object.getGameObject() == null) {
            object._setGameObject(this);

            this.components.put(object.getClass(), object);
        } else {
            throw new InvalidModificationException("You can't add a component to multiple game objects");
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
    public @NotNull <T extends Component> Boolean hasComponent(@NotNull Class<T> clazz) {
        NullUtils.checkNulls(clazz, "clazz");

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
    public @Nullable <T extends Component> T getComponent(@NotNull Class<T> clazz) {
        NullUtils.checkNulls(clazz, "clazz");

        return (T) this.components.get(clazz);
    }

    /**
     * Remove a component from the object
     * 
     * @param clazz The type to remove
     * @param <T> The type to remove
     * 
     * @throws InvalidModificationException If you try and remove a Transform
     * 
     * @since 1.0.0
     */
    public <T extends Component> void removeComponent(@NotNull Class<T> clazz) throws InvalidModificationException {
        NullUtils.checkNulls(clazz, "clazz");

        if (clazz.equals(Transform.class)) {
            throw new InvalidModificationException("You can't remove an objects Transform");
        } else {
            this.components.remove(clazz);
        }
    }

    /**
     * Get the scene this component is a part of
     * 
     * @return The scene this component is a part of
     * 
     * @since 1.0.0
     */
    public @Nullable Scene getScene() {
        return this.scene;
    }

    /**
     * Set the scene this component is a part of (Only used Internally)
     * 
     * @param scene The scene this component should be a part of
     * 
     * @since 1.0.0
     */
    public void _setScene(@NotNull Scene scene) {
        NullUtils.checkNulls(scene, "scene");

        this.scene = scene;
    }
}