package dst.ural.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class Directory {

    private Directory() {};

    private static final Logger LOG = Logger.getLogger(Directory.class.getName());

    public static final String DIRECTORY = "FanucPrograms";

    public static void createDirectory() {
        if (Files.notExists(Path.of(DIRECTORY))) {
            try {
                Files.createDirectory(Path.of(DIRECTORY));
            } catch (IOException e) {
                LOG.info("Can't create directory \n" + e.getMessage());
            }
        }
    }
}
