#!/usr/bin/env python
# Module:   main
# Date:     31st March 2015
# Author:   James Mills, prologic at shortcircuit dot net dot au


"""Address Book MVC Example"""


from __future__ import print_function


from os import environ, path
from argparse import ArgumentDefaultsHelpFormatter, ArgumentParser


from circuits import Component, Debugger
from circuits.web import Server, Sessions, Static

from jinja2 import Environment, FileSystemLoader


from .root import Root
from .version import version
from .utils import parse_bind
from .auth import LoginManager
from .tools import JinjaRenderer


defaults = {
    "appname": "AddressBook",
    "version": version,
}


class App(Component):

    def init(self, args):
        if args.debug:
            Debugger().register(self)

        bind = parse_bind(args.bind)

        basedir = path.dirname(__file__)
        staticdir = path.join(basedir, "static")
        templatesdir = path.join(basedir, "templates")

        Server(*bind).register(self)

        Root().register(self)
        Static(docroot=staticdir).register(self)

        env = Environment(loader=FileSystemLoader(templatesdir))
        JinjaRenderer(env, defaults).register(self)

        Sessions().register(self)
        LoginManager().register(self)

    def signal(self, *args):
        raise SystemExit(0)


def parse_args():
    parser = ArgumentParser(
        description=__doc__,
        formatter_class=ArgumentDefaultsHelpFormatter
    )

    parser.add_argument(
        "-b", "--bind", action="store", dest="bind", metavar="INT", type=str,
        default=environ.get("BIND", "0.0.0.0:1338"),
        help="Interface and Port to Bind to"
    )

    parser.add_argument(
        "-d", "--debug", action="store_true", dest="debug",
        default=environ.get("DEBUG", False),
        help="Enable Debug Mode"
    )

    return parser.parse_args()


def main():
    App(parse_args()).run()


if __name__ == "__main__":
    main()
