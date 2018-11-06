--- Sentencias SQL para la creaci�n del esquema de parranderos

--Uso:
--Copie el contenido de este archivo y p�guelo en una pesta�a SQL de SQLDeveloper
--Ejec�telo como un script con el bot�n correspondiente

--Creaci�n del secuenciador.
CREATE sequence SuperAndes_sequence;
--Creaci�n de la tabla User y especificaci�n de sus restricciones
CREATE TABLE A_USUARIO (id Number, password varchar(20), nombre varchar(40), correo varchar(30), tipo varchar(20),
CONSTRAINT A_USUARIO_PK PRIMARY KEY (id));

ALTER TABLE A_USUARIO
ADD CONSTRAINT A_USUARIO_tipo 
CHECK ()tipo IN ('empresa', 'persona'))
ENABLE;

--Creaci�n de la tabla Empresas
CREATE TABLE A_EMPRESA (NIT Number, idUser Number, dir varchar(30), puntos Number, tipoEmpresa varchar(10),
CONSTRAINT A_EMPRESA_PK PRIMARY KEY (NIT));

ALTER TABLE A_EMPRESA
ADD CONSTRAINT A_EMPRESA_FK_idUser
FOREIGN KEY (idUser)
REFERENCES A_USUARIO
ENABLE;

ALTER TABLE A_EMPRESA
ADD CONSTRAINT A_EMPRESA_tipoEmpresa
CHECK (tipoEmpresa IN ('cliente', 'proveedor'))
ENABLE;

--Creaci�n de la tabla Personas
CREATE TABLE A_PERSONA(cedula Number,idUser Number, puntos Number, idSucursal Number, tipoPersona varchar(10),
CONSTRAINT A_PERSONA_PK PRIMARY KEY (cedula));

ALTER TABLE A_PERSONA
ADD CONSTRAINT A_PERSONA_FK_idUser
FOREIGN KEY (idUser)
REFERENCES A_USUARIO
ENABLE;

ALTER TABLE A_PERSONA
ADD CONSTRAINT A_PERSONA_tipoPersona
CHECK (tipoPersona IN ('cliente', 'empleado_sucursal'))
ENABLE;


--Creaci�n de la tabla Ciudad
CREATE TABLE A_CIUDAD(id Number, nombre varchar(30),
CONSTRAINT A_CIUDAD_PK PRIMARY KEY (id));

--Creaci�n de la tabla Sucursales
CREATE TABLE A_SUCURSAL (id Number, nombre varchar (30), tamanho Number, direccion varchar(30), nivelReorden Number, nivelReabastecimiento Number, idCiudad Number,
CONSTRAINT A_SUCURSAL_PK PRIMARY KEY (id));

ALTER TABLE A_SUCURSAL 
ADD CONSTRAINT A_SUCURSAL_FK_idCiudad 
FOREIGN KEY (idCiudad)
REFERENCES A_CIUDAD
ENABLE;

--Creaci�n de la tabla ProveedoresSucursales
CREATE TABLE A_PROVEEDORSUCURSAL(idSucursal Number, NITProveedor Number,
CONSTRAINT A_PROVEEDORSUCURSAL_PK PRIMARY KEY (idSucursal, NITProveedor));

ALTER TABLE A_PROVEEDORSUCURSAL
ADD CONSTRAINT A_PROVEEDORSUCURSAL_FK_idSucursal
FOREIGN KEY (idSucursal)
REFERENCES A_SUCURSAL
ENABLE;

ALTER TABLE A_PROVEEDORSUCURSAL
ADD CONSTRAINT A_PROVEEDORSUCURSAL_FK_NITProveedor
FOREIGN KEY (NITProveedor)
REFERENCES A_EMPRESA
ENABLE;

--Creaci�n de la tabla A_CATEGORIA

CREATE TABLE A_CATEGORIA(nombre varchar(30), caracteristicas varchar(100), almacenamiento varchar(20), manejo varchar(40),
CONSTRAINT A_CATEGORIA_PK PRIMARY KEY (nombre));

ALTER TABLE A_CATEGORIA
ADD CONSTRAINT A_CATEGORIA_almacenamiento 
CHECK almacenamiento IN ('Nevera', 'Granos', 'Cereales');

--Creaci�n de la tabla A_CONTENEDOR

CREATE TABLE A_CONTENEDOR(id Number, idSucursal Number, tipo varchar(20), capacidad Number, capacidadOcupada Number,
CONSTRAINT A_CONTENEDOR_PK PRIMARY KEY (id));

ALTER TABLE A_CONTENEDOR
ADD CONSTRAINT A_CONTENEDOR_FK_idSucursal
FOREIGN KEY (idSucursal)
REFERENCES A_SUCURSAL
ENABLE;

ALTER TABLE A_CONTENEDOR
ADD CONSTRAINT A_CONTENEDOR_tipo
CHECK (tipo IN ('estante', 'bodega'))
ENABLE;

ALTER TABLE A_CONTENEDOR
ADD CONSTRAINT A_CONTENEDOR_capacidad_vs_capacidadOcupada
CHECK (capacidadOcupada <= capacidad)
ENABLE;

--Creaci�n de la tabla A_CARRITO

CREATE TABLE A_CARRITO(id Number, idUser Number,
CONSTRAINT A_CARRITO_PK PRIMARY KEY (id));

ALTER TABLE A_CARRITO
ADD CONSTRAINT A_CARRITO_FK_NITProveedor
FOREIGN KEY (idUser)
REFERENCES A_USUARIO
ENABLE;


--Creaci�n de la tabla A_PRODUCTOABSTRACTO
CREATE TABLE A_PRODUCTOABSTRACTO(id Number, nombre varchar(30), tipo varchar(20), unidadMedida varchar(2), categoria varchar(30),
CONSTRAINT A_PRODUCTOABSTRACTO_PK PRIMARY KEY (id));

ALTER TABLE A_PRODUCTOABSTRACTO
ADD CONSTRAINT A_PRODUCTOABSTRACTO_FK_categoria
FOREIGN KEY (categoria)
REFERENCES A_CATEGORIA
ENABLE;

--Creaci�n de la tabla OfrecidosSucursales
CREATE TABLE A_OFRECIDOSUCURSAL(idAbstracto Number, idSucursal Number, precio Number,
CONSTRAINT A_OFRECIDOSUCURSAL_PK PRIMARY KEY (idAbstracto, idSucursal));

ALTER TABLE A_OFRECIDOSUCURSAL
ADD CONSTRAINT A_OFRECIDOSUCURSAL_FK_idSucursal
FOREIGN KEY (idSucursal)
REFERENCES A_SUCURSAL
ENABLE;

ALTER TABLE A_OFRECIDOSUCURSAL
ADD CONSTRAINT A_OFRECIDOSUCURSAL_FK_idAbstracto
FOREIGN KEY (idAbstracto)
REFERENCES A_PRODUCTOABSTRACTO
ENABLE;

--Creaci�n de la tabla A_OFRECIDOPROVEEDOR
CREATE TABLE A_OFRECIDOPROVEEDOR(idAbstracto, NITProveedor, precio,
CONSTRAINT A_OFRECIDOPROVEEDOR_PK PRIMARY KEY (idOfrecido, NITProveedor));

ALTER TABLE A_OFRECIDOPROVEEDOR
ADD CONSTRAINT A_OFRECIDOPROVEEDOR_FK_NITProveedor
FOREIGN KEY (NITProveedor)
REFERENCES A_EMPRESA
ENABLE;

ALTER TABLE A_OFRECIDOPROVEEDOR
ADD CONSTRAINT A_OFRECIDOPROVEEDOR_FK_idAbstracto
FOREIGN KEY (idAbstracto)
REFERENCES A_PRODUCTOABSTRACTO
ENABLE;

--Creaci�n de la tabla A_PRODUCTOFISICO
CREATE TABLE A_PRODUCTOFISICO(id Number, idOfrecido Number, cantidadMedida Number, codigoBarras varchar(20), idContenedor Number, idCarrito Number,
CONSTRAINT A_PRODUCTOFISICO_PK PRIMARY KEY (id));

ALTER TABLE A_PRODUCTOFISICO
ADD CONSTRAINT A_PRODUCTOFISICO_FK_idOfrecido
FOREIGN KEY (idOfrecido)
REFERENCES A_OFRECIDOSUCURSAL
ENABLE;

ALTER TABLE A_PRODUCTOFISICO
ADD CONSTRAINT A_PRODUCTOFISICO_FK_idContenedor
FOREIGN KEY (idContenedor)
REFERENCES A_CONTENEDOR
ENABLE;

ALTER TABLE A_PRODUCTOFISICO
ADD CONSTRAINT A_PRODUCTOFISICO_FK_idCarrito
FOREIGN KEY (idCarrito)
REFERENCES A_CARRITO
ENABLE;
--Falta la parte de hexadecimales en el c�digo de barras.



--Creaci�n de tabla Pedido
CREATE TABLE A_PEDIDO  (id Number, idSucursal Number, NITProveedor Number, precio Number, estado varchar(10), fechaEntrega varchar(10), calidad varchar(15), calificacion Number,
CONSTRAINT A_PEDIDO_PK PRIMARY KEY (id));

ALTER TABLE A_PEDIDO
ADD CONSTRAINT A_PEDIDO_FK_idSucursal
FOREIGN KEY (idSucursal)
REFERENCES A_SUCURSAL
ENABLE;

ALTER TABLE A_PEDIDO
ADD CONSTRAINT A_PEDIDO_FK_NITProveedor
FOREIGN KEY (NITProveedor)
REFERENCES A_EMPRESA
ENABLE;

ALTER TABLE A_PEDIDO
ADD CONSTRAINT A_PEDIDO_estado
CHECK (estado IN('entregado', 'por_entregar'))
ENABLE;

ALTER TABLE A_PEDIDO
ADD CONSTRAINT A_PEDIDO_calidad
CHECK (calidad IN ('muy_mala', 'mala', 'regular', 'buena', 'muy_buena'))
ENABLE;

ALTER TABLE A_PEDIDO
ADD CONSTRAINT A_PEDIDO_calificacion
CHECK (calificacion IN (1,2,3,4,5))
ENABLE;

--Creaci�n de tabla A_PRODUCTOPEDIDO
CREATE TABLE A_PRODUCTOPEDIDO(idPedido Number, idProductoOfrecido Number, cantidad Number,
CONSTRAINT A_PRODUCTOPEDIDO_PK PRIMARY KEY (idPedido, idProductoOfrecido));

ALTER TABLE A_PRODUCTOPEDIDO
ADD CONSTRAINT A_PRODUCTOPEDIDO_FK_idPedido
FOREIGN KEY (idPedido)
REFERENCES A_PEDIDO
ENABLE;

ALTER TABLE A_PRODUCTOPEDIDO
ADD CONSTRAINT A_PRODUCTOPEDIDO_FK_idProductoOfrecido
FOREIGN KEY (idProductoOfrecido)
REFERENCES A_OFRECIDOPROVEEDOR
ENABLE;

--Creaci�n de tabla Promociones
CREATE TABLE A_PROMOCION(id Number, idSucursal Number, descripcion varchar(130), tipo varchar(20),
CONSTRAINT A_PROMOCION_PK PRIMARY KEY (id));

ALTER TABLE A_PROMOCION
ADD CONSTRAINT A_PROMOCION_FK_idSucursal
FOREIGN KEY (idSucursal)
REFERENCES A_SUCURSAL
ENABLE;

ALTER TABLE A_PROMOCION
ADD CONSTRAINT A_PROMOCION_tipo
CHECK (tipo IN ('pague_n_lleve_m', 'pague_x_lleve_y', 'porcentaje_descuento', 'paquete_productos'))
ENABLE;

--Creaci�n de tabla PromocionesPorCantidadOUnidad
CREATE TABLE A_PROMOCIONPORCANTIDADOUNIDAD(idProductoOfrecido Number, idPromocion Number, cantidadOUnidadesPagadas Number, cantidadOUnidadesCompradas Number,
CONSTRAINT A_PROMOCIONPORCANTIDADOUNIDAD_PK PRIMARY KEY (idProductoOfrecido));--Revisar <-- <-- <-- <--

ALTER TABLE A_PROMOCIONPORCANTIDADOUNIDAD
ADD CONSTRAINT A_PROMOCIONPORCANTIDADOUNIDAD_FK_idProductoOfrecido
FOREIGN KEY (idProductoOfrecido)
REFERENCES A_OFRECIDOSUCURSAL
ENABLE;

ALTER TABLE A_PROMOCIONPORCANTIDADOUNIDAD
ADD CONSTRAINT A_PROMOCIONPORCANTIDADOUNIDAD_FK_idPromocion
FOREIGN KEY (idPromocion)
REFERENCES A_PROMOCION
ENABLE;

--Creaci�n de la tabla PromocionesPorcentajeDescuento
CREATE TABLE A_PROMOCIONPORCENTAJEDESCUENTO(idProductoOfrecido Number, idPromocion Number, porcentajeDescuento Number,
CONSTRAINT A_PROMOCIONPORCENTAJEDESCUENTO_PK PRIMARY KEY (idProductoOfrecido Number));

ALTER TABLE A_PROMOCIONPORCENTAJEDESCUENTO
ADD CONSTRAINT A_PROMOCIONPORCENTAJEDESCUENTO_FK_idProductoOfrecido
FOREIGN KEY (idProductoOfrecido)
REFERENCES A_OFRECIDOSUCURSAL
ENABLE;

ALTER TABLE A_PROMOCIONPORCENTAJEDESCUENTO
ADD CONSTRAINT A_PROMOCIONPORCENTAJEDESCUENTO_FK_idPromocion
FOREIGN KEY (idPromocion)
REFERENCES A_PROMOCION
ENABLE;

--Creaci�n de la tabla PromocionesPaqueteProductos
CREATE TABLE A_PROMOCIONPAQUETEPRODUCTOS(idPromocion Number, precio Number, idProductoOfrecido1 Number, idProductoOfrecido2 Number,
CONSTRAINT A_PROMOCIONPAQUETEPRODUCTOS_PK PRIMARY KEY(idProductoOfrecido1, idProductoOfrecido2));

ALTER TABLE A_PROMOCIONPAQUETEPRODUCTOS
ADD CONSTRAINT A_PROMOCIONPAQUETEPRODUCTOS_FK_idPromocion
FOREIGN KEY (idPromocion)
REFERENCES A_PROMOCION
ENABLE;

ALTER TABLE A_PROMOCIONPAQUETEPRODUCTOS
ADD CONSTRAINT A_PROMOCIONPAQUETEPRODUCTOS_FK_idProductoOfrecido1
FOREIGN KEY (idProductoOfrecido1)
REFERENCES A_OFRECIDOSUCURSAL
ENABLE;

ALTER TABLE A_PROMOCIONPAQUETEPRODUCTOS
ADD CONSTRAINT A_PROMOCIONPAQUETEPRODUCTOS_FK_idProductoOfrecido2
FOREIGN KEY (idProductoOfrecido2)
REFERENCES A_OFRECIDOSUCURSAL
ENABLE;

--Creaci�n de tabla Facturas
CREATE TABLE A_FACTURA(id Number, idUser Number, costoTotal Number,
CONSTRAINT A_FACTURA_PK PRIMARY KEY (id));

ALTER TABLE A_FACTURA
ADD CONSTRAINT A_FACTURA_FK_idUser
FOREIGN KEY (idUser)
REFERENCES A_USUARIO
ENABLE;

--Creaci�n de tabla ItemsFacturas
CREATE TABLE A_ITEMFACTURA(idFactura Number, idProductoOfrecido Number, cantidad Number, costo Number,
CONSTRAINT A_ITEMFACTURA_PK PRIMARY KEY (idFactura, idProductoOfrecido));

ALTER TABLE A_ITEMFACTURA
ADD CONSTRAINT A_ITEMFACTURA_FK_idFactura
FOREIGN KEY (idFactura)
REFERENCES A_FACTURA
ENABLE;

ALTER TABLE A_ITEMFACTURA
ADD CONSTRAINT A_ITEMFACTURA_FK_idProductoOfrecido
FOREIGN KEY (idProductoOfrecido)
REFERENCES A_OFRECIDOSUCURSAL
ENABLE;

COMMIT;