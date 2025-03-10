package com.dima.eliseev;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TicketParser {

    public static void main(String[] args) {
        // Путь к файлу JSON
        String jsonFilePath = "test_data.json";  // Укажи путь к твоему JSON файлу

        try {
            // Читаем содержимое JSON файла
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray ticketsArray = new JSONArray(content);

            // Список для хранения тикетов
            List<Ticket> tickets = new ArrayList<>();

            // Обрабатываем каждый тикет
            for (int i = 0; i < ticketsArray.length(); i++) {
                JSONObject ticketJson = ticketsArray.getJSONObject(i);

                // Извлекаем данные для тикета
                Ticket ticket = new Ticket(
                        ticketJson.getString("name"),
                        ticketJson.getString("status"),
                        ticketJson.getString("code"),
                        ticketJson.getString("assignedTo"),
                        ticketJson.getString("openTime"),
                        ticketJson.getInt("urgency"),
                        ticketJson.getString("group")
                );

                // Добавляем тикет в список
                tickets.add(ticket);
            }

            // Выводим список тикетов
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
