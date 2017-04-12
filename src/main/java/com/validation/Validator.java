package com.validation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by kambiz on 06/04/2017.
 */
public class Validator implements Validation {

    @Override
    public <T extends Function> List<Function<Class, Optional<T>>> getValidationFunctionsForDomain() {
        return null;
    }

    //For a particular DTO get all the validation rules
    //Apply it to the DTO
    //Do not fail fast and get all the errors


}
