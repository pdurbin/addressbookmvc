# Module:   utils
# Date:     31st March 2015
# Author:   James Mills, prologic at shortcircuit dot net dot au


"""Utilities"""


def parse_bind(s, default_port=1338):
    # XXX: We ignore the protocol for now
    if "://" in s:
        protocol, s = s.split("://", 1)

    if ":" in s:
        address, port = s.split(":", 1)
        port = int(port)
    else:
        address, port = s, default_port

    return address, port
