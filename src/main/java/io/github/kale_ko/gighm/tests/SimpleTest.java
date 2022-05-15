package io.github.kale_ko.gighm.tests;

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
            int width = 800;
            int height = 600;
            int farPlane = 512;

            Scene scene = new Scene("Main"); // Create the main scene

            GameObject cameraObject = new GameObject("Camera"); // Create the camera object
            Camera camera = Camera.createOrthagraphic(width, height, farPlane); // Create the 2D camera component
            // OR createPerspective(fov, width / height, nearPlane, farPlane) for 3D;
            cameraObject.addComponent(camera); // Add the camera component to the camera object
            scene.addObjects(cameraObject); // Add the camera object into the scene

            Shader shader = ShaderLoader.loadDefault(); // Load the default shader
            Renderer renderer = new Renderer(scene, camera, shader); // Create the renderer with the scene, camera, and shader

            Window window = new Window(renderer, "Simple Demo", width, height); // Create the window with the render
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
