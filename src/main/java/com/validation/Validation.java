package com.validation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by kambiz on 06/04/2017.
 */
@FunctionalInterface
public interface Validation {

    <T> List<Function<String, Optional<T>>> getValidationFunctionsForDomain();
}
