import Contact from "./Contact";

export default class PhoneBook {

    public get getContacts() :Array<Contact> {
        return this.contacts;
    }

    public constructor(private contacts: Array<Contact>) {
    }

    public reverseLookup( phoneNumber:string):string {
        return this.contacts
                .filter(contact => phoneNumber == contact.getPhoneNumber)
                .map(contact => contact.getName)
                .shift();
    }

    public lookup(name : string) :string {
        return this.contacts.filter(contact => name == contact.getName)
                .map(contact => contact.getPhoneNumber)
                .shift();
    }

    public findAllContact (searchParameter: string): Array<Contact> {
        if (searchParameter == "") {
            return this.contacts;
        }
        return this.contacts
                .filter(contact => contact.getName.includes(searchParameter) ||
                        contact.getPhoneNumber.includes(searchParameter));
    }

    public addContact( newContact:Contact) : boolean{
        if (this.contacts.includes(newContact)) {
            return false;
        }
        this.contacts.push(newContact);
        return true;
    }

    public removeContact( contactToRemove: Contact) :boolean {
        if (this.contacts.includes(contactToRemove)) {
            return false;
        }
        this.contacts.filter(x => x != contactToRemove);
        return true;
    }

}