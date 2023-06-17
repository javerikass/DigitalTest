# DigitalTest
# **Описание проекта**
Проект представляет собой Spring Boot приложение, которое использует Spring Web, Spring Data, Hibernate, PostgreSQL, Lombok, ModelMapper, JUnit и Mockito.

Приложение содержит две сущности: предмет и персонаж игры. Каждый предмет связан с персонажем через отношение многие-к-одному, и каждый персонаж связан с предметами через отношение один-ко-многим.
В проекте реализованы контроллеры и эндпоинты для управления предметами и персонажами игры.

Контроллеры предоставляют REST API для создания, чтения, обновления и удаления предметов и персонажей.
Для каждого эндпоинта приведен пример запроса в формате cURL.

## **Проект использует следующие зависимости:**

•	**Spring Boot**: фреймворк для создания приложений на Spring

•	**Spring Web**: модуль для разработки web-приложений на Spring

•	**Spring Data**: модуль для работы с базами данных на Spring

•	**Hibernate**: фреймворк для работы с базами данных

•	**PostgreSQL**: СУБД используемая в проекте

•	**Lombok**: библиотека для автоматической генерации методов

•	**ModelMapper**: библиотека для маппинга объектов

•	**JUnit**: библиотека для написания unit-тестов

•	**Mockito**: библиотека для создания mock-объектов

## **Инструкция для сборки и запуска приложения**

Для сборки и запуска приложения необходимо выполнить следующие шаги:

1.	**Запустить Docker контейнер для PostgreSQL с помощью следующей команды:**

      docker run --name postgres -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=test -p 5432:5432 -d postgres

3.	**Клонировать репозиторий с помощью команды:**

      git clone https://github.com/javerikass/DigitalTest.git

5.	**Перейти в папку проекта:**

      cd digitalTest

7.	**Собрать проект с помощью команды:**

      ./mvnw package

9.	**Запустить приложение с помощью команды:**

      java -jar target/digitalTest-0.0.1-SNAPSHOT.jar

11.	**Приложение будет доступно по адресу** http://localhost:8080.

## **Примеры запросов**

### **Получение всех Item**

curl http://localhost:8080/v1/item/

### **Получение Item по имени**
curl http://localhost:8080/v1/item/item_name

### **Создание Item**

curl -X POST -H "Content-Type: application/json" -d '{"name": "item_name", "quantity": 10, "weight": 0.5, "description": "item_description", "price": 10.0, "playCharacterId": 1}' http://localhost:8080/v1/item/create

### **Обновление Item**

curl -X POST -H "Content-Type: application/json" -d '{"id": 1, "name": "new_item_name", "quantity": 5, "weight": 0.3, "description": "new_item_description", "price": 15.0, "playCharacterId": 2}' http://localhost:8080/v1/item/update

### **Удаление Item по id**

curl -X POST http://localhost:8080/v1/item/delete/1

### **Получение всех PlayCharacter**

curl http://localhost:8080/v1/character/

### **Получение PlayCharacter по имени**

curl http://localhost:8080/v1/character/getByName?name=character_name

### **Получение PlayCharacter по id со списком Item**

curl http://localhost:8080/v1/character/getByIdWithItems/1

### **Создание PlayCharacter**

curl -X POST -H "Content-Type: application/json" -d '{"name": "character_name", "level": 1, "health": 100, "strength": 10, "stamina": 5}' http://localhost:8080/v1/character/create

### **Обновление PlayCharacter**

curl -X POST -H "Content-Type: application/json" -d '{"id": 1, "name": "new_character_name", "level": 2, "health": 150, "strength": 15, "stamina": 10}' http://localhost:8080/v1/character/update

### **Удаление PlayCharacter по id**
curl -X POST http://localhost:8080/v1/character/delete/1