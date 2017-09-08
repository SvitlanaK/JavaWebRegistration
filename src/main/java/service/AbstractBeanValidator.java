package service;

import repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

abstract class AbstractBeanValidator implements IValidator {

    final Map<String, String> errors = new HashMap<>();

    void simpleValidation(String value, Pattern pattern, String messageKey, String messageValue) {
        if (Objects.equals(value, "") || value == null) {
            errors.put(messageKey, messageKey + " is null");
        } else if (!pattern.matcher(value).matches()) {
            errors.put(messageKey, messageValue);
        }
    }
}