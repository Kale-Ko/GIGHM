package io.github.kale_ko.gighm.tests;

import io.github.kale_ko.gighm.rendering.Renderer;
import io.github.kale_ko.gighm.rendering.Window;

public class RendererTest {
    public static void main(String[] args) {
        Renderer renderer = new Renderer();
        new Window(renderer, "Test window", 800, 600);
    }
}