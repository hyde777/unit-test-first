package core;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
class PhoneBookTest {

    @Nested
    class slow {
        @Test
        void should_find_name_matching_phone_number_on_data() throws IOException, URISyntaxException {
            // Arrange
            List<Contact> contacts = retrieveContacts();
            PhoneBook phoneBook = new PhoneBook(contacts);

            // Act
            String name = phoneBook.findName("0612345678");

            // Assert
            assertThat(name).isEqualTo("Jacques");
        }
    }

    @Nested
    class fast {

        @Test
        void should_find_name_matching_phone_number_on_data() throws IOException, URISyntaxException {
            // Arrange
            List<Contact> contacts = sampleContacts();
            PhoneBook phoneBook = new PhoneBook(contacts);

            // Act
            String name = phoneBook.findName("0612345678");

            // Assert
            assertThat(name).isEqualTo("Jacques");
        }

    }

    @Nested
    class not_self_validating {

        @Test
        void should_find_a_name_matching_phone_number_on_data() throws IOException, URISyntaxException {
            // Arrange
            List<Contact> contacts = sampleContacts();
            PhoneBook phoneBook = new PhoneBook(contacts);

            // Act
            String name = phoneBook.findName("0612345678");

            // Assert
            System.out.println("nom du contact obtenu en cherchant avec 0612345678 est " + name);
        }

    }
    private List<Contact> retrieveContacts() throws IOException, URISyntaxException {
        List<String> strings = new Persistence().retrieveFromPersistence();
        return strings.stream()
                .map(line -> line.split(";"))
                .filter(tuple -> tuple.length == 2)
                .map(tuple -> new Contact(tuple[1], tuple[0]))
                .collect(Collectors.toList());
    }

    private List<Contact> sampleContacts() {
        Contact contact1 = new Contact("Jacques", "0612345678");
        Contact contact2 = new Contact("Michael", "0612345679");

        return List.of(contact1, contact2);
    }
}