package com.xxx.component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.xxx.domain.Contact;
import com.xxx.domain.ContactUnit;
import com.xxx.domain.Organization;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by kambiz on 06/04/2017.
 */
public class MapSetDomainFunctionFactory {

    private static final Function<String, Optional<Organization>> toContactFromCwid = s-> Optional.ofNullable(s).map(Contact::new);
    private static final Function<String, Optional<Organization>> toContactUnitFromKey = s-> Optional.ofNullable(s).map(ContactUnit::new);

    public static Multimap<Class, Function<String, Optional<Organization>>> getValidationFunctionInventory() {
        Multimap<Class, Function<String, Optional<Organization>>> validationFunctions = ArrayListMultimap.create();
        validationFunctions.put(Contact.class, toContactFromCwid);
        validationFunctions.put(ContactUnit.class, toContactUnitFromKey);

        return validationFunctions;
    }

}
