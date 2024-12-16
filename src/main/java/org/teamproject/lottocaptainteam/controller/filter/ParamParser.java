package org.teamproject.lottocaptainteam.controller.filter;

import java.util.Arrays;
import java.util.List;

public class ParamParser {

    public static List<Integer> parsed(String rawParamInput) {
        List<String> splitRawParams = splitParams(rawParamInput);
        return splitRawParams.stream().map(Integer::parseInt).toList();
    }

    private static List<String> splitParams(String rawParamInput) {
        return Arrays.asList(rawParamInput.split(","));
    }
}
