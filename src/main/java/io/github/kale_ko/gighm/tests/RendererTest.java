package io.github.kale_ko.gighm.tests;

import java.io.IOException;
import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;
import io.github.kale_ko.gighm.rendering.shaders.Shader;
import io.github.kale_ko.gighm.rendering.shaders.ShaderLoader;
import io.github.kale_ko.gighm.rendering.textures.Texture2D;
import io.github.kale_ko.gighm.rendering.textures.Texture2DLoader;
import io.github.kale_ko.gighm.scene.GameObject;
import io.github.kale_ko.gighm.scene.Scene;
import io.github.kale_ko.gighm.scene.components.Mesh;

public class RendererTest {
    public static void main(String[] args) {
        try {
            Scene scene = new Scene("Main");
            Shader shader = new Shader(ShaderLoader.loadShaderData(RendererTest.class.getResourceAsStream("/vertex.glsl")), ShaderLoader.loadShaderData(RendererTest.class.getResourceAsStream("/fragment.glsl")));
            Renderer renderer = new Renderer(scene, shader);

            GameObject object = new GameObject("Test Object");
            Texture2D texture = Texture2DLoader.loadTexture("C:/Users/Kale Ko/Downloads/54416665.png");
            Mesh mesh = new Mesh(new float[] { -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f }, 2, texture, new float[] { 0, 0, 1, 0, 1, 1, 0, 1 }, 2, new int[] { 0, 1, 2, 2, 3, 0 });
            object.addComponent(mesh);
            scene.addObjects(object);

            new Window(renderer, "Test window", 800, 600, false, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}