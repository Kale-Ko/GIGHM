#version 330

uniform sampler2D sampler;

in vec2 uv;

out vec4 fragColor;

void main() {
    fragColor = texture2D(sampler, uv);
}
