package io.github.kale_ko.gighm.scene;

import java.util.ArrayList;
import java.util.List;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;
import io.github.kale_ko.gighm.util.Nullable;

/**
 * A scene for holding game objects that can be rendered
 * 
 * @author Kale Ko
 * 
 * @version 1.7.0
 * @since 1.0.0
 */
public class Scene {
    /**
     * The name of the scene
     * 
     * @since 1.0.0
     */
    private @NotNull String name;

    /**
     * The objects inside the scene
     * 
     * @since 1.0.0
     */
    private @NotNull List<GameObject> objects = new ArrayList<GameObject>();;

    /**
     * Create a scene
     * 
     * @since 1.4.0
     */
    public Scene() {
        this("Main");
    }

    /**
     * Create a scene
     * 
     * @param name The name of the scene
     * 
     * @since 1.0.0
     */
    public Scene(@NotNull String name) {
        NullUtils.checkNulls(name, "name");

        this.name = name;
    }

    /**
     * Get the name of the scene
     * 
     * @return The name of the scene
     * 
     * @since 1.0.0
     */
    public @NotNull String getName() {
        return this.name;
    }

    /**
     * Get the objects inside the scene
     * 
     * @return The objects inside the scene
     * 
     * @since 1.0.0
     */
    public @NotNull List<GameObject> getObjects() {
        return this.objects;
    }

    /**
     * Get a list of objects inside the scene by name
     * 
     * @param name The name of the objects
     * 
     * @return The game objects requested
     * 
     * @since 1.0.0
     */
    public @NotNull List<GameObject> getObjects(@NotNull String name) {
        return this.getObjects(name, true);
    }

    /**
     * Get a list of objects inside the scene by name
     * 
     * @param name The name of the objects
     * @param caseSensitive Weather the name check is case sensitive or not
     * 
     * @return The game objects requested
     * 
     * @since 1.0.0
     */
    public @NotNull List<GameObject> getObjects(@NotNull String name, @NotNull Boolean caseSensitive) {
        NullUtils.checkNulls(name, "name");
        NullUtils.checkNulls(caseSensitive, "caseSensitive");

        List<GameObject> found = new ArrayList<GameObject>();

        for (GameObject object : this.objects) {
            if ((caseSensitive && object.getName().equals(name)) || (!caseSensitive && object.getName().equalsIgnoreCase(name))) {
                found.add(object);
            }
        }

        return found;
    }

    /**
     * Get a specific object inside the scene by name
     * 
     * @param name The name of the object
     * 
     * @return The game object requested or null
     * 
     * @since 1.0.0
     */
    public @Nullable GameObject getObject(@NotNull String name) {
        return this.getObject(name, true);
    }

    /**
     * Get a specific object inside the scene by name
     * 
     * @param name The name of the object
     * @param caseSensitive Weather the name check is case sensitive or not
     * 
     * @return The game object requested or null
     * 
     * @since 1.0.0
     */
    public @Nullable GameObject getObject(@NotNull String name, @NotNull Boolean caseSensitive) {
        NullUtils.checkNulls(name, "name");
        NullUtils.checkNulls(caseSensitive, "caseSensitive");

        for (GameObject object : this.objects) {
            if ((caseSensitive && object.getName().equals(name)) || (!caseSensitive && object.getName().equalsIgnoreCase(name))) {
                return object;
            }
        }

        return null;
    }

    /**
     * Add an objects to the scene
     * 
     * @param object The objects to add
     * 
     * @since 1.0.0
     */
    public void addObjects(@NotNull GameObject object) {
        NullUtils.checkNulls(object, "object");

        if (object.getScene() == null) {
            object._setScene(this);

            this.objects.add(object);
        } else {
            throw new RuntimeException("You can't add a game object to multiple scenes");
        }
    }

    /**
     * Remove an objects from the scene
     * 
     * @param object The objects to remove
     * 
     * @since 1.0.0
     */
    public void removeObjects(@NotNull GameObject object) {
        NullUtils.checkNulls(object, "object");

        this.objects.remove(object);
    }
}