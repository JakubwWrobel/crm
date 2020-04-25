CREATE DATABASE crm
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
use crm;

CREATE TABLE employees
(
    id         int PRIMARY KEY AUTO_INCREMENT,
    firstName  VARCHAR(255),
    lastName   VARCHAR(255),
    address    VARCHAR(255),
    telNumber  VARCHAR(255),
    note       text,
    hourlyCost double
);

CREATE TABLE clients
(
    id        int AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName  VARCHAR(255),
    birthDate DATETIME
);

CREATE TABLE cars
(
    idNumber        int PRIMARY KEY,
    model           VARCHAR(255),
    brand           VARCHAR(255),
    dateProduction  DATETIME,
    nextCheckupDate DATETIME
);


CREATE TABLE orders
(
    id                     int PRIMARY KEY AUTO_INCREMENT,
    dateReceived           DATETIME,
    plannedDateStartRepair DATETIME,
    dateStartRepair        DATETIME,
    employeeAssigned       int not null,
    problemDes             text,
    resolutionDes          text,
    status                 VARCHAR(255),
    car                    int,
    costRepair             double,
    costOfItems            double,
    workHours              double,
    costOfHour             double,
    FOREIGN KEY (car) REFERENCES cars (idNumber),
    FOREIGN KEY (employeeAssigned) REFERENCES employees (id),
    FOREIGN KEY (status) REFERENCES statuses (status)
);


CREATE TABLE statuses
(
    id     int not null ,
    status VARCHAR(255) PRIMARY KEY
);
