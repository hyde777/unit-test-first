var assert = require('assert');
var should = require('chai').should()
import PhoneBook from '../prod/PhoneBook'
import Contact from '../prod/Contact'
import Persistence from '../prod/Persistence';
let MY_CONTACT_CSV: string = "my-contacts.csv";

describe('PhoneBookTest', function() {


    describe('slow', function() {
        it('should find name matching phone number on data', function() {
            // Arrange
            let phoneBook: PhoneBook = new PhoneBook(retrieveContacts());

            // Act
            let name: string = phoneBook.reverseLookup("0699999999");

            // Assert
            name.should.equal("Jacques R");
        });
    });

    describe('fast', function() {
        it('should find name matching phone number on data', function() {
            // Arrange
            let contacts : Array<Contact> = sampleContacts();
            let phoneBook: PhoneBook = new PhoneBook(contacts);

            // Act
            let name: string = phoneBook.reverseLookup("0612345678");

            // Assert
            name.should.equal("Jacques");
        })
    })

    describe('not_self_validating', function() {
        it('should find name matching phone number on data', function() {
           // Arrange
           let contacts : Array<Contact> = sampleContacts();
           let phoneBook :PhoneBook = new PhoneBook(contacts);

           // Act
           let name :string = phoneBook.reverseLookup("0612345678");

           // Assert
           console.log("nom du contact obtenu en cherchant avec 0612345678 est " + name);
        })
    })

    describe('other', function() {
        it('should find all contact matching', function() {
            // Arrange
            let phoneBook : PhoneBook= new PhoneBook(retrieveContacts());

            // Act
            let matchingContacts : Array<Contact> = phoneBook.findAllContact("Ja");

            // Assert
            matchingContacts.size().should.equal(266434);
        }),

        it('should_add_new_contact', function() {
            // Arrange
            let phoneBook : PhoneBook= new PhoneBook(retrieveContacts());
            let newContact : Contact = new Contact("Jack Napier", "0777777777");

            // Act
            let contactWasAdded  : boolean = phoneBook.addContact(newContact);
            if (contactWasAdded) {
                storeContacts(phoneBook.getContacts());
            }

            // Assert
            contactWasAdded.should.equal(true);
         }),
         
    })
    function retrieveContacts(): Array<Contact>  {
        let strings: Array<String> = new Persistence().retrieveFromPersistence(MY_CONTACT_CSV);
        return strings.map(line => line.split(";"))
                .filter(tuple => tuple.length == 2)
                .map(tuple => new Contact(tuple[1], tuple[0]));
    }

    function storeContacts(contacts :Array<Contact> ) {
        new Persistence().storeToPersistence(contacts, MY_CONTACT_CSV);
    }

    function sampleContacts(): Array<Contact>  {
        let contact1:Contact = new Contact("Jacques", "0612345678");
        let contact2:Contact = new Contact("Michael", "0612345679");

        return Array.of(contact1, contact2);
    }
})