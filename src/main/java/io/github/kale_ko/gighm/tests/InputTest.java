package io.github.kale_ko.gighm.tests;

import java.io.IOException;
import io.github.kale_ko.gighm.events.EventManager;
import io.github.kale_ko.gighm.events.types.input.KeyEvent;
import io.github.kale_ko.gighm.events.types.input.MouseButtonEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveEvent;
import io.github.kale_ko.gighm.events.types.input.MouseScrollEvent;
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

            Window window = new Window(renderer, "Input Test", 800, 600, false, true);

            InputManager inputManager = new InputManager();
            window.setInputManager(inputManager);

            EventManager eventManager = new EventManager();
            window.setEventManager(eventManager);

            eventManager.addEventListener(KeyEvent.class, event -> {
                System.out.println("KEY" + event.getAction() + ": " + event.getCode());
            });

            eventManager.addEventListener(MouseButtonEvent.class, event -> {
                System.out.println("MOUSE" + event.getAction() + ": " + event.getButton());
            });

            eventManager.addEventListener(MouseMoveEvent.class, event -> {
                System.out.println("MOUSEMOVE: " + event.getX() + ", " + event.getY());
            });

            eventManager.addEventListener(MouseScrollEvent.class, event -> {
                System.out.println("MOUSESCROLL: " + event.getX() + ", " + event.getY());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}