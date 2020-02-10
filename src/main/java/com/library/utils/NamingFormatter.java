package com.library.utils;

import com.library.model.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NamingFormatter {

    private static NamingFormatter namingFormatter;

    public static NamingFormatter getInstance() {
        if (namingFormatter == null) {
            namingFormatter = new NamingFormatter();
        }
        return namingFormatter;
    }


    public synchronized String formatForName(String name) throws StringIndexOutOfBoundsException {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }

    public synchronized Person formatFullName(Person person) throws StringIndexOutOfBoundsException {
        person.setSurname(person.getSurname().substring(0, 1).toUpperCase()
                + person.getSurname().substring(1).toLowerCase());
        person.setName(person.getName().substring(0, 1).toUpperCase()
                + person.getName().substring(1).toLowerCase());
        person.setPatronymic(person.getPatronymic().substring(0, 1).toUpperCase()
                + person.getPatronymic().substring(1).toLowerCase());

        return person;
    }
}
