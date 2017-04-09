package com.funinfo.work;

import com.validation.domain.FunctionalUnitInfo;

/**
 * Created by kambiz on 07/04/2017.
 */
public class FunctionalUnitService {

    public FunctionalUnitInfo toFunctionalUnitInfo(String contactUnitKey) {
        return new FunctionalUnitInfo(contactUnitKey);
    }
}
