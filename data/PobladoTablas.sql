--datos iniciales de tabla Ciudad

insert into CIUDAD (id, nombre) values (1, 'Lotheville');
insert into CIUDAD (id, nombre) values (2, 'Quincy');
insert into CIUDAD (id, nombre) values (3, 'Anthes');
insert into CIUDAD (id, nombre) values (4, 'Cali');
insert into CIUDAD (id, nombre) values (5, 'Barnett');
insert into CIUDAD (id, nombre) values (6, 'Mcguire');
insert into CIUDAD (id, nombre) values (7, 'Charing Cross');
insert into CIUDAD (id, nombre) values (8, 'Pierstorff');
insert into CIUDAD (id, nombre) values (9, 'Esker');
insert into CIUDAD (id, nombre) values (10, 'Atwood');
insert into CIUDAD (id, nombre) values (11, 'Colorado');
insert into CIUDAD (id, nombre) values (12, 'North');
insert into CIUDAD (id, nombre) values (13, 'Trailsway');
insert into CIUDAD (id, nombre) values (14, 'Ridgeway');
insert into CIUDAD (id, nombre) values (15, 'Golf Course');
insert into CIUDAD (id, nombre) values (16, 'Ridgeway');
insert into CIUDAD (id, nombre) values (17, 'Red Cloud');
insert into CIUDAD (id, nombre) values (18, 'Sundown');
insert into CIUDAD (id, nombre) values (19, 'Dakota');
insert into CIUDAD (id, nombre) values (20, 'Algoma');
insert into CIUDAD (id, nombre) values (21, 'Graceland');
insert into CIUDAD (id, nombre) values (22, 'Center');
insert into CIUDAD (id, nombre) values (23, 'Banding');
insert into CIUDAD (id, nombre) values (24, 'Bogota');
insert into CIUDAD (id, nombre) values (25, 'Medellin');
insert into CIUDAD (id, nombre) values (26, 'Armistice');
insert into CIUDAD (id, nombre) values (27, 'Rieder');
insert into CIUDAD (id, nombre) values (28, 'Mendota');
insert into CIUDAD (id, nombre) values (29, 'Golf Course');
insert into CIUDAD (id, nombre) values (30, 'Prentice');
insert into CIUDAD (id, nombre) values (31, 'Kipling');
insert into CIUDAD (id, nombre) values (32, 'Farwell');
insert into CIUDAD (id, nombre) values (33, 'Atwood');
insert into CIUDAD (id, nombre) values (34, 'Girardot');
insert into CIUDAD (id, nombre) values (35, 'Flandes');
insert into CIUDAD (id, nombre) values (36, 'Corben');
insert into CIUDAD (id, nombre) values (37, 'Northfield');
insert into CIUDAD (id, nombre) values (38, 'Caliangt');
insert into CIUDAD (id, nombre) values (39, 'Dapin');
insert into CIUDAD (id, nombre) values (40, 'Del Mar');


--Población de tabla Categoria

insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('congelados', );
insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('quesos');
insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('dulces');
insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('aseo');
insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('gaseosas');
insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('postres');
insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('enlatados');
insert into CATEGORIA (nombre, caracteristicas, almacenamiento, manejo) values ('salsas');




insert into Categoria(nombre, caracteristicas, almacenamiento, manejo) values ('no_perecederos', 'No perecen fácilmente', 'Cereales', 'tratar como sea');

insert into Usuario(id, password, nombre, correo, tipo) values (2000,'abcd1234', 'confenalco', 'confe@gmail.com', 'empresa');
insert into empresa(nit, iduser, dir, puntos, tipoEmpresa) values (10000, 2000, 'dir1',-1,'proveedor');

insert into Usuario(id, password, nombre, correo, tipo) values (2000, 'pass1', 'Carlos', 'carlos@a', 'persona');
insert into Persona(cedula, idUser, puntos, idSucursal, tipoPersona) values (1000000, 2000, -1, 24, 'trabajador_sucursal');
insert into Usuario(id, password, nombre, correo, tipo) values (2001, 'pass2', 'Pardo', 'pardo@a', 'persona');
insert into Persona(cedula, idUser, puntos, idSucursal, tipoPersona) values (1000001, 2001, 0, -1, 'cliente');
insert into Usuario(id, password, nombre, correo, tipo) values (2002, 'pass3', 'Fabian', 'fabian@a', 'persona');
insert into Persona(cedula, idUser, puntos, idSucursal, tipoPersona) values (1000002, 2002, 0, -1, 'cliente');

insert into Contenedor(id, idSucursal, tipo, capacidad, caacidadOcupada) values (2002, 24, 'estante', 200, 0, 'Cereales');
insert into Contenedor(id, idSucursal, tipo, capacidad, caacidadOcupada) values (2003, 24, 'estante', 200, 0, 'Cereales');

insert into ProductoAnullbstracto(id, nombre, tipo, unidadmedida, categoria) values ();



