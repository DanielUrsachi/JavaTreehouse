package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.model.Gif;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;
import java.time.LocalDate;

@Controller // indic asociatia cu controller de spring ( acest controller e pentru toate paginile )
public class GifController {
    @RequestMapping(value = "/") // pentru link dintre root directory si metoda

    public String listGifs() { // metoda pentru afisarea tuturor GIF-urilor, ce handle URI-ul root
        return "home"; // html-ul inclus

    }
    @RequestMapping(value = "/gif")
    public String gifDetails(ModelMap modelMap){ // link intre controller si model care il folosim
        //compiler-bot.gif, el o vede din resources
        Gif gif = new Gif("compiler-bot", LocalDate.of(2015,2,13), "Chris Ramacciotti", true);
        modelMap.put("gif", gif); // adaug in model, obiectul necesar
        return "gif-details";

    }
}
