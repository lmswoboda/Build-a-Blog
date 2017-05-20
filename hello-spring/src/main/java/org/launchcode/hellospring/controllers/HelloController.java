package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by lynnstrauss on 5/14/17.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (name == null){
            name = "World";
        }
        return "Hello " + name;
    }



    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloform() {


            List<String> lang = Arrays.asList("English,Spanish,French,German,Italian".split(","));
            List<String> hello = Arrays.asList("Hello,Hola,Bonjour,Guten Tag,Ciao".split(","));
            Map<String, String> map = new LinkedHashMap<String, String>();

            for (int i = 0; i<lang.size(); i++) {
                map.put(lang.get(i), hello.get(i));
        }


        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<select name = 'select'>" +
                    "<option value = 'English'>English</option>" +
                    "<option value = 'Spanish'>Spanish</option>" +
                    "<option value = 'French'>French</option>" +
                    "<option value = 'German'>German</option>" +
                    "<option value = 'Italian'>Italian</option>" +
                "</select>" +
                "<input typec='submit' value='Greet Me!'/>" +
                "</form>";
        return html;

        


    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {

        String name = request.getParameter("name");

        return "Hello " + name;
    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        return "Hello " + name;
    }



    @RequestMapping(value = "goodbye")
    public String goodbye() {

        return "redirect:/";

    }


}
