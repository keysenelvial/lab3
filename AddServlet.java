package com.example.lab1oop;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.lab1oop.Car;

@WebServlet(name = "addServlet", value = "/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Перенаправление запроса к файлу add.jsp
        request.getRequestDispatcher("/pages/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Получение данных из запроса
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String color = request.getParameter("color");
        int year = Integer.parseInt(request.getParameter("year"));
        int mileage = Integer.parseInt(request.getParameter("mileage"));

        // Создание нового объекта автомобиля
        Car car = new Car(name, brand, color, year, mileage);

        // Преобразование объекта в JSON
        String json = new Gson().toJson(car);

        // Добавление JSON в файл
        String filePath = "/Users/keysenelvial/IdeaProjects/lab1oop/src/main/webapp/carlist.json";
        File file = new File(filePath);

        if (file.exists()) {
            String fileContent = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);

            if (fileContent.trim().isEmpty()) {
                // Файл пустой, просто записываем первый объект
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("[" + json + "]");
                writer.close();
            } else {
                // Файл не пустой, добавляем объект в конец массива
                fileContent = fileContent.trim();
                if (fileContent.endsWith("]")) {
                    // Удаляем закрывающую квадратную скобку
                    fileContent = fileContent.substring(0, fileContent.length() - 1);
                }

                // Добавляем разделитель, если файл содержит другие объекты
                if (!fileContent.endsWith(",")) {
                    fileContent += ",";
                }

                // Добавляем новый объект в массив
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(fileContent + json + "\n" + "]");
                writer.close();
            }
        } else {
            // Файл не существует, создаем новый с первым объектом
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("[" + json + "]");
            writer.close();
        }

        // Отправка успешного ответа клиенту
        response.setStatus(HttpServletResponse.SC_OK);

        // Перенаправление пользователя на другую страницу
        response.sendRedirect("/pages/success.jsp");
    }
}