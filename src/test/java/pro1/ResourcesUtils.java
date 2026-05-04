package pro1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;


public class ResourcesUtils {
    public static String readResourceFile(String path){
        try {
            return readFirstExisting(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFirstExisting(String path) throws IOException {
        var cwd = Paths.get("").toAbsolutePath();
        while (cwd != null) {
            var candidates = new Path[]{
                    cwd.resolve(Paths.get("src", "test", "resources", path)),
                    cwd.resolve(Paths.get("test", "resources", path)),
                    cwd.resolve(Paths.get("resources", path))
            };
            for (var candidate : candidates) {
                if (Files.exists(candidate)) {
                    // Read files as UTF-8 to correctly handle Czech characters in fixtures
                    return Files.readString(candidate, StandardCharsets.UTF_8);
                }
            }
            cwd = cwd.getParent();
        }
        throw new IOException("Resource file not found in supported locations");
    }
}


