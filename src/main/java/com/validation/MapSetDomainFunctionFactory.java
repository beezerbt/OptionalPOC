package com.validation;

import com.funinfo.work.Errors;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.validation.domain.Contact;
import com.validation.domain.ContactUnit;
import com.validation.domain.Organization;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * Created by kambiz on 06/04/2017.
 */
public class MapSetDomainFunctionFactory implements Validation {

    private final Function<String, Optional<Organization>> toContactFromCwid = s-> Optional.ofNullable(s).map(Contact::new);
    private final Function<String, Optional<Organization>> toContactUnitFromKey = s-> Optional.ofNullable(s).map(ContactUnit::new);
    private final Map<Class, Function<String, Optional<Organization>>> functionInventory;

    public MapSetDomainFunctionFactory() {
        functionInventory = getValidationFunctionInventory();
    }

    private Map<Class, Function<String, Optional<Organization>>> getValidationFunctionInventory() {
        Map<Class, Function<String, Optional<Organization>>> validationFunctions = new TreeMap<>();
        validationFunctions.put(Contact.class, toContactFromCwid);
        validationFunctions.put(ContactUnit.class, toContactUnitFromKey);

        return validationFunctions;
    }

    public Map<Class, Validation<Errors, Organization>> applyTheFunctionToTheType(Organization input) {
        if(input instanceof Contact) {

        } else if (input instanceof ContactUnit) {

        } else {

        }
    }

    @Override
    public <T extends Function> List<Function<Class, Optional<T>>> getValidationFunctionsForDomain() {
        return null;
    }
}
