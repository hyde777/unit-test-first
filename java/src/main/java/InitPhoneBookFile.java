import core.Contact;
import core.Persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitPhoneBookFile {

    public static void main(String[] args) {
        System.out.println("Start generate phone book file");
        generateContacts();
        System.out.println("done");
    }

    public static void generateContacts() {
        Persistence persistence = new Persistence();
        List<String> firstNames = persistence.retrieveFromPersistence("first_name_aleatoire.csv");
        List<String> lastNames = persistence.retrieveFromPersistence("last_name_aleatoire.csv");

        List<String> names = firstNames.stream()
                .flatMap(firstName -> lastNames.stream().map(lastName -> firstName + " " + lastName))
                .collect(Collectors.toList());
        List<Contact> contacts = IntStream.range(0, names.size())
                .mapToObj(index -> new Contact(names.get(index), "061" + String.format("%07d", index)))
                .collect(Collectors.toList());
        Collections.shuffle(contacts);

        BiConsumer<FileWriter, Contact> writerContactBiConsumer = (writer, contact) -> {
            try {
                writer.append(String.join(";",
                        contact.getPhoneNumber(), contact.getName()));
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        try(FileWriter csvWriter = new FileWriter("./src/main/resources/core/my-contacts.csv")) {
            writerContactBiConsumer.accept(csvWriter, new Contact("Jacques R", "0699999999"));
            contacts.forEach(contact -> {
                writerContactBiConsumer.accept(csvWriter, contact);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
