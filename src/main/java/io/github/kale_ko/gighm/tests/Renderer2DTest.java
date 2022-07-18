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

public class Renderer2DTest {
    public static void main(String[] args) {
        try {
            Integer width = 800;
            Integer height = 600;

            Scene scene = new Scene();

            GameObject cameraObject = new GameObject();
            Camera camera = Camera.createOrthographic(width, height, 512f);
            cameraObject.addComponent(camera);
            cameraObject.getComponent(Transform.class).setPosition(new Vector3f(0, 0, -10));
            scene.addObjects(cameraObject);

            Shader shader = ShaderLoader.loadDefault();
            Renderer renderer = new Renderer(scene, camera, shader, new Color(0.8f, 0.8f, 0.8f));

            Window window = new Window(renderer, "2D Renderer", width, height, false, true);
            window.setIcon(Texture2DLoader.loadTexture(Renderer2DTest.class.getResourceAsStream("/assets/tests/kale.png")));

            window.getEventManager().addEventListener(RenderEvent.class, event -> {
                System.out.println("FPS: " + Math.round(1 / event.getDelta()));
            });

            GameObject object1 = new GameObject();
            Texture2D texture1 = Texture2DLoader.loadTexture(Renderer2DTest.class.getResourceAsStream("/assets/tests/kale.png"));
            Mesh mesh1 = PrimitiveObjects.PLANE.copy(texture1);
            object1.addComponent(mesh1);
            object1.getComponent(Transform.class).setPosition(new Vector3f(125, -65, 10));
            object1.getComponent(Transform.class).setScale(new Vector3f(160));

            GameObject object2 = new GameObject();
            Texture2D texture2 = Texture2DLoader.loadTexture(Renderer2DTest.class.getResourceAsStream("/assets/tests/kale.png"));
            Mesh mesh2 = PrimitiveObjects.PLANE.copy(texture2);
            object2.addComponent(mesh2);
            object2.getComponent(Transform.class).setPosition(new Vector3f(-85, 30, 10));
            object2.getComponent(Transform.class).setScale(new Vector3f(100));

            GameObject object3 = new GameObject();
            Texture2D texture3 = Texture2DLoader.loadTexture(Renderer2DTest.class.getResourceAsStream("/assets/tests/noise.png"));
            Mesh mesh3 = PrimitiveObjects.PLANE.copy(texture3);
            object3.addComponent(mesh3);
            object3.getComponent(Transform.class).setPosition(new Vector3f(45, 240, 10));
            object3.getComponent(Transform.class).setScale(new Vector3f(80));

            GameObject object4 = new GameObject();
            Mesh mesh4 = PrimitiveObjects.PLANE.copy(new Color(255, 100, 120));
            object4.addComponent(mesh4);
            object4.getComponent(Transform.class).setPosition(new Vector3f(-45, -180, 10));
            object4.getComponent(Transform.class).setScale(new Vector3f(70));

            scene.addObjects(object1);
            scene.addObjects(object2);
            scene.addObjects(object3);
            scene.addObjects(object4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}