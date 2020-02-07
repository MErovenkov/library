package com.library.utils;

import com.library.model.Genre;
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

    //todo: need test
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

    public boolean validationFullNameCheck(String surname, String name, String patronymic) {
        boolean validate = false;

        if (surname != null
                && name != null
                && patronymic != null) {
            if (!(surname.trim().equals("")
                    && name.trim().equals("")
                    && patronymic.trim().equals(""))) {
                if (surname.chars().allMatch(Character::isLetter)
                        &&  name.chars().allMatch(Character::isLetter)
                        && patronymic.chars().allMatch(Character::isLetter)) {
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
