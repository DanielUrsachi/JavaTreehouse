package com.teamtreehouse.courses.model;

import java.util.List;

public interface CourseIdeaDAO { // interfata abstracta, ce defineste pattern-ul
    boolean add (CourseIdea idea);
    List<CourseIdea> findAll();
    CourseIdea findBySlug(String slug);
}
