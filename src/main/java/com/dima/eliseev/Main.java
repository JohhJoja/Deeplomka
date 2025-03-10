package com.dima.eliseev;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Генератор случайных данных
        Random random = new Random();

        // Возможные значения для каждого поля
        String[] statuses = {"открыт", "закрыт", "ожидает"};
        String[] urgencyLevels = {"1", "2", "3", "4", "5"};
        String[] groups = {"Information security", "service desk", "адм. ПТК ЦБ", "ИС", "ЛВС", "ПС"};

        // Списки имен для случайной генерации ФИО
        String[] firstNames = {"Иван", "Петр", "Алексей", "Сергей", "Дмитрий", "Максим", "Антон", "Никита", "Егор", "Роман"};
        String[] lastNames = {"Иванов", "Петров", "Смирнов", "Кузнецов", "Попов", "Соколов", "Михайлов", "Федоров", "Новиков", "Романов"};
        String[] patronymics = {"Иванович", "Петрович", "Александрович", "Дмитриевич", "Максимович", "Антонович", "Никитич", "Егорович", "Романович"};

        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {  // Сгенерируем 100 записей
            String fullName = generateRandomName(firstNames, lastNames, patronymics);
            String assignedTo = generateRandomName(firstNames, lastNames, patronymics);  // Случайное имя для назначенного
            String status = statuses[random.nextInt(statuses.length)];
            String code = "IM" + String.format("%06d", random.nextInt(1000000));  // Код в формате IM123456
            String timeOpened = generateRandomTime();  // Сгенерированное случайное время
            String urgency = urgencyLevels[random.nextInt(urgencyLevels.length)];
            String group = groups[random.nextInt(groups.length)];

            // Собираем JSON запись
            String record = String.format("{\"Имя контактного лица\":\"%s\", \"Статус\":\"%s\", \"Код\":\"%s\", \"На кого назначено\":\"%s\", \"Время открытия\":\"%s\", \"Срочность\":\"%s\", \"Группа назначенных\":\"%s\"}",
                    fullName, status, code, assignedTo, timeOpened, urgency, group);
            data.add(record);
        }

        // Сохраняем в JSON файл
        try (FileWriter file = new FileWriter("test_data.json")) {
            file.write("[\n");
            for (int i = 0; i < data.size(); i++) {
                file.write(data.get(i));
                if (i != data.size() - 1) {
                    file.write(",\n");
                }
            }
            file.write("\n]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Генерация случайного ФИО
    private static String generateRandomName(String[] firstNames, String[] lastNames, String[] patronymics) {
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String patronymic = patronymics[random.nextInt(patronymics.length)];
        return firstName + " " + lastName + " " + patronymic;
    }

    // Генерация случайного времени открытия (в пределах последнего месяца)
    private static String generateRandomTime() {
        Random random = new Random();
        int day = random.nextInt(28) + 1;  // Генерируем случайный день месяца (с 1 по 28)
        int hour = random.nextInt(24);     // Генерируем случайный час
        int minute = random.nextInt(60);   // Генерируем случайную минуту
        int second = random.nextInt(60);   // Генерируем случайную секунду
        return String.format("2024-03-%02dT%02d:%02d:%02d", day, hour, minute, second);
    }
}
