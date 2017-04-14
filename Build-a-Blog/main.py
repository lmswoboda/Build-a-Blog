#!/usr/bin/env python
#
# Copyright 2007 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
import webapp2
import cgi
import os
import jinja2
from google.appengine.ext import db


# set up jinja
template_dir = os.path.join(os.path.dirname(__file__), "templates")
jinja_env = jinja2.Environment(loader = jinja2.FileSystemLoader(template_dir),
                                autoescape = True)

#set-up per Udacity
class Handler(webapp2.RequestHandler):
    def write(self, *a, **kw):
        self.response.out.write(*a, **kw)

    def render_str(self, template, **params):
        t = jinja_env.get_template(template)
        return t.render(params)

    def render(self, template, **kw):
        self.write(self.render_str(template, **kw))

class Submission(db.Model):
    title = db.StringProperty(required = True)
    body = db.TextProperty(required = True)
    created = db.DateTimeProperty(auto_now_add = True)

class MainPage(Handler):
    def render_front(self, title = "", body="", error=""):
        submissions = db.GqlQuery("SELECT * From Submission ORDER BY created DESC Limit 5")
        self.render("main.html", title = title, body = body, submissions = submissions,
        error = error)

    def get(self):
        self.render_front()


class NewPost(Handler):
    def render_newpost(self, title = "", body="", error="", submissions=""):
        self.render("newpost.html", title = title, body = body,
        submissions = submissions, error = error)
#
    def get(self):
        self.render_newpost()

    def post(self):
        title = self.request.get("title")
        body = self.request.get("body")

        if title and body:
            sub = Submission(title = title, body = body)

            sub.put()
            id = str(sub.key().id())
            self.redirect("/blog/" + id)

        else:
            error = "We need both a title and a body."
            self.render_newpost(title, body, error)


class ViewPostHandler(Handler):

    def get(self, id, id_post =""):
        # pass #replace this with some code to handle the request
        id_post = Submission.get_by_id(int(id))

        if id_post:
            title = id_post.title
            body = id_post.body
            self.render("render-by-id.html", title = title, body = body)

        else:
            error = "There is no post with that id."
            self.render("render-by-id.html", error = error)





app = webapp2.WSGIApplication([
    ('/', MainPage),
    ('/newpost', NewPost),
    webapp2.Route('/blog/<id:\d+>', ViewPostHandler)
], debug=True)
