package org.teamproject.lottocaptainteam.parser;

import java.util.List;
import org.json.JSONArray;

public class JsonParser {

    public static List<Integer> jsonToIntegerList(String json) {
        return jsonToStringList(json).stream()
                .map(Integer::parseInt)
                .toList();
    }

    private static List<String> jsonToStringList(String json) {
        return jsonToList(json).stream()
                .map(Object::toString)
                .toList();
    }

    private static List<Object> jsonToList(String json) {
        JSONArray jsonArray = new JSONArray(json);
        return jsonArray.toList();
    }

}
