#!/usr/bin/env python

from os import environ, path
from json import dumps, loads


from circuits.web import Server, JSONController, Static


class AddressAPI(JSONController):

    channel = "/addresses"

    def _load(self):
        return (
            loads(open("addresses.json", "r").read())
            if path.exists("addresses.json")
            else []
        )

    def _save(self, data):
        with open("addresses.json", "w") as f:
            f.write(dumps(data))

    def add(self, *args, **kwargs):
        data = self._load()
        data.append(kwargs)  # Just add the entire form :)
        self._save(data)
        return {"success": True}

    def delete(self, *args, **kwargs):
        data = self._load()
        for i, entry in enumerate(data):
            if entry["id"] == kwargs["id"]:
                del data[i]
                break
        self._save(data)
        return {"success": True}

    def list(self, *args, **kwargs):
        data = self._load()
        if not kwargs:
            return data
        results = []
        for i, entry in enumerate(data):
            if data["full_name"].startswith(kwargs["full_name"]):
                results.append(entry)
            elif data["email"].startswith(kwargs["email"]):
                results.append(entry)
        return results

    def search(self, *args, **kwargs):
        data = self._load()
        for i, entry in enumerate(data):
            if entry["id"] == kwargs["id"]:
                return {"success": True, "contact": entry}
        return {"success": False}


app = Server(("0.0.0.0", int(environ.get("PORT", 3000))))
AddressAPI().register(app)
Static(docroot="app").register(app)

app.run()
