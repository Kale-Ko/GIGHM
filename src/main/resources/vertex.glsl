#version 330

in vec2 verticies;
in vec2 uvs;

out vec2 uv;

void main() {
    uv = uvs;
    gl_Position = vec4(vec3(verticies, 0), 1);
}
