DROP DATABASE if exists WeatherConditions; 
CREATE DATABASE WeatherConditions; 
USE WeatherConditions; 

CREATE TABLE users (
    user_id int primary key not null auto_increment,
    username varchar(50), 
    password varchar(50)
);

CREATE TABLE searches (
    search_id int primary key not null auto_increment, 
    user_id int, 
    search_query varchar(50), 
    timestamp varchar(50)
);




