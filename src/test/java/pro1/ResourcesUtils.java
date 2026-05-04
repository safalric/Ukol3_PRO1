package pro1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ResourcesUtils {
    public static String readResourceFile(String path){
        try {
            return readFirstExisting(
                    Paths.get("src", "test", "resources", path),
                    Paths.get("test", "resources", path)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFirstExisting(Path... paths) throws IOException {
        for (var candidate : paths) {
            if (Files.exists(candidate)) {
                return Files.readString(candidate);
            }
        }
        throw new IOException("Resource file not found in supported locations");
    }
}


