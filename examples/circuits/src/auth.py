# Module:   auth
# Date:     31st March 2015
# Author:   James Mills, prologic at shortcircuit dot net dot au


"""Authentication"""


from circuits import handler, Component
from circuits.web import redirect, Controller


from .events import render


class Login(Controller):

    channel = "/login"

    def GET(self):
        return self.fire(render("login"), "web")

    def POST(self, username=None, password=None):
        if username == "admin" and password == "admin":
            self.session.update(active=True, username=username)

            return self.redirect("/")

        error = "Invalid Username or Password!"

        return self.fire(render("login", error=error), "web")


class Logout(Controller):

    channel = "/logout"

    def GET(self):
        return self.fire(render("logout"), "web")

    def POST(self):
        self.session.clear()

        return self.redirect("/login")


class LoginManager(Component):

    channel = "web"

    def init(self, login="/login", logout="/logout"):
        self.login = login
        self.logout = logout

        Login(self.login).register(self)
        Logout(self.logout).register(self)

    @handler("request", priority=0.2)
    def _on_request(self, event, req, res):
        if req.path == self.login:
            return

        if not req.session.get("active", False):
            print("No active session found!")
            print(req.session)
            event.stop()
            return redirect(req, res, self.login)
