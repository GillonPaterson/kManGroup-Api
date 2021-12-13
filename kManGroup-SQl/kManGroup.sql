create database kManGroup_gillon;

use kManGroup_gillon;

create table jobRoles (
employeeID int auto_increment NOT NULL,
employeeRole varchar(150) NOT NULL,
CONSTRAINT pk_Employee_ID PRIMARY KEY (employeeID));
