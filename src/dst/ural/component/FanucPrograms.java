package dst.ural.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static dst.ural.component.Directory.DIRECTORY;
import static dst.ural.component.Directory.createDirectory;

public class FanucPrograms {

    private static final Logger LOG = Logger.getLogger(FanucPrograms.class.getName());

    private static final String MAIN_NAME_PROGRAM = "-9999";
    private static final String PREFIX = "O";
    private static final String DELIMITER = "%";

    public static void createFanucProgram() {
        createDirectory();

        try {
            read().forEach(FanucPrograms::write);
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }

        LOG.info("Finish program");
    }

    // package

    static List<String> read() throws IOException {
        return Arrays.stream(Files.lines(Paths.get(DIRECTORY + "/" + MAIN_NAME_PROGRAM))
                        .map(line -> {
                            if (line.startsWith(PREFIX)) {
                                return DELIMITER + line;
                            }
                            return line;
                        })
                        .collect(Collectors.joining("\n"))
                        .split(DELIMITER))
                .filter(line -> line.startsWith(PREFIX))
                .collect(Collectors.toList());
    }

    static void write(String program) {
        String nameProgramm = nameProgram(program);
        try {
            Files.write(Paths.get(DIRECTORY + "/" + nameProgramm), Collections.singleton(program));
        } catch (IOException e) {
            LOG.info("Error write : " + nameProgramm);
        }
    }

    static String nameProgram(String program) {
        return program.split("\n")[0];
    }

}
