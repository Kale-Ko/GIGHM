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

            Scene scene = new Scene();

            GameObject cameraObject = new GameObject();
            Camera camera = Camera.createOrthagraphic(width, height, farPlane);
            // OR createPerspective(fov, width / height, nearPlane, farPlane);
            cameraObject.addComponent(camera);
            scene.addObjects(cameraObject);

            Shader shader = ShaderLoader.loadDefault();
            Renderer renderer = new Renderer(scene, camera, shader);

            new Window(renderer, "Test Renderer", width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
