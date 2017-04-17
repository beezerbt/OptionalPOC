package com.xxx.component;

import com.xxx.domain.Contact;
import com.xxx.domain.ContactUnit;
import com.xxx.domain.Organization;
import fj.data.Validation;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This serves up the functions used for the domain
 * to validate itself.
 * Created by kambiz on 06/04/2017.
 */
public class MapSetDomainFunctionFactory {

    //TODO::must turn them into real validation functions
    public static final Function<String, Optional<Organization>> toContactFromCwid = s -> Optional.ofNullable(s).map(Contact::new);
    public static final Function<String, Optional<Organization>> toContactUnitFromKey = s -> Optional.ofNullable(s).map(ContactUnit::new);
    public static final Function<Organization, Validation<String, Organization>> toMandatory = o -> {
        if (o == null) {
            return Validation.fail("Error::Domain item is mandatory.");
        } else {
            if (o instanceof Contact) {
                if (((Contact) o).getCwid() == null || ((Contact) o).getCwid().length() == 0) {
                    return Validation.fail("Error::ContactUnit CWID null or blank!!");
                }
            } //TODO::for ContactUnit else ...
            //TODO::check to see if the internals are there too...i.e. keys and names etc.
            //TODO::We have to do a case statement...which will be brittle...its sucks.
            return Validation.success(o);
        }
    };
    public static final BiFunction<Organization, Organization, Validation<String, Organization>> toMutuallyExclusiveButMandatory = (organization, organization2) -> {
        if (Optional.ofNullable(Optional.ofNullable(organization).orElse(organization2)).isPresent()) {
            return Validation.success(Optional.ofNullable(Optional.ofNullable(organization).orElse(organization2)).get());
        } else {
            return Validation.fail("ERROR::Both values are null. One or the other must be provided. They are mutually exclusive.");
        }
    };


    public static final Function<String, Function<String, Optional<Organization>>> commandToFunctions = command -> {
        if (command.equalsIgnoreCase("toContactFromCwid")) {
            return MapSetDomainFunctionFactory.toContactFromCwid;
        } else {
            return null;
        }
    };
}
