package com.xxx.component.validation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.xxx.component.MapSetDomainFunctionFactory;
import com.xxx.domain.Contact;
import com.xxx.domain.ContactUnit;
import com.xxx.domain.Organization;
import fj.data.Validation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kambiz on 06/04/2017.
 */
public class OrganizationValidator {

    private List<Validation<String, Organization>> results;
    public Multimap<Class, Validation<String, Organization>> filterValidationFunctions(Organization input) {
        Multimap<Class, Validation<String, Organization>> result = ArrayListMultimap.create();
        if(input instanceof Contact) {
            List collect = MapSetDomainFunctionFactory.getValidationFunctionInventory()
                    .get(input.getClass())
                    .stream()
                    .collect(Collectors.toList());
            results = validate(Contact.class, input, collect);
        } else if (input instanceof ContactUnit) {
            //TODO::for contact unit.
        } else {
            Validation.fail("Unsupported Validation for class type:"+input.getClass());
        }
        return result;
    }

    private List<Validation<String, Organization>> validate(Class organizationType, Organization organization, List applicableFunctions ) {

        return null;
    }
}
