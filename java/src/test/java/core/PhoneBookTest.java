package core;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
class PhoneBookTest {

    private static final String MY_CONTACT_CSV = "my-contacts.csv";

    @Nested
    class slow {
        @Test
        void should_find_name_matching_phone_number_on_data() {
            // Arrange
            PhoneBook phoneBook = new PhoneBook(retrieveContacts());

            // Act
            String name = phoneBook.reverseLookup("0699999999");

            // Assert
            assertThat(name).isEqualTo("Jacques R");
        }
    }

    @Nested
    class fast {

        @Test
        void should_find_name_matching_phone_number_on_data() {
            // Arrange
            List<Contact> contacts = sampleContacts();
            PhoneBook phoneBook = new PhoneBook(contacts);

            // Act
            String name = phoneBook.reverseLookup("0612345678");

            // Assert
            assertThat(name).isEqualTo("Jacques");
        }

    }

    @Nested
    class not_self_validating {

        @Test
        void should_find_a_name_matching_phone_number_on_data() {
            // Arrange
            List<Contact> contacts = sampleContacts();
            PhoneBook phoneBook = new PhoneBook(contacts);

            // Act
            String name = phoneBook.reverseLookup("0612345678");

            // Assert
            System.out.println("nom du contact obtenu en cherchant avec 0612345678 est " + name);
        }

    }

    @Nested
    class other {

        @Test
        void should_find_all_contact_matching() {
            // Arrange
            PhoneBook phoneBook = new PhoneBook(retrieveContacts());

            // Act
            List<Contact> matchingContacts = phoneBook.findAllContact("Ja");

            // Assert
            assertThat(matchingContacts.size()).isEqualTo(266434);
        }

        @Test
        void should_add_new_contact() {
            // Arrange
            PhoneBook phoneBook = new PhoneBook(retrieveContacts());
            Contact newContact = new Contact("Jack Napier", "0777777777");

            // Act
            boolean contactWasAdded = phoneBook.addContact(newContact);
            if (contactWasAdded) {
                storeContacts(phoneBook.getContacts());
            }

            // Assert
            assertThat(contactWasAdded).isTrue();
        }

    }

    private List<Contact> retrieveContacts() {
        List<String> strings = new Persistence().retrieveFromPersistence(MY_CONTACT_CSV);
        return strings.stream()
                .map(line -> line.split(";"))
                .filter(tuple -> tuple.length == 2)
                .map(tuple -> new Contact(tuple[1], tuple[0]))
                .collect(Collectors.toList());
    }

    private void storeContacts(List<Contact> contacts) {
        new Persistence().storeToPersistence(contacts, PhoneBookTest.MY_CONTACT_CSV);
    }

    private List<Contact> sampleContacts() {
        Contact contact1 = new Contact("Jacques", "0612345678");
        Contact contact2 = new Contact("Michael", "0612345679");

        return List.of(contact1, contact2);
    }

}