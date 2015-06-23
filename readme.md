# Goal

* Create various implementations of a minimally simple addressbook service

# Why?

* To ease comparison between different languages/frameworks/approaches
* To demonstrate how to use the various languages/frameworks/approaches

## The Original Idea

http://todomvc.com but using server-side technologies.

# Implementation Guidelines

## General

* Go for clarity and simplicity which should ease comparison and improve the pedagogical win.
* Err on the side of excessive commenting.
* Implementations MUST include persistence.
* Implementations SHOULD behave as described in the API doc below, whether it provides an actual API service or merely outputs HTML directly.

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

|        URI        | Responses[1]  |   HTTP Verb/Parameters                        |        Description                  |
|:------------------|---------------|-----------------------------------------------|------------------------------------:|
| /contacts         | 200, 204      | none                                          | list all contacts                   |
| /contact          | 201, 400      | PUT with JSON representation of new contact   | create a new contact                |
| /contact/\<id>    | 200, 404      | GET                                           | display a specific contact          |
| /contact/\<id>    | 200, 400, 404 | POST with JSON representation of *new* data   | update a specific contact           |
| /contact/\<id>    | 204, 404      | DELETE                                        | delete a specific contact           |

See [RFC7231 Section 6][1] for details on HTTP/1.1 Status Codes

[1]: https://tools.ietf.org/html/rfc7231#section-6
[DDL]: https://en.wikipedia.org/wiki/Data_definition_language
