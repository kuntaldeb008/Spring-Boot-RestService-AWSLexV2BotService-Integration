/* IBM Confidential
 * OCO Source Materials
 * © Copyright IBM Corporation 2018
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
*/


package com.example.demo.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PojoToJsonConverter {

    protected PojoToJsonConverter() {
        throw new UnsupportedOperationException();
    }

    public static String voltaToJSON(Object obj, Type type) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(obj, type);
        return json;
    }

    public static String toJSON(Object obj) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }

    public static String toJSONwDateFmt(Object obj, String dateFmt) throws Exception {
        Gson gson = new GsonBuilder()
                .setDateFormat(dateFmt)
                .create();
        String json = gson.toJson(obj);
        return json;
    }

    public static <T extends Object> T stringToClass(String json, Class<T> classType) throws Exception {
        Gson gson = new Gson();
        return gson.fromJson(json, classType);
    }

    public static <T extends Object> T stringToClasswDateFmt(String json, Class<T> classType, String dateFmt) throws Exception {
        Gson gson = new GsonBuilder()
                .setDateFormat(dateFmt)
                .create();
        return gson.fromJson(json, classType);
    }

    public static <T extends Object> List<T> stringToList(String json, Class<T[]> classType) throws Exception {
        Gson gson = new Gson();
        return new ArrayList(Arrays.asList(gson.fromJson(json, classType)));
    }

 /*   public static <T extends Object> List<T> arrayToList(String[] json, Class<T[]> classType) throws Exception {
        Gson gson = new Gson();
        return new ArrayList(Arrays.asList(gson.fromJson("[" + StringUtils.join(json, ",") + "]", classType)));
    }

    public static <T extends Object> List<T> arrayToListwDateFmt(String[] json, Class<T[]> classType, String dateFmt) throws Exception {
        Gson gson = new GsonBuilder()
                .setDateFormat(dateFmt)
                .create();
        return new ArrayList(Arrays.asList(gson.fromJson("[" + StringUtils.join(json, ",") + "]", classType)));
    }

    public static <T extends Object> T resourceToClass(String resource, Class<T> classType) throws IOException, JsonSyntaxException {
        String json = loadResource("json/" + resource + ".json");
        Gson gson = new Gson();
        return gson.fromJson(json, classType);
    }

    public static <T extends Object> T resourceToClasswDateFmt(String resource, Class<T> classType, String dateFmt) throws IOException, JsonSyntaxException {
        String json = loadResource("json/" + resource + ".json");
        Gson gson = new GsonBuilder()
                .setDateFormat(dateFmt)
                .create();
        return gson.fromJson(json, classType);
    }

    public static <T extends Object> List<T> resourceToList(String resource, Class<T[]> classType) throws IOException, JsonSyntaxException {
        String json = loadResource("json/" + resource + ".json");
        Gson gson = new Gson();
        return new ArrayList(Arrays.asList(gson.fromJson(json, classType)));
    }

    public static String resourceToString(String resource) throws IOException, JsonSyntaxException {
        return loadResource("json/" + resource + ".json");
    }

    public static String loadResource(String resource) throws IOException {
        final int blng = 4096;
        StringBuilder json = new StringBuilder();
        try (InputStream iStream = new ClassPathResource(resource).getInputStream();
                Reader reader = new InputStreamReader(iStream);) {
            char[] buffer = new char[blng];
            int cnt = reader.read(buffer, 0, blng);
            while (cnt >= 0) {
                json.append(buffer, 0, cnt);
                cnt = reader.read(buffer, 0, blng);
            }
        }
        return json.toString();
    } */
}

