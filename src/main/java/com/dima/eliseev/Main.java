package com.dima.eliseev;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Генерация тестовых данных
        List<Ticket> tickets = generateTickets();

        // Создание JSON-массива
        JSONArray ticketsArray = new JSONArray();

        // Перебор и добавление объектов в массив
        for (Ticket ticket : tickets) {
            JSONObject ticketJson = new JSONObject();
            ticketJson.put("name", ticket.getName());
            ticketJson.put("status", ticket.getStatus());
            ticketJson.put("code", ticket.getCode());
            ticketJson.put("assignedTo", ticket.getAssignedTo());
            ticketJson.put("openTime", ticket.getOpenTime());
            ticketJson.put("urgency", ticket.getUrgency());
            ticketJson.put("group", ticket.getGroup());

            ticketsArray.put(ticketJson);
        }

        // Запись в файл
        try (FileWriter file = new FileWriter("tickets.json")) {
            file.write(ticketsArray.toString(4)); // Отступы для читабельности
            System.out.println("JSON файл был создан успешно!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Пример обработки запроса
        String query = "предоставить запись, которая имеет статус открыт, выполнялась вчера Елисеевым Дмитрием Алексеевичем";
        processQuery(query, tickets); // Передаем запрос и список тикетов
    }

    // Метод для генерации случайных данных
    private static List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();
        Random rand = new Random();
        String[] names = {"Иванов Иван Иванович", "Петров Петр Петрович", "Елисеев Дмитрий Алексеевич"};
        String[] statuses = {"открыт", "закрыт", "ожидает"};
        String[] groups = {"Information security", "service desk", "адм. ПТК ЦБ", "ИС", "ЛВС", "ПС"};

        for (int i = 0; i < 100; i++) {
            String name = names[rand.nextInt(names.length)];
            String status = statuses[rand.nextInt(statuses.length)];
            String code = "IM" + (100000 + rand.nextInt(900000)); // Код в формате IM123456
            String assignedTo = names[rand.nextInt(names.length)];
            String openTime = "2024-04-" + (rand.nextInt(30) + 1) + " 12:00:00";
            int urgency = rand.nextInt(5) + 1; // Срочность от 1 до 5
            String group = groups[rand.nextInt(groups.length)];

            tickets.add(new Ticket(name, status, code, assignedTo, openTime, urgency, group));
        }

        return tickets;
    }

    // Метод для обработки запроса
    private static void processQuery(String query, List<Ticket> tickets) {
        // Простой парсер запроса для извлечения информации
        String[] queryParts = query.split(", ");

        String status = "";
        String assignedTo = "";

        // Извлекаем статус из запроса
        for (String part : queryParts) {
            if (part.contains("статус")) {
                status = part.split(" ")[part.split(" ").length - 1]; // Получаем последний элемент после "статус"
            }
            if (part.contains("выполнялась")) {
                assignedTo = extractSurnameFromQuery(part); // Извлекаем фамилию из фразы "выполнялась"
            }
        }

        System.out.println("Параметры запроса:");
        System.out.println("Статус: " + status);
        System.out.println("Исполнитель (фамилия): " + assignedTo);

        // Диагностика: выводим все тикеты, чтобы убедиться, что данные загружены
        System.out.println("Тикеты в системе:");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }

        // Фильтруем тикеты по статусу и выполняющему
        boolean found = false;
        for (Ticket ticket : tickets) {
            // Сравниваем статус с учетом регистра
            boolean statusMatch = ticket.getStatus().equalsIgnoreCase(status);
            // Сравниваем только фамилию, убираем окончания с помощью метода
            boolean assignedMatch = stripSurname(ticket.getAssignedTo()).equalsIgnoreCase(stripSurname(assignedTo));

            System.out.println("Проверка тикета: " + ticket.getName());
            System.out.println("Сравнение статуса: " + ticket.getStatus() + " == " + status + " -> " + statusMatch);
            System.out.println("Сравнение исполнителя: " + ticket.getAssignedTo() + " == " + assignedTo + " -> " + assignedMatch);

            if (statusMatch && assignedMatch) {
                found = true;
                System.out.println("Найден тикет: " + ticket.toString());
            }
        }

        if (!found) {
            System.out.println("Тикеты, удовлетворяющие запросу, не найдены.");
        }
    }

    // Метод для удаления окончаний фамилий
    private static String stripSurname(String name) {
        // Простой способ: оставляем только фамилию
        String[] parts = name.split(" ");
        return parts[0]; // Берем только фамилию, остальное игнорируем
    }

    // Метод для извлечения фамилии из запроса
    private static String extractSurnameFromQuery(String part) {
        // Делаем предположение, что фамилия будет перед "ым"
        String[] words = part.split(" ");
        for (String word : words) {
            // Если слово заканчивается на "ым", считаем его фамилией
            if (word.endsWith("ым")) {
                return word.substring(0, word.length() - 2); // Убираем окончание "ым"
            }
        }
        return ""; // Если не нашли фамилию, возвращаем пустую строку
    }
}
