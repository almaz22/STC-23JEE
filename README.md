# STC-23JEE
Проект домашних заданий по курсу "Разработчик программного обеспечения с применением технологий Java Enterprise Edition (online)"

Проект разделен на пакеты по номерам заданий по примеру part1.lesson01.task01. 
Также, имеется разделение по веткам, в которых разворачивается отдельное решение подзадачи. 

Для запуска контейнера с postgresql требуется выполнить комманду: 
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=qwerty -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres --name pde postgres

Для запуска докер образа с собранным проектом требуется выполнить команду из директории с Dockerfile:
docker build -t tom .
docker run -it --rm -p 8080:8080 --name=myapp tom:latest

Для запуска контейнеров в одной сети в режиме Multi-Container Apps требуется выполнить команду из директории 
с docker-compose.yml:
docker-compose up -d --build

Для запуска конкретного контейнера из Multi-Container выполнить (к примеру tomcat):
docker-compose up -d --build tomcat
