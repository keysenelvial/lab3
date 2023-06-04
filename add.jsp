<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавить автомобиль</title>
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
    <h1>Добавить автомобиль</h1>
    <form id="car-form" action="/add" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Модель:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="brand" class="form-label">Марка:</label>
            <input type="text" class="form-control" id="brand" name="brand" required>
        </div>
        <div class="mb-3">
            <label for="color" class="form-label">Цвет:</label>
            <input type="text" class="form-control" id="color" name="color" required>
        </div>
        <div class="mb-3">
            <label for="year" class="form-label">Год выпуска:</label>
            <input type="number" class="form-control" id="year" name="year" required>
        </div>
        <div class="mb-3">
            <label for="mileage" class="form-label">Пробег:</label>
            <input type="number" class="form-control" id="mileage" name="mileage" required>
        </div>
        <div>
            <input type="submit" class="btn btn-primary" value="Добавить">
        </div>
    </form>
</div>

<script>
    let Car = {
        name: "",
        brand: "",
        color: "",
        year: 0,
        mileage: 0
    };

    function getData() {
        Car.name = document.getElementById("name").value;
        Car.brand = document.getElementById("brand").value;
        Car.color = document.getElementById("color").value;
        Car.year = parseInt(document.getElementById("year").value);
        Car.mileage = parseInt(document.getElementById("mileage").value);

        let carJson = JSON.stringify(Car);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "add", true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                // Действия после успешной отправки данных
                console.log("Данные успешно отправлены на сервер");
                // Сбросить значения полей формы
                document.getElementById("name").value = "";
                document.getElementById("brand").value = "";
                document.getElementById("color").value = "";
                document.getElementById("year").value = "";
                document.getElementById("mileage").value = "";
            } else {
                // Обработка ошибок при отправке данных
                console.error("Ошибка при отправке данных на сервер");
            }
        };
        xhr.send(carJson);
    };
</script>

</body>
</html>