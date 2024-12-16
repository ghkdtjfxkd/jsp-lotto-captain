package org.teamproject.lottocaptainteam.parser;

import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;

public class ListParser {
    private static final String customSeparator = ",";

    public static List<String> createResultFrom (String input) {
        JSONArray jsonArray = new JSONArray(input);
        List<Object> list = jsonArray.toList();

        return list.stream().map(Object::toString).toList();
    }

    public static List<Integer> createNumbersFrom (String input) {
        return createResultFrom(input).stream()
                .map(Integer::parseInt)
                .toList();
    }
}
