package com.validation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by kambiz on 06/04/2017.
 */
@FunctionalInterface
public interface OrganizationValidation {

    <T extends Function> List<Function<Class, Optional<T>>> getValidationFunctionsForDomain();

    /*default <T extends Class, U extends List<Function>> List<Function> findListOfApplicableFunctionsForType(Class validationType) {
        List<Function<Class, Optional<T>>> allValidationFunctions = getValidationFunctionsForDomain();
        allValidationFunctions
                .stream()
                .filter(classOptionalFunction -> classOptionalFunction.apply(validationType)
    } */
}
