package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Persistence {

    private static final String SRC_MAIN_RESOURCES_CORE = "./src/main/resources/core/";

    public List<String> retrieveFromPersistence(String fileName) {
        try {
            URI uri = getClass().getResource(fileName).toURI();
            return Files.readAllLines(Path.of(uri));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    void storeToPersistence(List<Contact> contacts, String fileName) {
        try (FileWriter csvWriter  = new FileWriter(new File(SRC_MAIN_RESOURCES_CORE + fileName))) {
            contacts.forEach(contact -> {
                try {
                    csvWriter.append(String.join(";",
                            contact.getPhoneNumber(), contact.getName()));
                    csvWriter.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
