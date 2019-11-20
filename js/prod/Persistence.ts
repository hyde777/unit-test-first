import Contact from "./Contact";

export default class Persistence {

    private SRC_MAIN_RESOURCES_CORE : string = "./src/main/resources/core/";

    public retrieveFromPersistence(fileName: string) : Array<string>  {
        try {
            let result :string[] = new Array<string>();
            let lineReader = require('readline').createInterface({
                input: require('fs').createReadStream(fileName)
              });
              lineReader.on('line', function (line) {
                result.push(line)
              });
            return result;
        } catch (e) {
            console.log(e)
            return new Array<string>()
        }
    }

    public storeToPersistence( contacts: Array<Contact>, fileName: string) {
        try (FileWriter csvWriter  = new FileWriter(new File(SRC_MAIN_RESOURCES_CORE + fileName))) {
            contacts.forEach(contact => {
                try {
                    csvWriter.append(String.join(";",
                            contact.getPhoneNumber(), contact.getName()));
                    csvWriter.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            csvWriter.flush();
        } catch (e) {
            console.log(e)
        }
    }
}