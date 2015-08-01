# Goal

* Create various implementations of a minimally simple addressbook service

# Why?

* To ease comparison between different languages/frameworks/approaches
* To demonstrate how to use the various languages/frameworks/approaches

## The Original Idea

http://todomvc.com but using server-side technologies.

* have a database with a table with names and phone numbers (and probably a surrogate key)
* the application should list all the entries (optionally paged)
* you should be able to add, edit and delete an entry
* each entry has 2 fields: name and phone number
* *any* other features are optional


# Implementation Guidelines

## General

* Go for clarity and simplicity which should ease comparison and improve the pedagogical win.
* Err on the side of excessive commenting.
* Implementations MUST include persistence.
* Implementations SHOULD behave as described in the API doc below, *whether it provides an actual API service or merely outputs HTML directly*.

## Contacts

A contact is completely described as:

```json
{
  "id":   <integer>,
  "name": "<string>",
  "phone": "<string>"
}
```

### SQL

If you choose to use an SQL-based persistence, here is some example [DDL]:

```sql
CREATE TABLE contacts (
id INTEGER NOT NULL PRIMARY UNIQUE AUTOINCREMENT,
name TEXT NOT NULL,
phone TEXT NOT NULL
);
```

The above is SQLite compatible syntax.

### File

If you choose to use file-based persistence, please use OS-independent path handling/access where possible.

## API

|        URI        | Responses[1]  |   HTTP Method (Verb) and Parameters           |        Description                  |
|:------------------|---------------|-----------------------------------------------|------------------------------------:|
| /contacts         | 200, 204      | GET                                           | list all contacts                   |
| /contact          | 201, 400      | POST with JSON representation of new contact  | create a new contact                |
| /contact/\<id>    | 200, 404      | GET                                           | display a specific contact          |
| /contact/\<id>    | 200, 400, 404 | PUT with JSON representation of *new* data    | update a specific contact           |
| /contact/\<id>    | 204, 404      | DELETE                                        | delete a specific contact           |

* See [RFC7231 Section 6][1] for details on HTTP/1.1 Status Codes.
* See [RFC7231 Section 4.3][2] for details on HTTP/1.1 Method Definitions.


[1]: https://tools.ietf.org/html/rfc7231#section-6
[2]: https://tools.ietf.org/html/rfc7231#section-4.3
[DDL]: https://en.wikipedia.org/wiki/Data_definition_language
