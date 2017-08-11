package com.teamtreehouse.courses;

import com.teamtreehouse.courses.model.CourseIdea;
import com.teamtreehouse.courses.model.CourseIdeaDAO;
import com.teamtreehouse.courses.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main { //gen de controller
    public static void main(String[] args) {
        CourseIdeaDAO dao = new SimpleCourseIdeaDAO(); // interfetei ii atribuim derivata
        staticFileLocation("/public"); //cauta resursele din HTML din directoria initiala indicata

        before(((request, response) -> { // daca nu are path, este pentru toate
            if (request.cookie("username") != null){ // daca  are cookies cu username
                request.attribute("username", request.cookie("username")); // cookie transformam in atribut!
            }
        }));

        before("/ideas", ((request, response) -> { // metoda rulata inainte de oricare functe pentru pathul ideas
            //TODO:csd - Send message about redirect ... soehow
            if (request.attribute("username") == null){ // daca nu avem cookie
                response.redirect("/"); //redirect la root directory
                halt(); // break la oricare metoda in continuare (get/post)
            }
        }));

        get("/hello", (req, res) -> "Hello World"); //get http method din URI-ul: /hello + prin lambda transmite Hello World

        get("/", (req, res) -> { //pentru afisarea html-ului din hbs
            Map<String, String> model = new HashMap<>();
            model.put("username", req.attribute("username")); // importam cookie la deskiderea html-page-ului
            return new ModelAndView(model, "index.hbs"); // returneaza file-ul html, dupa trimiterea model-ului
        }, new HandlebarsTemplateEngine()); // metoda necesara pentu asta


       post("/sign-in", (req, res) -> { // la metoda post
           Map<String, String> model = new HashMap<>();
           String username = req.queryParams("username");
           res.cookie("username",username); //setam cookie dupa identificator si input
           model.put("username", username); // astfel transmitem numele variabilei si valoarea sa
            return new ModelAndView(model, "sign-in.hbs"); // arunca pe sign-in
        }, new HandlebarsTemplateEngine());

       get("/ideas", (request, response) -> { // get list of subjects
           Map<String, Object> model = new HashMap<>(); // atentie Object!, obiect nedefinit pentru list
           model.put("ideas",dao.findAll()); // returneaza toate ideas
           return new ModelAndView(model,"ideas.hbs");
       }, new HandlebarsTemplateEngine());

       post("/ideas", (request, response) -> { // adding an object idea
           String title = request.queryParams("title"); // cere field-ul cu idea
           CourseIdea courseIdea = new CourseIdea(title, request.attribute("username")); // introduce field-ul cu ideea, si cere din cookie numele
           dao.add(courseIdea); //adaugarea in lista de idei
           response.redirect("/ideas"); // redirect la ea insasi = refresh la pagina, pentru ca se duce la get
           return null;
       });
       post("/ideas/:slug/vote", ((request, response) -> { // specificata metoda pentru vote si var prin slug
           CourseIdea idea = dao.findBySlug(request.params("slug")); // extragerea elementului pentru vot dupa slug (dupa parametru)
           idea.addVoter(request.attribute("username")); //adaugarea numelui votului din cookie in element
           response.redirect("/ideas"); // redirect
           return null;
       }));


    }
}
