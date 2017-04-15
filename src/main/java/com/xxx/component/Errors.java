package com.xxx.component;

import com.xxx.domain.Organization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kambiz on 07/04/2017.
 */
public class Errors {

    private List<Map<Class, String>> errorList = new ArrayList<>();

    public void register(Organization org, String s) {
        HashMap<Class, String> classStringHashMap = new HashMap<>();
        classStringHashMap.put(org.getClass(), s);
        errorList.add(classStringHashMap);
    }

    @Override
    public String toString() {
        return "Errors{" +
                "errorList=" + errorList +
                '}';
    }

    public List<Map<Class, String>> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Map<Class, String>> errorList) {
        this.errorList = errorList;
    }
}
