package com.xxx.service;

import com.xxx.domain.FunctionalUnitInfo;

/**
 * Created by kambiz on 07/04/2017.
 */
public class FunctionalUnitService {

    public FunctionalUnitInfo toFunctionalUnitInfo(String contactUnitKey) {
        return new FunctionalUnitInfo(contactUnitKey);
    }
}
