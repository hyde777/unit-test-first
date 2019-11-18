package fast;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Persistence {

    List<String> retrieveFromPersistence() {
        try {
            Thread.sleep(10000);
            URI uri = getClass().getResource("my-contacts.csv").toURI();
            return Files.readAllLines(Path.of(uri));
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
