package com.validation;

import com.validation.domain.Contact;
import com.validation.domain.ContactUnit;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by kambiz on 06/04/2017.
 */
public class MapSetDomainFunctionFactory implements Validation {

    private Function<String, Optional<Contact>> toContactFromCwid = s-> Optional.ofNullable(s).map(Contact::new);
    private Function<String, Optional<ContactUnit>> toContactUnitFromKey = s-> Optional.ofNullable(s).map(ContactUnit::new);

    @Override
    public <T> List<Function<String, Optional<T>>> getValidationFunctionsForDomain() {
        return null;
    }
}
