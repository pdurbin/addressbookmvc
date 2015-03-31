# Module:   root
# Date:     31st March 2015
# Author:   James Mills, prologic at shortcircuit dot net dot au


"""Root"""


from circuits.web import Controller


from .events import render


class Root(Controller):

    def GET(self, view=None):
        return self.fire(render(view or "index", session=self.session), "web")
