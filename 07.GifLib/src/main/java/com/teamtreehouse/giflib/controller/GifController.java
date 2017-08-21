package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.data.GifRepository;
import com.teamtreehouse.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;
import java.time.LocalDate;
import java.util.List;

@Controller // indic asociatia cu controller de spring ( acest controller e pentru toate paginile )
public class GifController {
    @Autowired // spune la framework to construct and assign gifrepository object as soon as it's needed
    private GifRepository gifRepository; // in cazul in care nu-l initializam => nullPointerException, putem initializa in metoda sau prin AutoWeired

    @RequestMapping(value = "/") // pentru link dintre root directory si metoda
    public String listGifs(ModelMap modelMap) { // metoda pentru afisarea tuturor GIF-urilor, ce handle URI-ul root
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home"; // html-ul inclus

    }
    @RequestMapping(value = "/gif/{name}") // prin {name}, noi preluam numele gif path
    // prin @PathVariable , parsam name-ul preluat mai sus
    public String gifDetails(@PathVariable String name, ModelMap modelMap){ // link intre controller si model care il folosim
        //compiler-bot.gif, el o vede din resources
        Gif gif = gifRepository.findByName(name); // accesam lista de gif-uri, fara a initializa ca obiect, folosind autowired
        modelMap.put("gif", gif); // adaug in model, obiectul necesar
        return "gif-details";
    }

}
