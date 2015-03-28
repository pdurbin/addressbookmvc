# Java EE 7 AddressBookMVC Example

Technologies used:

- Java 7
- Maven 3
- Glassfish 4
- Vagrant (for demo purposes)

Run `vagrant up` to deploy the app to <http://localhost:8888/javaee7addressbook-1.0-SNAPSHOT/>

The expected output from Vagrant is something like this:

    <?xml version='1.0' encoding='UTF-8' ?>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml"><head id="j_idt2">
            <title>Java EE 7 AddressBookMVC Example</title></head><body>Java EE 7 AddressBookMVC Example</body>
    </html>
    {
        "message": "Added: 1"
    }
    {
        "message": {
            "people": [
                {
                    "displayName": "Philip Durbin", 
                    "id": 1
                }
            ]
        }
    }
    {
        "message": "Deleted: 1"
    }
    {
        "message": {
            "people": []
        }
    }
