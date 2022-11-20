package io.github.kale_ko.gighm.tests;

import java.awt.Color;
import java.io.IOException;
import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.shaders.ShaderLoader;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Camera;

public class SimpleTest {
    public static void main(String[] args) {
        try {
            // Just defining the variables that are used
            Integer width = 800;
            Integer height = 600;
            Float farPlane = 512f;

            Scene scene = new Scene("Main"); // Create the main scene

            GameObject cameraObject = new GameObject("Camera"); // Create the camera object
            Camera camera = Camera.createOrthographic(width, height, farPlane); // Create the 2D camera component
            // OR createPerspective(fov, (float) width / (float) height, nearPlane, farPlane) for 3D;
            cameraObject.addComponent(camera); // Add the camera component to the camera object
            scene.addObject(cameraObject); // Add the camera object into the scene

            Shader shader = ShaderLoader.loadDefault(); // Load the default shader
            Renderer renderer = new Renderer(scene, camera, shader, new Color(50, 50, 200)); // Create the renderer with the scene, camera, shader, and a sky color

            Window window = new Window(renderer, "GIGHM - Simple Demo", width, height); // Create the window with the render

            window.setTitle("Demo!"); // Change the window title once it is created
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}