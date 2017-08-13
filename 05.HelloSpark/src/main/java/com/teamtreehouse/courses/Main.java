package com.teamtreehouse.courses;

import com.teamtreehouse.courses.model.CourseIdea;
import com.teamtreehouse.courses.model.CourseIdeaDAO;
import com.teamtreehouse.courses.model.NotFoundException;
import com.teamtreehouse.courses.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final String FLASH_MESSAGE_KEY = "flash_message"; // key pentru flash message

    public static void main(String[] args) {  //gen de controller
        CourseIdeaDAO dao = new SimpleCourseIdeaDAO(); // interfetei ii atribuim derivata
        staticFileLocation("/public"); //cauta resursele din HTML din directoria initiala indicata

        before(((request, response) -> { // daca nu are path, este pentru toate
            if (request.cookie("username") != null) { // daca  are cookies cu username
                request.attribute("username", request.cookie("username")); // cookie transformam in atribut!
            }
        }));

        before("/ideas", ((request, response) -> { // metoda rulata inainte de oricare functe pentru pathul ideas
            if (request.attribute("username") == null) { // daca nu avem cookie
                setFlashMessage(request,"Whoops, please sing in first!"); // setarea mesajului ca nu suntem logat, inainte de redirect
                response.redirect("/"); //redirect la root directory
                halt(); // break la oricare metoda in continuare (get/post)
            }
        }));

        get("/hello", (req, res) -> "Hello World"); //get http method din URI-ul: /hello + prin lambda transmite Hello World

        get("/", (req, res) -> { //pentru afisarea html-ului din hbs
            Map<String, String> model = new HashMap<>();
            model.put("username", req.attribute("username")); // importam cookie la deskiderea html-page-ului
            model.put("flashMessage", captureFlashMessage(req)); // adaugarea in model flash mesajul setat in cazul in care user-ul ne-logat este redirectionat in root directory
            return new ModelAndView(model, "index.hbs"); // returneaza file-ul html, dupa trimiterea model-ului
        }, new HandlebarsTemplateEngine()); // metoda necesara pentu asta


        post("/sign-in", (req, res) -> { // la metoda post
            Map<String, String> model = new HashMap<>();
            String username = req.queryParams("username");
            res.cookie("username", username); //setam cookie dupa identificator si input
            model.put("username", username); // astfel transmitem numele variabilei si valoarea sa
            return new ModelAndView(model, "sign-in.hbs"); // arunca pe sign-in
        }, new HandlebarsTemplateEngine());

        get("/ideas", (request, response) -> { // get list of subjects
            Map<String, Object> model = new HashMap<>(); // atentie Object!, obiect nedefinit pentru list
            model.put("ideas", dao.findAll()); // returneaza toate ideas
            model.put("flashMessage", captureFlashMessage(request)); // adaugarea flashMessage in model pentru afisare, folosim in loc ce getFlashMessage, pentru ca sa se afiseze doar o data
            return new ModelAndView(model, "ideas.hbs");
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
            boolean added = idea.addVoter(request.attribute("username")); //adaugarea numelui votului din cookie in element
            if (added) { //daca adaugarea a avut succes
                setFlashMessage(request, "Thanks for your vote"); // setam mesajul pentru flashMessage
            }else {
                setFlashMessage(request, "You already voted!");
            }
            response.redirect("/ideas"); // redirect
            return null;
        }));
        get("ideas/:slug", (request, response) -> { //pagina cu lista de Voters
            Map<String, Object> model = new HashMap<>();
            model.put("idea", dao.findBySlug(request.params("slug"))); // introducea key = idea si obiectul CourseIdea
            return new ModelAndView(model, "idea.hbs");

        }, new HandlebarsTemplateEngine());

        exception(NotFoundException.class, (exc, req, res) -> { // in cazul exceptei de tipul clasei NotFoundException
            res.status(404); // daca pagina nu e gasit
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            String html = engine.render(
                    new ModelAndView(null, "not-found.hbs"));
            res.body(html); // introducem in respone  stringul cu modelAndView


        });


    }

    //flash!!
    private static void setFlashMessage(Request request, String message) { // metoda pentru flash messages
        request.session().attribute(FLASH_MESSAGE_KEY, message); // adaugam inca un atribut la request, cu textul transmis pentru flash

    }

    private static String getFlashMessage(Request request) { // parsarea flashMessage, daca acesta exista
        if (request.session(false) == null) { // daca aceasta inca nu este creeata = null
            return null;
        }
        if (!request.session().attributes().contains(FLASH_MESSAGE_KEY)) { // daca acel existent, nu contine flashMessageKey = null
            return null;
        }
        return (String) request.session().attribute(FLASH_MESSAGE_KEY);
    }


    private static String captureFlashMessage(Request request) { // pentru evitarea afisarii la refresh, stergerea mesajului dupa ce utilizatorul la vazut
        String message = getFlashMessage(request);
        if(message != null){ // daca avem atribut cu flash - message, il sterge, pentru ca a fost deja afisat cu un rind mai sus, deci, poate fi sters, pentru a nu aparea la refresh
            request.session().removeAttribute(FLASH_MESSAGE_KEY);
        }
        return message;

    }
}
