package com.teamtreehouse.docgen;

import com.teamtreehouse.math.MathUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main { // punctul de intrare / rulare
    public static void main(String[] args) {

        ///reflection
        System.out.println("Reflection work: ");
        // Get a class object
        Class<?> clazz = MathUtils.class; // obtinem un obiect de tip clasa, in care includem clasa noastra (clazz = reserved-keyword in java), astfel atasez la clazz, clasa mea
            
        // Get all declared methods
        Method[] methods = clazz.getDeclaredMethods(); // preiau metodele din MathUtils ce este in clazz acum
        
        // Loop over methods
        for(Method m : methods) { // parcurg toate metodele

            // Display method name
            System.out.printf("name: %s%n",m.getName());
            
            // Display parameter count
            System.out.printf("\t# params: %s%n",m.getParameterCount());
            
            // Display return type
            System.out.printf("\treturn type: %s%n",m.getReturnType().getSimpleName());
            
            // Display modifiers of access
            int mods = m.getModifiers();
            String modStr = Modifier.toString(mods);
            System.out.printf("\tmodifiers: %s%n",modStr);
        }

        /// documentation
        // TODO: Process the MathUtils class's annotations
        System.out.println("Anntotations work: ");
        DocProcessor.process(MathUtils.class);

    }
}