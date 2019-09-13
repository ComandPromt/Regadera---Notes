CREATE TABLE `notas`(
	`id` int(11) AUTO_INCREMENT PRIMARY KEY,
	`Nombre` varchar(50) NOT NULL UNIQUE,
	`tipo` varchar(50) NOT NULL,
	`descripcion` varchar(255) NOT NULL
) DEFAULT CHARSET=utf8;