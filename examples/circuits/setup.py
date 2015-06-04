#!/usr/bin/env python

from setuptools import setup


def parse_requirements(filename):
    with open(filename, "r") as f:
        for line in f:
            if line and line[0] != "#":
                yield line.strip()


setup(
    name="server",
    version="dev",
    description="circuits addressbookmvc demo",
    scripts=(
        "server.py",
    ),
    install_requires=list(parse_requirements("requirements.txt")),
    zip_safe=True
)
