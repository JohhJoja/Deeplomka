package com.dima.eliseev;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class DataLoader {

    // Загрузка тикетов из JSON
    public static List<Ticket> loadTickets(String fileName) throws IOException {
        List<Ticket> tickets = new ArrayList<>();

        // Чтение содержимого файла
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        bufferedReader.close();

        // Преобразование в JSONArray
        JSONArray ticketsArray = new JSONArray(sb.toString());

        // Преобразование JSON в объекты Ticket
        for (int i = 0; i < ticketsArray.length(); i++) {
            JSONObject ticketJson = ticketsArray.getJSONObject(i);
            Ticket ticket = new Ticket(
                    ticketJson.getString("name"),
                    ticketJson.getString("status"),
                    ticketJson.getString("code"),
                    ticketJson.getString("assignedTo"),
                    ticketJson.getString("openTime"),
                    ticketJson.getInt("urgency"),
                    ticketJson.getString("group")
            );
            tickets.add(ticket);
        }

        return tickets;
    }

    // Загрузка тренировочных данных из JSON
    public static List<Map<String, String>> loadTrainingData(String fileName) throws IOException {
        List<Map<String, String>> trainingData = new ArrayList<>();

        // Чтение содержимого файла
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        bufferedReader.close();

        // Преобразование в JSONArray
        JSONArray jsonArray = new JSONArray(sb.toString());

        // Преобразование JSON в список словарей с запросами и метками
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String, String> data = new HashMap<>();
            data.put("query", jsonObject.getString("query"));
            data.put("status", jsonObject.getString("status"));
            data.put("assignedTo", jsonObject.getString("assignedTo"));
            trainingData.add(data);
        }

        return trainingData;
    }
}


