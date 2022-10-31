# GIGHM

GIGHM is a Java game engine built using GLFW that supports both 2D and 3D rendering.

## Features

- Rendering scenes that contain game objects with custom transforms, meshes, and textures.
- Loading custom meshes and textures from file and creating custom skyboxes.
- An event system that can catch keys and button presses, render events, and an input system that supports getting mouse position and keys down.
- An extendable component system for more easily writing custom logic for game objects with [full documentation](https://gighm.kaleko.ga/docs/).

## Setup

To get started you just need to add GIGHM as a dependency to your project.

### Gradle

```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```

```gradle
dependencies {
    implementation "com.github.Kale-Ko:GIGHM:VERSION"
}
```

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.Kale-Ko</groupId>
    <artifactId>GIGHM</artifactId>
    <version>VERSION</version>
</dependency>
```

## Usage

To create a simple scene you only need a few lines.

```java
Scene scene = new Scene("Main"); // Create the main scene

GameObject cameraObject = new GameObject("Camera"); // Create the camera object
Camera camera = Camera.createOrthographic(width, height, farPlane); // Create the 2D camera component
// OR createPerspective(fov, (float) width / (float) height, nearPlane, farPlane) for 3D;
cameraObject.addComponent(camera); // Add the camera component to the camera object
scene.addObject(cameraObject); // Add the camera object into the scene

Shader shader = ShaderLoader.loadDefault(); // Load the default shader
Renderer renderer = new Renderer(scene, camera, shader); // Create the renderer with the scene, camera, and shader

Window window = new Window(renderer, "Simple Demo", width, height); // Create the window with the render

window.setTitle("Demo!"); // Change the window title once it is created
```

For the complete example see [here](https://github.com/Kale-Ko/GIGHM/blob/master/src/main/java/io/github/kale_ko/gighm/tests/SimpleTest.java).

For a simple game example see [here](https://github.com/Kale-Ko/GIGHM/blob/master/src/main/java/io/github/kale_ko/gighm/tests/SimpleGame.java).

For more info on the usage of the api see the [Javadocs](https://gighm.kaleko.ga/docs/).
