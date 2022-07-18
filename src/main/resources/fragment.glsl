#version 330

uniform bool hasSampler;

uniform sampler2D sampler;
in vec2 uv;

uniform vec3 color;

out vec4 fragColor;

void main() {
    if (hasSampler) {
        fragColor = texture2D(sampler, uv);
    } else {
        fragColor = vec4(color.r / 255, color.g / 255, color.b / 255, 1);
    }
}
