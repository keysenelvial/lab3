package com.example.lab1oop;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/read")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/read.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Чтение содержимого файла carlist.json
        String filePath = "/Users/keysenelvial/IdeaProjects/lab1oop/src/main/webapp/carlist.json";
        File file = new File(filePath);
        String fileContent = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);

        if (file.exists()) {

            if (!fileContent.trim().isEmpty()) {
                // Отправка содержимого файла в формате JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(fileContent);

                // Десериализация JSON в массив объектов Car
                Gson gson = new Gson();
                Car[] cars = gson.fromJson(fileContent, Car[].class);
                return;
            }
        }

        // Если файл пустой или не существует, отправка пустого массива в формате JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("[]");
    }
}