package com.xxx.component.validation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.xxx.component.MapSetDomainFunctionFactory;
import com.xxx.domain.Contact;
import com.xxx.domain.ContactUnit;
import com.xxx.domain.Organization;
import fj.data.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class is to be a generic validator.
 * Its current incarnation is a work in progress and is specific to the
 * organization part of the domain.
 * Created by kambiz on 06/04/2017.
 */
public class OrganizationValidator {
    List<Validation<String, Organization>> results = new ArrayList<>();

    public Multimap<Class, Validation<String, Organization>> filterValidationFunctions(Organization input) {
        Multimap<Class, Validation<String, Organization>> result = ArrayListMultimap.create();
        if(input instanceof Contact) {
            List<Function<String, Optional<Organization>>> collect = MapSetDomainFunctionFactory.getValidationFunctionInventory()
                    .get(input.getClass())
                    .stream()
                    .collect(Collectors.toList());
            results = validate(input, collect);
        } else if (input instanceof ContactUnit) {
            results.add(Validation.fail("filterValidationFunctions::Unsupported Validation for class type:"+input.getClass()));
        } else {
            results.add(Validation.fail("filterValidationFunctions::Unsupported Validation for class type:"+input.getClass()));
        }
        return result;
    }

    private List<Validation<String, Organization>> validate(Organization criteria, List<Function<String, Optional<Organization>>> applicableFunctions ) {
        List<Validation<String, Organization>> results = new ArrayList<>();
        for (Function<String, Optional<Organization>> fn:applicableFunctions) {
            if(criteria instanceof Contact) {
                Optional<Organization> j = fn.apply(((Contact)criteria).getCwid());
                if(j.isPresent()) {
                    results.add(Validation.success(criteria));
                } else {
                    results.add(Validation.fail("Failure to validate the validation rule for Contact."));
                }
            } else {
                results.add(Validation.fail("validate::validateUnsupported Validation for class type:"+criteria.getClass()));
            }
        }
        return null;
    }
}
