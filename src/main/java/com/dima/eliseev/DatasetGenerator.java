package com.dima.eliseev;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import java.io.*;

public class DatasetGenerator {

    public static void main(String[] args) {
        // Пример тикетов
        List<Ticket> tickets = Arrays.asList(
                new Ticket("Елисеев Дмитрий Алексеевич", "открыт", "IM123456", "Иванов Иван Иванович", "2024-03-10 14:30", 1, "Information security"),
                new Ticket("Петрова Марина Николаевна", "закрыт", "IM654321", "Смирнов Сергей Александрович", "2024-03-11 09:15", 2, "service desk"),
                new Ticket("Иванов Игорь Викторович", "ожидает", "IM111222", "Алексеев Андрей Иванович", "2024-03-12 16:50", 3, "ЛВС")
        );

        // Генерация тренировочных данных
        List<Map<String, String>> trainingData = new ArrayList<>();

        for (Ticket ticket : tickets) {
            Map<String, String> data = new HashMap<>();

            // Генерация запросов для каждого тикета
            String query = "Предоставить тикет с таким статусом '" + ticket.getStatus() +
                    "' и назначенным '" + ticket.getAssignedTo() + "'.";
            data.put("query", query);
            data.put("status", ticket.getStatus());
            data.put("assignedTo", ticket.getAssignedTo());

            // Добавление к данным
            trainingData.add(data);
        }

        // Запись в JSON файл
        JSONArray jsonArray = new JSONArray();
        for (Map<String, String> data : trainingData) {
            JSONObject jsonObject = new JSONObject(data);
            jsonArray.put(jsonObject);
        }

        try (FileWriter file = new FileWriter("training_data.json")) {
            file.write(jsonArray.toString());
            System.out.println("Данные для обучения сохранены в файл 'training_data.json'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
