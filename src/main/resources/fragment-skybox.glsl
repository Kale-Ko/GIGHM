#version 330

uniform samplerCube sampler;
in vec3 uv;

out vec4 fragColor;

void main() {
    fragColor = texture(sampler, uv);
}
