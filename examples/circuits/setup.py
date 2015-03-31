#!/usr/bin/env python


from setuptools import setup, find_packages


from addressbook.version import version


def parse_requirements(filename):
    with open(filename, "r") as f:
        for line in f:
            if line and line[0] != "#":
                yield line.strip()


setup(
    name="addressbook",
    version=version,
    description="Address Book MVC Example",
    long_description=open("README.rst", "r").read(),
    author="James Mills",
    author_email="James Mills, prologic at shortcircuit dot net dot au",
    url="https://github.com/prologic/addressbookmvc",
    download_url="https://github.com/prologic/addressbookmvc/archive/master.zip",
    classifiers=[],
    license="MIT",
    keywords="address book example",
    platforms="POSIX",
    packages=find_packages("src"),
    install_requires=list(parse_requirements("requirements.txt")),
    entry_points={
        "console_scripts": [
            "addressbook=addressbook.main:main"
        ]
    },
    zip_safe=True
)
