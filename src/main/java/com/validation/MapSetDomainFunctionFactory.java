package com.validation;

import com.funinfo.work.Errors;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.validation.domain.Contact;
import com.validation.domain.ContactUnit;
import com.validation.domain.Organization;

import java.util.*;
import java.util.function.Function;

/**
 * Created by kambiz on 06/04/2017.
 */
public class MapSetDomainFunctionFactory implements Validation {

    private final Function<String, Optional<Organization>> toContactFromCwid = s-> Optional.ofNullable(s).map(Contact::new);
    private final Function<String, Optional<Organization>> toContactUnitFromKey = s-> Optional.ofNullable(s).map(ContactUnit::new);
    private final Multimap<Class, Function<String, Optional<Organization>>> functionInventory;

    public MapSetDomainFunctionFactory() {
        functionInventory = getValidationFunctionInventory();
    }

    private Multimap<Class, Function<String, Optional<Organization>>> getValidationFunctionInventory() {
        Multimap<Class, Function<String, Optional<Organization>>> validationFunctions = ArrayListMultimap.create();
        validationFunctions.put(Contact.class, toContactFromCwid);
        validationFunctions.put(ContactUnit.class, toContactUnitFromKey);

        return validationFunctions;
    }

    public  Multimap<Class, fj.data.Validation<Errors, Organization>> applyTheFunctionToTheType(Organization input) {
        Multimap<Class, fj.data.Validation<Errors, Organization>> result = ArrayListMultimap.create();
        if(input instanceof Contact) {
            //TODO::continue from here.
            functionInventory.get(Contact.class);
        } else if (input instanceof ContactUnit) {

        } else {

        }
        return result;
    }

    @Override
    public <T extends Function> List<Function<Class, Optional<T>>> getValidationFunctionsForDomain() {
        return null;
    }
}
