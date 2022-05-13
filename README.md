# GIGHM

GIGHM is a game engine written in Java using GLFW that supports both 2D and 3D.

## Features

Currently GIGHM supports rendering scenes which contain game objects.

Game objects can have components which dictate things about them.

There is an event system so you can listen for key presses, renders, etc.

There is also an input system which supports checking if keyboard keys or mouse buttons are down and also allows getting mouse pos and scroll.

## Planned Features

More extendable component system.

A configurable physics system.

## Setup

To get started you just need to add GIGHM as a dependency.

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

To create a simple scene you only need a few lines

```java
Scene scene = new Scene();

GameObject cameraObject = new GameObject();
Camera camera = Camera.createOrthagraphic(width, height, farPlane); 
// OR createPerspective(fov, width / height, nearPlane, farPlane);
cameraObject.addComponent(camera);
scene.addObjects(cameraObject);

Shader shader = ShaderLoader.loadDefault();
Renderer renderer = new Renderer(scene, camera, shader);

Window window = new Window(renderer, "Simple Demo", width, height);
```
