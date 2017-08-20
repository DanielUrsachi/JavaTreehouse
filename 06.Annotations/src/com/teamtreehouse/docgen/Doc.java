package com.teamtreehouse.docgen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME) // rentetion that works in runtime, aici avem posibilitatea de introducerea a mai multor variabile
@Target({ElementType.TYPE,ElementType.METHOD}) // aici indicam locurile unde anotatia poate fi folosita, in cazul nostru pentru thype == class, si metode

public @interface Doc { // definition of custom annotation
    //containere default ale caracteristicilor ca atribute

    /** Description of class or method */
    String desc() default "";

    /** Description of parameters, if annotated element is a method & has parameters */
    String[] params() default {};
    
    /** Description of return value, if annotated element is a method & isn't void */
    String returnVal() default "";
}