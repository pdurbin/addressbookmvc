# Phonebook demo by aditsu

[Try it online](http://phonebookdemo.aditsu.net/)

<table>
<tr><th align="left">Language</th><td>java</td></tr>
<tr><th align="left">Web framework</th><td><a href="https://wicket.apache.org/">wicket</a></td></tr>
<tr><th align="left">Database library</th><td><a href="http://depeche.sourceforge.net/">depeche</a></td></tr>
<tr><th align="left">Database server</th><td><a href="http://www.postgresql.org/">postgresql</a></td></tr>
<tr><th align="left">Embedded servlet container<br>(optional)</th><td><a href="http://www.eclipse.org/jetty/">jetty</a></td></tr>
<tr><th align="left">Build tool</th><td><a href="http://ant.apache.org/">ant</a></td></tr>
<tr><th align="left">Dependency management</th><td><a href="http://ant.apache.org/ivy/">ivy</a></td></tr>
<tr><th align="left">IDE (optional)</th><td><a href="http://www.eclipse.org/">eclipse</a></td></tr>
</table>

You can build the phonebook application as a web directory and run it in any servlet container such as jetty,  
or you can run it as a java application, with jetty embedded.

**Database setup:** Create a postgres database called "phonebook" and run the command from `setup.sql`

## 1. Building a web directory

Running `ant` will automatically build the `webdir` target. It requires ivy.  
If you don't have ivy, you will get an error message like:

```
[…] failed to create task or type antlib:org.apache.ivy.ant:settings
[…]
This appears to be an antlib declaration.
Action: Check that the implementing library exists in one of:
        -(suggested directories)
        -a directory added on the command line with the -lib argument
```

Download the ivy binary from http://ant.apache.org/ivy/download.cgi, extract it and either:
- copy `ivy-*.jar` to one of the suggested directories above and run `ant` again  
or
- run `ant -lib (directory containing ivy-*.jar)`

ant should now succeed and build a web directory in `build/webdir`.

Now you can take the web directory, optionally zip it into a war file, and run it in a servlet container.

Instructions for jetty standalone (tested with jetty 8.1):
- copy/move the `webdir` folder to `webapps/phonebook`
- if running jetty for the first time, clear the `contexts` folder
- copy `phonebook.xml` to `contexts`
- start jetty (`bin/jetty.sh start` from jetty's folder)
- access the application at <http://localhost:8080>

## 2. Running jetty embedded

### Using ant:

First, build the project: `ant build` (refer to point 1 regarding ivy)  
Once it is built, execute `ant run` and access the application at <http://localhost:8080>

### Using eclipse:

- install ivy and ivyde - instructions at https://ant.apache.org/ivy/ivyde/download.html
- configure ivy under `preferences -> ivy -> settings`; use the provided `ivysettings.xml`
- import the phonebook project; eclipse should build it automatically
- run `StartPhonebook`
- access the application at <http://localhost:8080>
