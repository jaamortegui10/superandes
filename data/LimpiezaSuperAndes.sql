--- Elimina las tablas de la base de datos.
DROP TABLE "ITEMFACTURA" CASCADE CONSTRAINTS;
DROP TABLE "FACTURA" CASCADE CONSTRAINTS;
DROP TABLE "PRODUCTOPEDIDO" CASCADE CONSTRAINTS;
DROP TABLE "PEDIDO" CASCADE CONSTRAINTS;
DROP TABLE "PROMPORCANTIDADOUNIDAD" CASCADE CONSTRAINTS;
DROP TABLE "PROMPORCENTAJEDESCUENTO" CASCADE CONSTRAINTS;
DROP TABLE "PROMPAQUETEPRODUCTOS" CASCADE CONSTRAINTS;
DROP TABLE "PROMOCION" CASCADE CONSTRAINTS;
DROP TABLE "OFRECIDOPROVEEDOR" CASCADE CONSTRAINTS;
DROP TABLE "PRODUCTOFISICO" CASCADE CONSTRAINTS;
DROP TABLE "OFRECIDOSUCURSAL" CASCADE CONSTRAINTS;
DROP TABLE "PRODUCTOABSTRACTO" CASCADE CONSTRAINTS;
DROP TABLE "CATEGORIA" CASCADE CONSTRAINTS;
DROP TABLE "CONTENEDOR" CASCADE CONSTRAINTS;
DROP TABLE "PROVEEDORSUCURSAL" CASCADE CONSTRAINTS;
DROP TABLE "SUCURSAL" CASCADE CONSTRAINTS;
DROP TABLE "EMPRESA" CASCADE CONSTRAINTS;
DROP TABLE "PERSONA" CASCADE CONSTRAINTS;
DROP TABLE "USUARIO" CASCADE CONSTRAINTS;
DROP TABLE "CIUDAD" CASCADE CONSTRAINTS;
DROP TABLE "CARRITO" CASCADE CONSTRAINTS;


COMMIT;

--Eliminar el contenido de todas las tablas.
delete from ItemFactura;
delete from Factura;
delete from PromPaqueteProductos;
delete from PromPorcentajeDescuento;
delete from PromPorCantidadOUnidad;
delete from Promocion;
delete from ProductoPedido;
delete from Pedido;
delete from ProductoFisico;
delete from Carrito;
delete from Contenedor;
delete from OfrecidoProveedor;
delete from OfrecidoSucursal;
delete from ProductoAbstracto;
delete from Categoria;
delete from ProveedorSucursal;
delete from Sucursal;
delete from Ciudad;
delete from Persona;
delete from Empresa;
delete from Usuario;

commit;