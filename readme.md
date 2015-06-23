# Goal

* Create various implementations of a minimally simple addressbook service

# Why?

* To ease comparison between different languages/frameworks/approaches
* To demonstrate how to use the various languages/frameworks/approaches

## The Original Idea

http://todomvc.com but using server-side technologies.

# Spec

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

This is SQLite compatible syntax.

```sql
CREATE TABLE contacts (
id INTEGER NOT NULL PRIMARY UNIQUE AUTOINCREMENT,
name TEXT NOT NULL,
phone TEXT NOT NULL
);
```

## API

|        URI        | Responses[1]  |   HTTP Verb/Parameters                        |        Description                  |
|:------------------|---------------|-----------------------------------------------|------------------------------------:|
| /contacts         | 200, 204      | none                                          | list all contacts                   |
| /contact          | 201, 400      | PUT with JSON representation of new contact   | create a new contact                |
| /contact/<id>     | 200, 404      | GET                                           | display a specific contact          |
| /contact/<id>     | 200, 400, 404 | POST with JSON representation of *new* data   | update a specific contact           |
| /contact/<id>     | 204, 404      | DELETE                                        | delete a specific contact           |

See [RFC7231 Section 6][1] for details on HTTP/1.1 Status Codes

[1]: https://tools.ietf.org/html/rfc7231#section-6
