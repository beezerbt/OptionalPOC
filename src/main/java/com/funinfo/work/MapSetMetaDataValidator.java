package com.funinfo.work;

import com.validation.domain.FunctionalUnitInfo;
import com.validation.domain.MapSetMetaData;

import java.util.Optional;

/**
 * Created by kambiz on 07/04/2017.
 */
public class MapSetMetaDataValidator {

    public void setFunctionalUnitInfo(FunctionalUnitInfo functionalUnitInfo) {
        this.functionalUnitInfo = functionalUnitInfo;
    }

    public FunctionalUnitInfo getFunctionalUnitInfo() {
        return functionalUnitInfo;
    }

    private FunctionalUnitInfo functionalUnitInfo;
    private boolean gridValidation;
    private MapSetMetaData unvalidated;
    private FunctionalUnitService functionalUnitService;
    private Errors errors;

    public MapSetMetaDataValidator(boolean gridValidation, MapSetMetaData unvalidated, FunctionalUnitService functionalUnitService) {
        this.gridValidation = gridValidation;
        this.unvalidated = unvalidated;
        this.functionalUnitService = functionalUnitService;
    }

    public void validate() {
        Optional<FunctionalUnitInfo> optionalFunnctionalUnitInfo =
                Optional.ofNullable(unvalidated.getContactUnitKey())
                .map(o -> functionalUnitService.toFunctionalUnitInfo(o));
        if(optionalFunnctionalUnitInfo.isPresent()) {
            setFunctionalUnitInfo(optionalFunnctionalUnitInfo.get());
        }
        //We would do the same for the EmployeeId with the optional first checking existence then doing the validate call
        //Then we would check if they are both null, or both existing, or the rigth exclusive or
    }
}
