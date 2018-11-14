-- Add Relations:
--AddR1
Create Table CodigoBarra (idFisico Number, codigoBarras varchar(20),
Constraint codigoBarra_PK Primary Kery (idFisico) );

--AddR2
Create Table Ofrecido (id Number, precio Number, tipoOfrecido varchar(8), 

idAbstracto Number,
Constraint id_PK Primary Key (id)  );

Alter Table Ofrecido 
Add Constraint tO_Check
Check (tipoOfrecido in ('sucursal', 'proveedor'))
Enable;

Alter Table Ofrecido
Add Constraint O_FK_iAbs
Foreign Key (idAbstracto)
references ProductoAbstracto
Enable;

--AddR3
Create Table Cliente(documento Number, nombre varchar(40), password 

varchar(25), dir varchar(30), tipo varchar(7), puntos Number,
Constraint Cliente_PK Primary Key (documento) );

Alter Table Cliente
Add Constraint cliente_ck_tipo
Check (tipo in ('persona', 'empresa'))
Enable;

Alter Table Cliente
Add Constraint cliente_ck_puntos
Check (puntos >= 0)
Enable;

--AddR4
Create Table Proveedor(nit Number, nombre varchar(40), password varchar

(25), dir varchar(30),
Constraint Proveedor_PK Primary Key (nit) );

--delete Columns
--delete1

Alter Table Promocion drop slogan;

--delete2
Alter Table Factura drop constraint ITEMFACTURA_FK_idFactura;
Alter Table Factura drop idUser;

--<con constraint>

--delete3
Alter table Carrito drop constraint CARRITO_FK_NITProveedor;
Alter Table Carrito drop idUser;
--<con constraint>

--delete4
Alter Table ProductoAbstracto drop tipo;
--<no tenía constraint>

--delete5
Alter Table Pedido drop idProveedor;
Alter Table Pedido drop constraint PEDIDO_FK_NITProveedor;
--<con constraint>

--delete6
Alter Table Categoria drop almacenamiento;



--delete Relations
--deleteR1
drop table Empresa;

--deleteR2
drop table Persona;

--deleteR3
drop table Usuario;

--Foreigns keys:
--Copy1 - Paste1

Alter Table CodigoBarras 
Add Constraint CB_FK_idFisico
Foreign Key (idFisico)
references ProductoFisico
Enable;

--Copy2 - Paste2

Alter Table Pedido 
Add Constraint pedido_FK_nitProv
Foreign Key (nitProveedor)
Enable;

Copy3 - Paste3

Alter Table Factura
Add Constraint fact_FK_idCliente
Foreign Key (idCliente)
references Cliente
Enable;

Alter Table Carrito
Add Constraint car_FK_idCliente
Foreign Key (idCliente)
references Cliente
Enable;

--Copy4 - Paste4

Alter Table Contenedor
Add Constraint Cont_FK_categoria
Foreign Key (categoria)
references Categoria
Enable;

--Cambios Columnas de una tabla a otra
--Change1
Alter Table ProductoFisico drop cantidadMedida;
Alter Table ProductoAbstracto add cantidadMedida Number;

--Change2
Alter Table ProductoFisico drop codigoBarras;
--Se pasó a la creación de la tabla CodigoBarras

--Change3
Alter Table OfrecidoSucursal drop precio;
Alter Table OfrecidoProveedor drop precio;
--Creación columna implementada en creación de Ofrecido

--Change4
Alter Table OfrecidoSucursal drop idAbstracto;
Alter Table OfrecidoProveedor drop idAbstracto;
--Creación columna implementada en creación de Ofrecido
