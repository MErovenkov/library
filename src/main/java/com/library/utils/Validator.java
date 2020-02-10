package com.library.utils;

import com.library.model.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validator {

    private static Validator validator;

    public static Validator getInstance() {
        if (validator == null) {
            validator = new Validator();
        }
        return validator;
    }

    //todo: need test log
    public boolean validationCheckName(String name) {
        boolean validate = false;

        if (name != null) {
            if (!name.trim().equals("")) {
                if (name.chars().allMatch(Character::isLetter)) {
                    validate = true;

                } else {
                    log.warn("Имя должна состоять только из букв "
                            + new Throwable().getStackTrace()[1].getMethodName());
                    //TODO: 02.02.2020 выбросить фронт exception
                }
            } else {
                log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                        +  " с пустым полем name");
                //TODO: 02.02.2020 выбросить фронт exception
            }
        } else {
            log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                    + " с name равным null");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return validate;
    }

    public boolean validationFullNameCheck(Person person) {
        boolean validate = false;

        if (person.getSurname() != null
                && person.getName() != null
                && person.getPatronymic() != null) {
            if (!(person.getSurname().trim().equals("")
                    && person.getName().trim().equals("")
                    && person.getPatronymic().trim().equals(""))) {
                if (person.getSurname().chars().allMatch(Character::isLetter)
                        && person.getName().chars().allMatch(Character::isLetter)
                        && person.getPatronymic().chars().allMatch(Character::isLetter)) {
                    validate = true;
                } else {
                    log.warn("Полное имя должна состоять только из букв "
                            + new Throwable().getStackTrace()[1].getMethodName());
                    //TODO: 02.02.2020 выбросить фронт exception
                }
            } else {
                log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                        +  " с пустым полем name");
                //TODO: 02.02.2020 выбросить фронт exception
            }
        } else {
            log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                    + " с name равным null");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return validate;
    }
}
