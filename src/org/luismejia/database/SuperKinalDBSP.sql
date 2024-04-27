use SuperKinalDB;

-- //////////////////////////////////////////////////////////////////////Procedimientos para Clientes///////////////////////////////////////////////////////
 
-- Agregar
 
DELIMITER $$
create procedure sp_agregarCliente(nom varchar(30), ape varchar(30), tel varchar (15), dir varchar(200),ni varchar(15))
BEGIN
	insert into Clientes(nombre, apellido, telefono, direccion,nit) values
		(nom, ape, tel,dir,ni);
END$$
DELIMITER ;
 
call sp_agregarCliente('Diego', 'Neburi', '8200-6040', 'GTM','7954264-0');
 
-- Listar
 
DELIMITER $$ 
create procedure sp_listarClientes()
BEGIN
	SELECT * FROM Clientes;
END $$
DELIMITER ;

-- Eliminar
 
DELIMITER $$ 
create procedure sp_eliminarCliente (in cliId int)
BEGIN
		delete from Clientes 
        where clienteId = cliId;
END $$	
DELIMITER ;
call sp_eliminarCliente(4);
 
-- Actualizar
 
DELIMITER $$
create procedure sp_actualizarCliente (clidId int, nom varchar(30), ape varchar (30), tel varchar(15), dir varchar(200), ni varchar(15))
BEGIN 
	update Clientes
		set nombre = nom, apellido = ape, telefono = tel, direccion = dir, nit = ni where clienteId = clidId;
END $$
DELIMITER ;
call sp_actualizarCliente(1,'Andres','Alvarado','2222-3333','Ciudad ferrica','8746521-0');
-- Buscar
 
DELIMITER $$
 
CREATE PROCEDURE sp_BuscarCliente(IN clidId INT)
    BEGIN
    select * from Clientes where Clientes.clienteId = clidId;
    END$$
    CALL sp_buscarCliente(1);

DELIMITER ;
 
CALL sp_BuscarCliente(1);
 
 -- //////////////////////////////////////////////////////////////////////Procedimientos Cargos///////////////////////////////////////////////////////

-- Agregar cargos 

-- Agregar Cargo
DELIMITER $$
CREATE PROCEDURE sp_agregarCargo(nom VARCHAR(30), des VARCHAR(100))
BEGIN
    INSERT INTO Cargo(nombreCargo, descripcionCargo)
    VALUES(nom, des);
END$$
DELIMITER ;

-- Listar Cargos
DELIMITER $$
CREATE PROCEDURE sp_listarCargos()
BEGIN
    SELECT * FROM Cargo;
END$$
DELIMITER ;

-- Eliminar Cargo
DELIMITER $$
CREATE PROCEDURE sp_eliminarCargo(cargoId INT)
BEGIN
    DELETE FROM Cargo WHERE cargoId = cargoId;
END$$
DELIMITER ;

-- editar Cargo
DELIMITER $$
CREATE PROCEDURE sp_editarrCargo(cargoId INT, nom VARCHAR(30), des VARCHAR(100))
BEGIN
    UPDATE Cargo
    SET nombreCargo = nom, descripcionCargo = des
    WHERE cargoId = cargoId;
END$$
DELIMITER ;

-- Buscar Cargo
DELIMITER $$
CREATE PROCEDURE sp_buscarCargoPorID(cargoId INT)
BEGIN
    SELECT * FROM Cargo WHERE cargoId = cargoId;
END$$
DELIMITER ;


 -- //////////////////////////////////////////////////////////////////////Procedimientos para Compras///////////////////////////////////////////////////////

-- Agregar Compra
DELIMITER $$
CREATE PROCEDURE sp_agregarCompra(fec DATE, tot DECIMAL(10,2))
BEGIN
    INSERT INTO Compras(fechaCompra, totalCompra)
    VALUES(fec, tot);
END$$
DELIMITER ;

-- Listar Compras
DELIMITER $$
CREATE PROCEDURE sp_listarCompras()
BEGIN
    SELECT * FROM Compras;
END$$
DELIMITER ;

-- Eliminar Compra
DELIMITER $$
CREATE PROCEDURE sp_eliminarCompra(compraId INT)
BEGIN
    DELETE FROM Compras WHERE compraId = compraId;
END$$
DELIMITER ;

-- Editar Compra
DELIMITER $$
CREATE PROCEDURE sp_actualizarCompra(compraId INT, fec DATE, tot DECIMAL(10,2))
BEGIN
    UPDATE Compras
    SET fechaCompra = fec, totalCompra = tot
    WHERE compraId = compraId;
END$$
DELIMITER ;

-- Buscar Compra
DELIMITER $$
CREATE PROCEDURE sp_buscarCompraPorID(compraId INT)
BEGIN
    SELECT * FROM Compras WHERE compraId = compraId;
END$$
DELIMITER ;

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Categoria Productos///////////////////////////////////////////////////////
 
-- Procedimiento Almacenado para Agregar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_agregarCategoriaProducto(nom VARCHAR(30), descCateg VARCHAR(100))
BEGIN
    INSERT INTO CategoriaProductos(nombreCategoria, descripcionCategoria)
    VALUES(nom, descCateg);
END$$
DELIMITER ;

-- Procedimiento Almacenado para Listar Categorías de Producto
DELIMITER $$
CREATE PROCEDURE sp_listarCategoriasProductos()
BEGIN
    SELECT * FROM CategoriaProductos;
END$$
DELIMITER ;

-- Procedimiento Almacenado para Eliminar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_eliminarCategoriaProducto(categoriaProductosId INT)
BEGIN
    DELETE FROM CategoriaProductos WHERE categoriaProductosId = categoriaProductosId;
END$$
DELIMITER ;

-- Procedimiento Almacenado para Actualizar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_actualizarCategoriaProducto(categoriaProductosId INT, nom VARCHAR(30), descCateg VARCHAR(100))
BEGIN
    UPDATE CategoriaProductos
    SET nombreCategoria = nom, descripcionCategoria = descCateg
    WHERE categoriaProductosId = categoriaProductosId;
END$$
DELIMITER ;

-- Procedimiento Almacenado para Buscar Categoría de Producto por ID
DELIMITER $$
CREATE PROCEDURE sp_buscarCategoriaProductoPorID(categoriaProductosId INT)
BEGIN
    SELECT * FROM CategoriaProductos WHERE categoriaProductosId = categoriaProductosId;
END$$
DELIMITER ;

-- Procedimiento Almacenado para Buscar Categoría de Producto por Nombre
DELIMITER $$
CREATE PROCEDURE sp_buscarCategoriaProductoPorNombre(nom VARCHAR(30))
BEGIN
    SELECT * FROM CategoriaProductos WHERE nombreCategoria = nom;
END$$
DELIMITER ;


 -- //////////////////////////////////////////////////////////////////////Procedimientos para Distribuidores///////////////////////////////////////////////////////


-- Agregar Distribuidor
DELIMITER $$
CREATE PROCEDURE sp_agregarDistribuidor(nomDistribuidor VARCHAR(30), dirDistribuidor VARCHAR(200), nitDistribuidor VARCHAR(15), telDistribuidor VARCHAR(15), webDistribuidor VARCHAR(50))
BEGIN
    INSERT INTO Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web)
    VALUES(nomDistribuidor, dirDistribuidor, nitDistribuidor, telDistribuidor, webDistribuidor);
END$$
DELIMITER ;

-- Listar Distribuidores
DELIMITER $$
CREATE PROCEDURE sp_listarDistribuidores()
BEGIN
    SELECT * FROM Distribuidores;
END$$
DELIMITER ;

-- Eliminar Distribuidor
DELIMITER $$
CREATE PROCEDURE sp_eliminarDistribuidor(distribuidorId INT)
BEGIN
    DELETE FROM Distribuidores WHERE distribuidorId = distribuidorId;
END$$
DELIMITER ;

-- Editar Distribuidor
DELIMITER $$
CREATE PROCEDURE sp_editarDistribuidor(distribuidorId INT, nomDistribuidor VARCHAR(30), dirDistribuidor VARCHAR(200), nitDistribuidor VARCHAR(15), telDistribuidor VARCHAR(15), webDistribuidor VARCHAR(50))
BEGIN
    UPDATE Distribuidores
    SET nombreDistribuidor = nomDistribuidor, direccionDistribuidor = dirDistribuidor, nitDistribuidor = nitDistribuidor, telefonoDistribuidor = telDistribuidor, web = webDistribuidor
    WHERE distribuidorId = distribuidorId;
END$$
DELIMITER ;

-- Buscar Distribuidor 
DELIMITER $$
CREATE PROCEDURE sp_buscarDistribuidorPorID(distribuidorId INT)
BEGIN
    SELECT * FROM Distribuidores WHERE distribuidorId = distribuidorId;
END$$
DELIMITER ;


 -- //////////////////////////////////////////////////////////////////////Procedimientos para Productos///////////////////////////////////////////////////////

--  Agregar Producto
DELIMITER $$
CREATE PROCEDURE sp_agregarProducto(nom VARCHAR(50), descProd VARCHAR(100), cant INT, precioUV DECIMAL(10,2), precioMV DECIMAL(10,2), precioC DECIMAL(10,2), img BLOB, distribuidorId INT, categoriaProductosId INT)
BEGIN
    INSERT INTO Productos(nombreProducto, descripcionProducto, cantidadStock, precionVentaUnitario, precioVentaMayor, precioCompra, imagen, distribuidorId, categoriaProductosId)
    VALUES(nom, descProd, cant, precioUV, precioMV, precioC, img, distribuidorId, categoriaProductosId);
END$$
DELIMITER ;

--  Listar Productos
DELIMITER $$
CREATE PROCEDURE sp_listarProductos()
BEGIN
    SELECT * FROM Productos;
END$$
DELIMITER ;

-- Eliminar Producto
DELIMITER $$
CREATE PROCEDURE sp_eliminarProducto(productosId INT)
BEGIN
    DELETE FROM Productos WHERE productosId = productosId;
END$$
DELIMITER ;

-- Editar Producto
DELIMITER $$
CREATE PROCEDURE sp_editarProducto(productosId INT, nom VARCHAR(50), descProd VARCHAR(100), cant INT, precioUV DECIMAL(10,2), precioMV DECIMAL(10,2), precioC DECIMAL(10,2), img BLOB, distribuidorId INT, categoriaProductosId INT)
BEGIN
    UPDATE Productos
    SET nombreProducto = nom, descripcionProducto = descProd, cantidadStock = cant, precionVentaUnitario = precioUV, precioVentaMayor = precioMV, precioCompra = precioC, imagen = img, distribuidorId = distribuidorId, categoriaProductosId = categoriaProductosId
    WHERE productosId = productosId;
END$$
DELIMITER ;

-- Buscar Producto
DELIMITER $$
CREATE PROCEDURE sp_buscarProductoPorID(productosId INT)
BEGIN
    SELECT * FROM Productos WHERE productosId = productosId;
END$$
DELIMITER ;

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Promociones///////////////////////////////////////////////////////

-- Agregar Promoción
DELIMITER $$
CREATE PROCEDURE sp_agregarPromocion(precio DECIMAL(10,2), descPromo VARCHAR(100), fechaIni DATE, fechaFin DATE, productoId INT)
BEGIN
    INSERT INTO Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productosId)
    VALUES(precio, descPromo, fechaIni, fechaFin, productoId);
END$$
DELIMITER ;

-- Listar Promociones
DELIMITER $$
CREATE PROCEDURE sp_listarPromociones()
BEGIN
    SELECT * FROM Promociones;
END$$
DELIMITER ;

-- Eliminar Promoción
DELIMITER $$
CREATE PROCEDURE sp_eliminarPromocion(promocionesId INT)
BEGIN
    DELETE FROM Promociones WHERE promocionesId = promocionesId;
END$$
DELIMITER ;

-- Editar Promoción
DELIMITER $$
CREATE PROCEDURE sp_actualizarPromocion(promocionesId INT, precio DECIMAL(10,2), descPromo VARCHAR(100), fechaIni DATE, fechaFin DATE, productoId INT)
BEGIN
    UPDATE Promociones
    SET precioPromocion = precio, descripcionPromocion = descPromo, fechaInicio = fechaIni, fechaFinalizacion = fechaFin, productosId = productoId
    WHERE promocionesId = promocionesId;
END$$
DELIMITER ;

-- Buscar Promoción
DELIMITER $$
CREATE PROCEDURE sp_buscarPromocionPorID(promocionesId INT)
BEGIN
    SELECT * FROM Promociones WHERE promocionesId = promocionesId;
END$$
DELIMITER ;

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Empleados///////////////////////////////////////////////////////

-- Agregar Empleado
DELIMITER $$
CREATE PROCEDURE sp_agregarEmpleado(nom VARCHAR(30), ape VARCHAR(30), suel DECIMAL(10,2), hEntr TIME, hSal TIME, carId INT, encId INT)
BEGIN
    INSERT INTO Empleados(nombreEmpleado, apellidoEmpleado, sueldo, horaDeEntrada, horaDeSalida, cargoId, encargadoId)
    VALUES(nom, ape, suel, hEntr, hSal, carId, encId);
END$$
DELIMITER ;

-- Listar Empleados
DELIMITER $$
CREATE PROCEDURE sp_listarEmpleados()
BEGIN
    SELECT * FROM Empleados;
END$$
DELIMITER ;

-- Eliminar Empleado
DELIMITER $$
CREATE PROCEDURE sp_eliminarEmpleado(empleadoId INT)
BEGIN
    DELETE FROM Empleados WHERE empleadoId = empleadoId;
END$$
DELIMITER ;

--  Editar Empleado
DELIMITER $$
CREATE PROCEDURE sp_editarEmpleado(empleadoId INT, nom VARCHAR(30), ape VARCHAR(30), suel DECIMAL(10,2), hEntr TIME, hSal TIME, carId INT, encId INT)
BEGIN
    UPDATE Empleados
    SET nombreEmpleado = nom, apellidoEmpleado = ape, sueldo = suel, horaDeEntrada = hEntr, horaDeSalida = hSal, cargoId = carId, encargadoId = encId
    WHERE empleadoId = empleadoId;
END$$
DELIMITER ;

-- Buscar Empleado
DELIMITER $$
CREATE PROCEDURE sp_buscarEmpleadoPorID(empleadoId INT)
BEGIN
    SELECT * FROM Empleados WHERE empleadoId = empleadoId;
END$$
DELIMITER ;

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Facturas///////////////////////////////////////////////////////

-- Agregar Factura
DELIMITER $$
CREATE PROCEDURE sp_agregarFactura(fec DATE, hor TIME, cliId INT, empId INT, tot DECIMAL(10,2))
BEGIN
    INSERT INTO Facturas(fecha, hora, clienteId, empleadoId, total)
    VALUES(fec, hor, cliId, empId, tot);
END$$
DELIMITER ;

-- Listar Facturas
DELIMITER $$
CREATE PROCEDURE sp_listarFacturas()
BEGIN
    SELECT * FROM Facturas;
END$$
DELIMITER ;

-- Eliminar Factura
DELIMITER $$
CREATE PROCEDURE sp_eliminarFactura(facturaId INT)
BEGIN
    DELETE FROM Facturas WHERE facturaId = facturaId;
END$$
DELIMITER ;

-- Editar Factura
DELIMITER $$
CREATE PROCEDURE sp_editarFactura(facturaId INT, fec DATE, hor TIME, cliId INT, empId INT, tot DECIMAL(10,2))
BEGIN
    UPDATE Facturas
    SET fecha = fec, hora = hor, clienteId = cliId, empleadoId = empId, total = tot
    WHERE facturaId = facturaId;
END$$
DELIMITER ;

-- Buscar Factura
DELIMITER $$
CREATE PROCEDURE sp_buscarFacturaPorID(facturaId INT)
BEGIN
    SELECT * FROM Facturas WHERE facturaId = facturaId;
END$$
DELIMITER ;

 -- //////////////////////////////////////////////////////////////////////Procedimientos para TicketSoporte///////////////////////////////////////////////////////

-- Agregar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_agregarTicketSoporte(descT VARCHAR(250), est VARCHAR(30), cliId INT, factId INT)
BEGIN
    INSERT INTO TicketSoporte(descripcionTicket, estatus, clienteId, facturaId)
    VALUES(descT, est, cliId, factId);
END$$
DELIMITER ;

-- Listar Tickets de Soporte
DELIMITER $$
CREATE PROCEDURE sp_listarTicketsSoporte()
BEGIN
    SELECT * FROM TicketSoporte;
END$$
DELIMITER ;

-- Eliminar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_eliminarTicketSoporte(ticketSoporteId INT)
BEGIN
    DELETE FROM TicketSoporte WHERE ticketSoporteId = ticketSoporteId;
END$$
DELIMITER ;

-- Editar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_actualizarTicketSoporte(ticketSoporteId INT, descT VARCHAR(250), est VARCHAR(30), cliId INT, factId INT)
BEGIN
    UPDATE TicketSoporte
    SET descripcionTicket = descT, estatus = est, clienteId = cliId, facturaId = factId
    WHERE ticketSoporteId = ticketSoporteId;
END$$
DELIMITER ;

-- Buscar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_buscarTicketSoportePorID(ticketSoporteId INT)
BEGIN
    SELECT * FROM TicketSoporte WHERE ticketSoporteId = ticketSoporteId;
END$$
DELIMITER ;

 -- //////////////////////////////////////////////////////////////////////Procedimientos para DetalleFactura///////////////////////////////////////////////////////

-- Agregar Detalle de Factura
DELIMITER $$
CREATE PROCEDURE sp_agregarDetalleFactura(factId INT, prodId INT)
BEGIN
    INSERT INTO DetalleFactura(facturaId, productoId)
    VALUES(factId, prodId);
END$$
DELIMITER ;

-- Listar Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_listarDetallesFactura()
BEGIN
    SELECT * FROM DetalleFactura;
END$$
DELIMITER ;

-- Eliminar Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_eliminarDetalleFactura(detalleFacturaId INT)
BEGIN
    DELETE FROM DetalleFactura WHERE detalleFacturaId = detalleFacturaId;
END$$
DELIMITER ;

-- Editar Detalle de Factura
DELIMITER $$
CREATE PROCEDURE sp_editarDetalleFactura(detalleFacturaId INT, factId INT, prodId INT)
BEGIN
    UPDATE DetalleFactura
    SET facturaId = factId, productoId = prodId
    WHERE detalleFacturaId = detalleFacturaId;
END$$
DELIMITER ;

-- Procedimiento Almacenado para Buscar Detalle de Factura por ID
DELIMITER $$
CREATE PROCEDURE sp_buscarDetalleFacturaPorID(detalleFacturaId INT)
BEGIN
    SELECT * FROM DetalleFactura WHERE detalleFacturaId = detalleFacturaId;
END$$
DELIMITER ;

-- Procedimiento Almacenado para Buscar Detalle de Factura por Factura
DELIMITER $$
CREATE PROCEDURE sp_buscarDetallesFacturaPorFactura(factId INT)
BEGIN
    SELECT * FROM DetalleFactura WHERE facturaId = factId;
END$$
DELIMITER ;
