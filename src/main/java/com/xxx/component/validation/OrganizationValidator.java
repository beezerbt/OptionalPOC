package com.xxx.component.validation;

import com.xxx.component.MapSetDomainFunctionFactory;
import com.xxx.domain.Contact;
import com.xxx.domain.ContactUnit;
import com.xxx.domain.Organization;
import fj.data.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by kambiz on 06/04/2017.
 */
public class OrganizationValidator implements Consumer<String>{

    private List<Validation<String, Organization>> results = new ArrayList<>();
    private Organization itemToValidate;
    private Organization secondItemToValidate;
    private Stream<String> contactUnitValidationRules = Stream.of("toContactUnitFromKey", "toMandatory");
    private Stream<String> contactValidationRules = Stream.of("toContactFromKey", "toMandatory");

    public void validate (Organization itemToValidate, String typeName) {
        this.itemToValidate = itemToValidate;
        if(itemToValidate instanceof Contact ||
                typeName.equalsIgnoreCase("Contact")) {
            contactValidationRules.forEach(this::accept);
        } else if (itemToValidate instanceof ContactUnit ||
                typeName.equalsIgnoreCase("ConatctUnit")) {
            contactUnitValidationRules.forEach(this::accept);
        } else {
            System.out.println("Validation not supported for type::["+Organization.class+"]");
        }
    }

    public void validate (Organization itemToValidate, String typeName, Organization secondItemToValidate, String secondTypeName) {
        this.itemToValidate = itemToValidate;
        this.secondItemToValidate = secondItemToValidate;
        if ((itemToValidate instanceof Contact || typeName.equalsIgnoreCase("Contact")) &&
            (secondItemToValidate instanceof ContactUnit || secondTypeName.equalsIgnoreCase("ContactUnit"))) {
            accept("toMutuallyExclusiveButMandatory");
        }
        else {
            System.out.println("Validation not supported for types::["+typeName+"] and ["+secondTypeName+"]");
        }
    }

    @Override
    public void accept(String s) {
        switch (s) {
            case "toMandatory":{
                results.add(MapSetDomainFunctionFactory.toMandatory.apply(itemToValidate));
                break;
            }
            case "toContactFromKey": {
                Optional<Organization> toContactFromKeyFunction = MapSetDomainFunctionFactory.toContactFromCwid.apply(s);
                results.add(toContactFromKeyFunction.isPresent() ?
                        Validation.success(toContactFromKeyFunction.get()) : Validation.fail("Error: toContactFromKey validation failed."));
                break;
            }
            case "toContactUnitFromKey": {
                Optional<Organization> toContactUnitFromKeyFunction = MapSetDomainFunctionFactory.toContactUnitFromKey.apply(s);
                results.add(toContactUnitFromKeyFunction.isPresent() ?
                        Validation.success(toContactUnitFromKeyFunction.get()) : Validation.fail("Error: toContactUnitFromKey validation failed."));
                break;
            }
            case "toMutuallyExclusiveButMandatory": {
                results.add(MapSetDomainFunctionFactory.toMutuallyExclusiveButMandatory.apply(itemToValidate, secondItemToValidate));
                break;
            }
            default: {
                System.out.println("Urecognized function command::["+s+"]");
                break;
            }
        }
    }

    @Override
    public Consumer<String> andThen(Consumer<? super String> after) {
        return null;
    }

    public List<Validation<String, Organization>> getResults() {
        return results;
    }
}
