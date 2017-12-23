CREATE DATABASE BDSangucheto
USE BDSangucheto
CREATE TABLE USUARIOS (
	dni varchar(9) not null,
    pass varchar(20) not null,
    primary key(dni)
)