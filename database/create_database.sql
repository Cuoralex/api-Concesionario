CREATE DATABASE consecionario;
USE consecionario;

CREATE TABLE marca(
	id int auto_increment primary key,
    descripcion varchar(25) not null
);

CREATE TABLE ciudad(
	id int auto_increment primary key,
    nombre varchar(25) not null
);

CREATE TABLE rol(
	id int auto_increment primary key,
    descripcion varchar(25) not null
);

CREATE TABLE color(
	id int auto_increment primary key,
    descripcion varchar(25) not null
);

CREATE TABLE gama(
	id int auto_increment primary key,
    descripcion varchar(25) not null
);

CREATE TABLE carro(
	id int auto_increment primary key,
    id_color int,
    modelo int NOT NULL,
    id_marca int NOT NULL,
    cilindraje double NOT NULL,
    capacidad int NOT NULL,
    precio double NOT NULL,
    id_gama int,
    FOREIGN KEY (id_gama) REFERENCES gama(id),
    FOREIGN KEY (id_marca) REFERENCES marca(id),
	FOREIGN KEY (id_color) REFERENCES color(id)
);

CREATE TABLE tienda(
	id int auto_increment primary key,
    id_ciudad int,
    nombre varchar(50) NOT NULL,
    direccion varchar(150) NOT NULL,
    telefono varchar(25) NOT NULL,
    FOREIGN KEY (id_ciudad) REFERENCES ciudad(id)
);

CREATE TABLE usuario(
	id int auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    correo varchar(50) not null,
    telefono varchar(25) not null,
    direccion varchar(50) not null,
    fecha_nacimiento date,
    id_ciudad int,
    id_rol int,
    FOREIGN KEY (id_ciudad) REFERENCES ciudad(id),
    FOREIGN KEY (id_rol) REFERENCES rol(id)
);

CREATE TABLE factura(
	id int auto_increment primary key,
    id_asesor int,
    id_cliente int,
    id_carro int,
    id_tienda int,
    fecha datetime not null,
    cantidad int not null,
    tipo_pago varchar(10),
    estado_pago varchar(10),
    FOREIGN KEY (id_asesor) REFERENCES usuario(id),
    FOREIGN KEY (id_cliente) REFERENCES usuario(id),
    FOREIGN KEY (id_carro) REFERENCES carro(id),
    FOREIGN KEY (id_tienda) REFERENCES tienda(id)
);

INSERT INTO marca (descripcion)
	VALUES
    ("Chevrolet"),
    ("Mazda"),
    ("Audi"),
    ("BMW"),
    ("Mercedez Benz"),
    ("Toyota"),
    ("Renault"),
    ("Ferrari"),
    ("McLaren"),
    ("Jaguar");

INSERT INTO ciudad (nombre)
	VALUES
    ("Medellín"),
	("Cúcuta"),
    ("Bogotá"),
    ("Cali"),
    ("Barranquilla"),
    ("Cartagena"),
    ("Bucaramanga"),
    ("Tunja"),
    ("Santa Marta");

INSERT INTO rol (descripcion) VALUES ("Asesor"), ("Cliente");

INSERT INTO color (descripcion) VALUES ("Negro"), ("Blanco"), ("Plateado"), ("Rojo"), ("Azul"), ("Gris");

INSERT INTO gama (descripcion) VALUES ("Alta"), ("Media"), ("Comercial");
