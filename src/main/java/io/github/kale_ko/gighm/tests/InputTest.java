package io.github.kale_ko.gighm.tests;

import java.io.IOException;
import io.github.kale_ko.gighm.input.InputManager;
import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.shaders.ShaderLoader;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Camera;

public class InputTest {
    public static void main(String[] args) {
        try {
            Scene scene = new Scene("Main");

            Camera camera = Camera.createOrthagraphic(1, 1, 1);

            Shader shader = ShaderLoader.loadShader(InputTest.class.getResourceAsStream("/vertex.glsl"), InputTest.class.getResourceAsStream("/fragment.glsl"));
            Renderer renderer = new Renderer(scene, camera, shader);

            InputManager inputManager = new InputManager();
            Window window = new Window(renderer, inputManager, "Input Test", 800, 600, false, true);

            window.setTitle("Input Test!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}