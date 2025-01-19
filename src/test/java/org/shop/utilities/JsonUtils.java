package org.shop.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonUtils {

    public static JsonObject jsonObject;

    public static Map<String, String> getTestCaseData(String tcName) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/resources/data.json")));
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Map<String, String>> data = objectMapper.readValue(jsonContent, Map.class);
            if(data.containsKey(tcName))
            {
                return data.get(tcName);
            }
            else
            {
                throw  new IllegalArgumentException("Test Case Name is not Found"+tcName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while reading data.json file",e);
        }
    }

    public static String getJsonData(String value) {
        String[] compTCName = value.split("\\.");
        String tcName = compTCName[0].substring(1);
        String jsonKey = compTCName[1];
        Map<String, String> testCaseData = JsonUtils.getTestCaseData(tcName);
        value = testCaseData.get(jsonKey);
        return value;
    }

    @Step("Read Json File")
    public static JsonObject readJsonFile() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/main/resources/data.json"));
        return gson.fromJson(bufferedReader, JsonObject.class);
    }

    @Step("Return data from Json File")
    public static String getTestData(String tagName, String dataName) throws FileNotFoundException {
        jsonObject = readJsonFile();
        return jsonObject.getAsJsonObject(tagName).get(dataName).getAsString();
    }
}