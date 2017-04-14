package com.validation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.validation.domain.Contact;
import com.validation.domain.ContactUnit;
import com.validation.domain.Organization;
import fj.data.Validation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by kambiz on 06/04/2017.
 */
public class MapSetDomainFunctionFactory implements OrganizationValidation {

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

    public  Multimap<Class, fj.data.Validation<String, Organization>> applyTheFunctionToTheType(Organization input) {
        assert input!=null;
        Multimap<Class, fj.data.Validation<String, Organization>> result = ArrayListMultimap.create();

        if(input instanceof Contact) {
            //TODO::continue from here.
            List<Function<String, Optional<Organization>>> applicableFunctions = functionInventory.get(Contact.class)
                    .stream()
                    .collect(Collectors.toList());
           Optional<Organization> appliedResult = applicableFunctions.get(0).apply(((Contact) input).getCwid());
           result.put(input.getClass(), appliedResult.isPresent()? Validation.success(input): Validation.fail("Validation failed for"+ input.getClass()));
        } else if (input instanceof ContactUnit) {
            //TODO::for contact unit.
        } else {
            Validation.fail("Unsupported Validation for class type:"+input.getClass());
        }
        return result;
    }

    @Override
    public <T extends Function> List<Function<Class, Optional<T>>> getValidationFunctionsForDomain() {
        return null;
    }
}
