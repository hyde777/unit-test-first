
export default class Contact {

    public constructor(private name: string,private phoneNumber: string) {    }

    public get getName():string {
        return this.name;
    }

    public get getPhoneNumber(): string{
        return this.phoneNumber;
    }

    public equals(o:object) :boolean{
        if (this == o) return true;
        if (o == null || typeof this != typeof o) return false;
        let contact :Contact = <Contact> o;
        return this.name === contact.name &&
            this.phoneNumber === contact.phoneNumber;
    }

    public toString(): string {
        return "PhoneBook{" +
                "name='" + this.name + '\'' +
                ", phoneNumber='" + this.phoneNumber + '\'' +
                '}';
    }
}
