package io.github.kale_ko.gighm.tests;

import java.io.IOException;
import io.github.kale_ko.gighm.events.types.input.KeyEvent;
import io.github.kale_ko.gighm.events.types.input.MouseButtonEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveEvent;
import io.github.kale_ko.gighm.events.types.input.MouseScrollEvent;
import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.shaders.ShaderLoader;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Camera;

public class InputTest {
    public static void main(String[] args) {
        try {
            Scene scene = new Scene();

            Camera camera = Camera.createOrthographic(1, 1, 256f);

            Shader shader = ShaderLoader.loadDefault();
            Renderer renderer = new Renderer(scene, camera, shader);

            Window window = new Window(renderer, "GIGHM - Input/Events", 800, 600, false, true);

            window.getEventManager().addEventListener(KeyEvent.class, event -> {
                System.out.println("KEY" + event.getAction() + ": " + event.getCode());
            });

            window.getEventManager().addEventListener(MouseButtonEvent.class, event -> {
                System.out.println("MOUSE" + event.getAction() + ": " + event.getButton());
            });

            window.getEventManager().addEventListener(MouseMoveEvent.class, event -> {
                System.out.println("MOUSEMOVE: " + event.getX() + ", " + event.getY());
            });

            window.getEventManager().addEventListener(MouseScrollEvent.class, event -> {
                System.out.println("MOUSESCROLL: " + event.getX() + ", " + event.getY());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}