package io.github.kale_ko.gighm.tests;

import java.awt.Color;
import java.io.IOException;
import org.joml.Vector3d;
import io.github.kale_ko.gighm.events.EventManager;
import io.github.kale_ko.gighm.events.types.rendering.RenderEvent;
import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.shaders.ShaderLoader;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.rendering.textures.Texture2DLoader;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Camera;
import io.github.kale_ko.gighm.scene.components.Mesh;
import io.github.kale_ko.gighm.scene.components.Transform;

public class Renderer3DTest {
    public static void main(String[] args) {
        try {
            int width = 800;
            int height = 600;

            Scene scene = new Scene("Main");

            GameObject cameraObject = new GameObject("Camera");
            Camera camera = Camera.createPerspective(179, width / height, 0.01f, 512);
            cameraObject.addComponent(camera);
            cameraObject.getComponent(Transform.class).setPosition(new Vector3d(0, 0, -20));
            scene.addObjects(cameraObject);

            Shader shader = ShaderLoader.loadShader(Renderer2DTest.class.getResourceAsStream("/vertex.glsl"), Renderer2DTest.class.getResourceAsStream("/fragment.glsl"));
            Renderer renderer = new Renderer(scene, camera, shader);
            renderer.setClearColor(new Color(0.8f, 0.8f, 0.8f));

            Window window = new Window(renderer, "Test Renderer", width, height, false, true);

            EventManager eventManager = new EventManager();
            window.setEventManager(eventManager);

            eventManager.addEventListener(RenderEvent.class, event -> {
                System.out.println("FPS: " + (1 / event.getDelta()));
            });

            GameObject object1 = new GameObject("Test Object");
            Texture2D texture1 = Texture2DLoader.loadTexture(Renderer3DTest.class.getResourceAsStream("/tests/kale.png"));
            Mesh mesh1 = new Mesh(new float[] { -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f }, 2, texture1, new float[] { 0, 0, 1, 0, 1, 1, 0, 1 }, 2, new int[] { 0, 1, 2, 2, 3, 0 });
            object1.addComponent(mesh1);
            object1.getComponent(Transform.class).setPosition(new Vector3d(125, -65, 10));
            object1.getComponent(Transform.class).setScale(new Vector3d(32));

            GameObject object2 = new GameObject("Test Object 2");
            Texture2D texture2 = Texture2DLoader.loadTexture(Renderer2DTest.class.getResourceAsStream("/tests/noise.png"));
            Mesh mesh2 = new Mesh(new float[] { -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f }, 2, texture2, new float[] { 0, 0, 1, 0, 1, 1, 0, 1 }, 2, new int[] { 0, 1, 2, 2, 3, 0 });
            object2.addComponent(mesh2);
            object2.getComponent(Transform.class).setPosition(new Vector3d(45, 240, 10));
            object2.getComponent(Transform.class).setScale(new Vector3d(16));

            scene.addObjects(object1);
            scene.addObjects(object2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}