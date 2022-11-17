package io.github.kale_ko.gighm.tests;

import java.awt.Color;
import java.io.IOException;
import org.joml.Vector3f;
import io.github.kale_ko.gighm.events.types.rendering.RenderEvent;
import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;
import io.github.kale_ko.gighm.rendering.objects.PrimitiveObjects;
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
            Integer width = 800;
            Integer height = 600;

            Scene scene = new Scene();

            GameObject cameraObject = new GameObject();
            Camera camera = Camera.createPerspective(90f, (float) width / (float) height, 0.01f, 512f);
            cameraObject.addComponent(camera);
            cameraObject.getComponent(Transform.class).setPosition(new Vector3f(0, 0, -20));
            scene.addObject(cameraObject);

            Shader shader = ShaderLoader.loadDefault();
            Renderer renderer = new Renderer(scene, camera, shader, new Color(0.8f, 0.8f, 0.8f));

            Window window = new Window(renderer, "GIGHM - 3D Renderer", width, height, false, true);
            window.setIcon(Texture2DLoader.loadTexture(Renderer2DTest.class.getResourceAsStream("/assets/tests/kale.png")));

            window.getEventManager().addEventListener(RenderEvent.class, event -> {
                System.out.println("FPS: " + Math.round(1 / event.getDelta()));
            });

            GameObject object1 = new GameObject();
            Texture2D texture1 = Texture2DLoader.loadTexture(Renderer3DTest.class.getResourceAsStream("/assets/tests/kale.png"));
            Mesh mesh1 = PrimitiveObjects.CUBE.copy(texture1);
            object1.addComponent(mesh1);
            object1.getComponent(Transform.class).setPosition(new Vector3f(3, -6, 10));
            object1.getComponent(Transform.class).setScale(new Vector3f(3));

            GameObject object2 = new GameObject();
            Texture2D texture2 = Texture2DLoader.loadTexture(Renderer3DTest.class.getResourceAsStream("/assets/tests/noise.png"));
            Mesh mesh2 = PrimitiveObjects.CUBE.copy(texture2);
            object2.addComponent(mesh2);
            object2.getComponent(Transform.class).setPosition(new Vector3f(5, 2, 10));
            object2.getComponent(Transform.class).setScale(new Vector3f(1));

            scene.addObject(object1);
            scene.addObject(object2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}