# Java EE 7 AddressBookMVC Example

Technologies used:

- Java 7
- Maven 3
- Glassfish 4
- Derby (bundled with Glassfish)
- Vagrant (for demo purposes)

Run `vagrant up` to deploy the app to <http://localhost:8888/javaee7addressbook-1.0-SNAPSHOT/>

Once Vagrant is running, you can run integration tests with `mvn test -Dtest=PeopleIT`
