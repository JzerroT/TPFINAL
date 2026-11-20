DROP DATABASE IF EXISTS gestion_barrios;
CREATE DATABASE gestion_barrios;
USE gestion_barrios;

CREATE TABLE situacionLaboral (
    descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (descripcion)
);

CREATE TABLE comuna (
    numeroComuna INT          NOT NULL,
    sedeComunal  VARCHAR(100) NOT NULL,
    juntaComunal VARCHAR(100) NOT NULL,
    PRIMARY KEY (numeroComuna)
);

CREATE TABLE barrios (
    nombreBarrio      VARCHAR(100) NOT NULL,
    hectareas         DOUBLE       NOT NULL,
    tipoBarrio        VARCHAR(30)  NOT NULL,
    numeroComuna      INT          NOT NULL,
    cantidadPH        INT          NULL,
    porcentajeAsfalto DOUBLE       NULL,
    numeroLey         INT          NULL,
    aguaFormal        BOOLEAN      NULL,
    monoblocks        INT          NULL,
    consorcioActivo   BOOLEAN      NULL,
    PRIMARY KEY (nombreBarrio),
    FOREIGN KEY (numeroComuna) REFERENCES comuna (numeroComuna)
);

CREATE TABLE persona (
    dni              VARCHAR(10)  NOT NULL,
    nombre           VARCHAR(60)  NOT NULL,
    apellido         VARCHAR(60)  NOT NULL,
    fechaNacimiento  DATE         NOT NULL,
    situacionLaboral VARCHAR(20)  NOT NULL,
    nombreBarrio     VARCHAR(100) NULL,
    PRIMARY KEY (dni),
    FOREIGN KEY (situacionLaboral) REFERENCES situacionLaboral (descripcion),
    FOREIGN KEY (nombreBarrio) REFERENCES barrios (nombreBarrio) ON DELETE SET NULL
);

INSERT INTO situacionLaboral VALUES ('OCUPADO'), ('DESOCUPADO'), ('SUBOCUPADO');

INSERT INTO comuna VALUES
    (1, 'Sede Comunal 1', 'Junta Comunal 1'),
    (7, 'Sede Comunal 7', 'Junta Comunal 7'),
    (8, 'Sede Comunal 8', 'Junta Comunal 8'),
    (14, 'Sede Comunal 14', 'Junta Comunal 14');

INSERT INTO barrios (nombreBarrio, hectareas, tipoBarrio, numeroComuna, cantidadPH, porcentajeAsfalto) VALUES
    ('Palermo', 1580, 'Trazado formal', 14, 5200, 99),
    ('Flores', 780, 'Trazado formal', 7, 2300, 96),
    ('Villa Lugano', 900, 'Trazado formal', 8, 900, 90);

INSERT INTO barrios (nombreBarrio, hectareas, tipoBarrio, numeroComuna, numeroLey, aguaFormal) VALUES
    ('Barrio Padre Mugica', 32, 'Urbanizacion Planificada', 1, 3343, FALSE),
    ('Rodrigo Bueno', 10, 'Urbanizacion Planificada', 1, 5798, TRUE),
    ('Barrio 20', 48, 'Urbanizacion Planificada', 8, 5705, FALSE);

INSERT INTO barrios (nombreBarrio, hectareas, tipoBarrio, numeroComuna, monoblocks, consorcioActivo) VALUES
    ('Complejo Lugano I y II', 70, 'Complejo Habitacional', 8, 47, FALSE),
    ('Complejo Piedrabuena', 20, 'Complejo Habitacional', 8, 20, TRUE);

INSERT INTO persona VALUES
    ('30111222', 'Ana María', 'Gómez', '1983-04-12', 'OCUPADO', 'Palermo'),
    ('32444555', 'Carlos', 'Díaz', '1986-09-30', 'SUBOCUPADO', 'Flores'),
    ('40666777', 'Lucía', 'Fernández', '1997-01-05', 'DESOCUPADO', 'Barrio 20'),
    ('38999000', 'Martín', 'Pereyra', '1994-11-21', 'OCUPADO', 'Complejo Lugano I y II'),
    ('41222333', 'Sofía', 'Ramírez', '1999-06-17', 'OCUPADO', 'Barrio Padre Mugica');
