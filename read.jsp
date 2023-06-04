<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список автомобилей</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
    <div class="container">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/add">Добавить</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/read">Читать</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <h1>Список автомобилей</h1>
    <table id="car-table" class="table table-striped">
        <thead>
        <tr>
            <th>Модель</th>
            <th>Марка</th>
            <th>Цвет</th>
            <th>Год выпуска</th>
            <th>Пробег</th>
        </tr>
        </thead>
        <tbody id="table_body">
        <!-- Здесь будут отображаться данные -->
        </tbody>
    </table>
</div>

<script>
    // Функция для получения данных с сервера
    function fetchTableData() {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    let jsonStr = xhr.responseText;
                    let jsonObj = JSON.parse(jsonStr);
                    let table_body = document.getElementById("table_body");
                    table_body.innerHTML = ""; // Очистить содержимое таблицы перед заполнением

                    for (let element of jsonObj) {
                        let row = document.createElement("tr");
                        for (let value of Object.values(element)) {
                            let cell = document.createElement("td");
                            cell.textContent = value;
                            row.appendChild(cell);
                        }
                        table_body.appendChild(row);
                    }
                } else {
                    console.error("Ошибка при получении данных:", xhr.status);
                }
            }
        };

        xhr.open("POST", "/read", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send();
    }

    // Обновление данных таблицы при загрузке страницы и каждые 5 секунд
    document.addEventListener('DOMContentLoaded', function() {
        fetchTableData();
        setInterval(fetchTableData, 5000);
    });
</script>
</body>
</html>