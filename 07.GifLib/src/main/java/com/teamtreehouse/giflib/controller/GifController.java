package com.teamtreehouse.giflib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;

@Controller // indic asociatia cu controller de spring ( acest controller e pentru toate paginile )
public class GifController {
    @RequestMapping(value = "/") // pentru link dintre root directory si metoda
    ///@ResponseBody // raspunsul de la server, care exclude careva procesari suplimentare
    public String listGifs(){ // metoda pentru afisarea tuturor GIF-urilor, ce handle URI-ul root
        return "home"; /// tot aici putem include si un string, avind ResponsBody, afiseaza ca p, fara html template
    }
}
