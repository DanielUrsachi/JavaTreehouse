package com.teamtreehouse.courses.model;

import java.util.ArrayList;
import java.util.List;

public class SimpleCourseIdeaDAO implements CourseIdeaDAO { // clasa ce implementeaza metodele abstractiei
    private List<CourseIdea> ideas;

    public SimpleCourseIdeaDAO() {
        ideas = new ArrayList<>();
    }

    @Override
    public boolean add(CourseIdea idea) {
        return ideas.add(idea);
    }

    @Override
    public List<CourseIdea> findAll() {
        return new ArrayList<>(ideas); // returnam o copie a listei, pentru excluderea posibilitatii de apend
    }

    @Override
    public CourseIdea findBySlug(String slug) {
        return ideas.stream() // permite parcurgerea elementelor
                .filter(idea -> idea.getSlug().equals(slug)) // returneaza elementele cu slug-ul introdus
                .findFirst() //returneaza doar primul element
                .orElseThrow(NotFoundException::new); // throw exceptie din clasa ce deriveaza RunTime Exception
    }
}
