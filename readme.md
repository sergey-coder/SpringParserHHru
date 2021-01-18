Учебный проект. В проекте переписано мое приложение TestAppSearchVacancies на другом стэке технологий

1.Используемые технологии:
- java 1.8;
- spring boot;
- spring MVC;
- spring hibernait;
- maven;
- tomcat 9;
- REST;
- thymeleaf;

2.Реализовано:
- на главное странице пользователь через select вводит названия вакансии и города;
- через GET запрос приложение обращается к API сайта hh.ru и запрашивает список вакансий;
- приложение обрабатывает входящий JSON и сохраняет данные в базу данных;
- слой сервисов для взаимодействие с базой данных:
    - model;
    - repository;
    - интерфейс Service;
    - реализация интерфейса impl;
- обработка запросов- REST
- реализован просмотр вакансий из базы данных
- реализовано удаление отдельных записей и всей базы данных