#version 330

in vec3 verticies;
in vec2 uvs;

out vec2 uv;

uniform mat4 projection;

void main() {
    uv = uvs;

    gl_Position = projection * vec4(verticies, 1);
}
