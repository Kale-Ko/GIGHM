package io.github.kale_ko.gighm.tests;

import java.io.IOException;
import org.joml.Vector3f;
import io.github.kale_ko.gighm.events.types.input.MouseButtonEvent;
import io.github.kale_ko.gighm.events.types.input.MouseMoveDeltaEvent;
import io.github.kale_ko.gighm.input.MouseButtonAction;
import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;
import io.github.kale_ko.gighm.rendering.objects.PrimitiveMeshes;
import io.github.kale_ko.gighm.rendering.objects.Skybox;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.shaders.ShaderLoader;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.rendering.textures.Texture2DLoader;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Camera;
import io.github.kale_ko.gighm.scene.components.Mesh;
import io.github.kale_ko.gighm.scene.components.Transform;

public class ExampleScene {
    static Boolean mouseDown = false;

    public static void main(String[] args) {
        Integer width = 800;
        Integer height = 600;

        Float fov = 80f;
        Float near = 0.01f;
        Float far = 256f;

        Float sensitivity = 0.0025f;

        try {
            Scene scene = new Scene("Main");

            GameObject cameraObject = new GameObject("Main Camera");
            Camera camera = Camera.createPerspective(fov, (float) width / (float) height, near, far);
            cameraObject.addComponent(camera);
            cameraObject.getComponent(Transform.class).setPosition(new Vector3f(0, 0, -10));
            scene.addObject(cameraObject);

            Texture2D logoTexture = Texture2DLoader.loadTexture(Texture2DLoader.class.getResourceAsStream("/assets/tests/kale.png"));
            Texture2D skySideTexture = Texture2DLoader.loadTexture(Texture2DLoader.class.getResourceAsStream("/assets/tests/sky-sides.png"));
            Texture2D skyTopTexture = Texture2DLoader.loadTexture(Texture2DLoader.class.getResourceAsStream("/assets/tests/sky-top.png"));

            Shader shader = ShaderLoader.loadShader(ShaderLoader.class.getResourceAsStream("/vertex.glsl"), ShaderLoader.class.getResourceAsStream("/fragment.glsl"));
            Renderer renderer = new Renderer(scene, camera, shader, new Skybox(skySideTexture, skySideTexture, skySideTexture, skySideTexture, skyTopTexture, skyTopTexture));

            Window window = new Window(renderer, "GIGHM - Example Scene", width, height, false, true);
            window.setIcon(logoTexture);

            GameObject cubeObject = new GameObject("Cube");
            Mesh cubeMesh = PrimitiveMeshes.CUBE.copy(logoTexture);
            cubeObject.addComponent(cubeMesh);
            cubeObject.getComponent(Transform.class).setPosition(new Vector3f(0, 1, 0));
            cubeObject.getComponent(Transform.class).setScale(new Vector3f(5, 5, 5));
            scene.addObject(cubeObject);

            window.getEventManager().addEventListener(MouseButtonEvent.class, (event) -> {
                mouseDown = event.getAction() == MouseButtonAction.DOWN;
            });

            window.getEventManager().addEventListener(MouseMoveDeltaEvent.class, (event) -> {
                if (mouseDown) {
                    cameraObject.getComponent(Transform.class).getRotation().rotateY(event.getDeltaX() * sensitivity);
                    cameraObject.getComponent(Transform.class).getRotation().rotateLocalX(event.getDeltaY() * sensitivity);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}