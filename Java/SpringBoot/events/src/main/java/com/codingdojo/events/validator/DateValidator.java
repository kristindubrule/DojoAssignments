package com.codingdojo.events.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.codingdojo.events.models.*;
import java.util.Date;

@Component
public class DateValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Event event = (Event) object;

        if (event.getEventDate() != null) {
            if (event.getEventDate().compareTo(new Date()) <= 0) {
                errors.rejectValue("eventDate", "Future");
            }
        }
    }
}
