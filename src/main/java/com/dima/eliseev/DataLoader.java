package com.dima.eliseev;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class DataLoader {

    public static List<Map<String, String>> loadData(String filePath) {
        List<Map<String, String>> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            JSONArray jsonArray = new JSONArray(content.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                Map<String, String> recordData = new HashMap<>();
                recordData.put("name", record.getString("Имя контактного лица"));
                recordData.put("status", record.getString("Статус"));
                recordData.put("code", record.getString("Код"));
                recordData.put("assignedTo", record.getString("На кого назначено"));
                recordData.put("openTime", record.getString("Время открытия"));
                recordData.put("urgency", record.getString("Срочность"));
                recordData.put("group", record.getString("Группа назначенных"));
                records.add(recordData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void main(String[] args) {
        List<Map<String, String>> data = loadData("test_data.json");
        // Выводим все загруженные данные для проверки
        for (Map<String, String> record : data) {
            System.out.println(record);
        }
    }
}

