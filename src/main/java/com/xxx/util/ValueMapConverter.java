package com.xxx.util;

import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * Created by kambiz on 20/04/2017.
 */
public class ValueMapConverter {

    public void convertThisToThat(Map<String, Object> input, MultiValueMap<String, String> mvm) {
        input.forEach((s, o) -> {
            if(o instanceof List) {
                mvm.put(s, (List)o);
            } else {
                mvm.add(s, (String)o);
            }
        });
    }
}
