package io.github.kale_ko.gighm.rendering.objects;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import io.github.kale_ko.gighm.rendering.textures.Texture2DLoader;
import io.github.kale_ko.gighm.scene.components.Mesh;
import io.github.kale_ko.gighm.util.NotNull;
import io.github.kale_ko.gighm.util.NullUtils;

/**
 * Load meshes for certain file types
 * 
 * @author Kale Ko
 * 
 * @version 2.1.0
 * @since 2.1.0
 */
public class MeshLoader {
    /**
     * Load a obj mesh file
     * 
     * @param file The file to load the mesh from
     * 
     * @return A {@link Mesh} from the data passed
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 2.1.0
     */
    public static @NotNull Mesh loadMesh(@NotNull String file) throws IOException {
        NullUtils.checkNulls(file, "file");

        return loadMesh(new File(file));
    }

    /**
     * Load a obj mesh file
     * 
     * @param file The file to load the mesh from
     * 
     * @return A {@link Mesh} from the data passed
     * 
     * @throws IOException If it fails to read the file
     * 
     * @since 2.1.0
     */
    public static @NotNull Mesh loadMesh(@NotNull File file) throws IOException {
        NullUtils.checkNulls(file, "file");

        return loadMesh(new FileInputStream(file));
    }

    /**
     * Load a obj mesh stream
     * 
     * @param stream The stream to load the mesh from
     * 
     * @return A {@link Mesh} from the data passed
     * 
     * @throws IOException If it fails to read the stream
     * 
     * @since 2.1.0
     */
    public static @NotNull Mesh loadMesh(@NotNull InputStream stream) throws IOException {
        NullUtils.checkNulls(stream, "stream");

        List<Float> vertices = new ArrayList<Float>();
        List<Float> uvs = new ArrayList<Float>();

        BufferedInputStream reader = new BufferedInputStream(stream);
        StringBuilder data = new StringBuilder();

        Integer read;
        byte[] contents = new byte[1024];
        while ((read = reader.read(contents)) != -1) {
            data.append(new String(contents, 0, read));
        }

        reader.close();

        for (String line : data.toString().split("\n")) {
            String[] values = line.split(" ");

            if (values[0].startsWith("#")) {
                continue;
            } else if (values[0].equals("v")) {
                vertices.add(Float.parseFloat(values[1]));
                vertices.add(Float.parseFloat(values[2]));
                vertices.add(Float.parseFloat(values[3]));
            } else if (values[0].equals("vt")) {
                vertices.add(Float.parseFloat(values[1]));
                vertices.add(Float.parseFloat(values[2]));
            }
        }

        return new Mesh(vertices.toArray(new Float[] {}), 3, Texture2DLoader.loadTexture(Texture2DLoader.class.getResourceAsStream("/assets/white.png")), uvs.toArray(new Float[] {}));
    }
}