# GIGHM

GIGHM is a game engine written in Java using GLFW that supports both 2D and 3D.

## Features

Currently GIGHM supports rendering scenes of game objects with custom tramsforms, meshes, and textures.

There is also an event system so you can listen for key presses, renders, etc and an input system which supports checking if keyboard keys or mouse buttons are down and also allows getting the mouse pos and scroll.

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
Camera camera = Camera.createOrthagraphic(width, height, farPlane); // Create the 2D camera component
// OR createPerspective(fov, width / height, nearPlane, farPlane) for 3D;
cameraObject.addComponent(camera); // Add the camera component to the camera object
scene.addObjects(cameraObject); // Add the camera object into the scene

Shader shader = ShaderLoader.loadDefault(); // Load the default shader
Renderer renderer = new Renderer(scene, camera, shader); // Create the renderer with the scene, camera, and shader

Window window = new Window(renderer, "Simple Demo", width, height); // Create the window with the render
```

For a complete example see [here](https://github.com/Kale-Ko/GIGHM/blob/master/src/main/java/io/github/kale_ko/gighm/tests/SimpleTest.java).

For more info on the usage of the api see the [Javadocs](https://gighm.kaleko.ga/docs/).
