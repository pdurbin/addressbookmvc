# Module:   tools
# Date:     31st March 2015
# Author:   James Mills, prologic at shortcircuit dot net dot au


"""Tools"""


from circuits import Component


class JinjaRenderer(Component):

    channel = "web"

    def init(self, env, defaults):
        self.env = env
        self.defaults = defaults

    def render(self, name, **data):
        context = self.defaults.copy()
        context.update(data)
        template = self.env.get_template("{0:s}.html".format(name))

        return template.render(**context)
