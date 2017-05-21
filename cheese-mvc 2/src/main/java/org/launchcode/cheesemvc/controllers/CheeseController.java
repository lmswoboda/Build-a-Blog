package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by lynnstrauss on 5/18/17.
 */


@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

//    Took Arraylist from index method and put here so accessible to all methods here and added Static in front
//    per instructor and will be explained later.  Also removed hard coded Arraylist elements from index method.
//    static?--this ArrayList exists only when this application is running.  If stop, ArrayList goes away-no database?
    static HashMap<String, String> cheeses = new HashMap<>();
    static ArrayList<String> removeList = new ArrayList<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value ="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, String cheeseDescription) {
        cheeses.put(cheeseName, cheeseDescription);

//        Redirects to /cheese and since we are in /cheese nothing put in below.
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Remove");

        return "cheese/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheese) {

        for (String c : cheese) {
            cheeses.remove(c);
        }
        return "redirect:";
    }


}
