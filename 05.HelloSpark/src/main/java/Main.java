import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World"); //get http method din URI-ul: /hello + prin lambda transmite Hello World

        get("/", (req, res) -> { //pentru afisarea html-ului din hbs
            Map<String, String> model = new HashMap<>();
            model.put("username", req.cookie("username")); // importam cookie la deskiderea html-page-ului
            return new ModelAndView(model, "index.hbs"); // returneaza file-ul html, dupa trimiterea model-ului
        }, new HandlebarsTemplateEngine()); // metoda necesara pentu asta


       post("/sign-in", (req, res) -> { // la metoda post
           Map<String, String> model = new HashMap<>();
           String username = req.queryParams("username");
           res.cookie("username",username); //setam cookie dupa identificator si input
           model.put("username", username); // astfel transmitem numele variabilei si valoarea sa
            return new ModelAndView(model, "sign-in.hbs"); // arunca pe sign-in
        }, new HandlebarsTemplateEngine());


    }
}
