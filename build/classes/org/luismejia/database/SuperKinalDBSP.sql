use SuperKinalDB;
-- //////////////////////////////////////////////////////////////////////Procedimientos para Clientes///////////////////////////////////////////////////////
 
-- Agregar
 
DELIMITER $$
create procedure sp_agregarClientes(nom varchar(30), ape varchar(30), tel varchar (15), dir varchar(200),ni varchar(15))
BEGIN
	insert into Clientes(nombre, apellido, telefono, direccion,nit) values
		(nom, ape, tel,dir,ni);
END$$
DELIMITER ;
 
call sp_agregarClientes('Diego', 'Neburi', '8200-6040', 'GTM','7954264-0');

 
-- Listar
 
DELIMITER $$ 
create procedure sp_listarClientes()
BEGIN
	SELECT * FROM Clientes;
END $$
DELIMITER ;
call sp_listarClientes();

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


-- //////////////////////////////////////////////////////////////////////Procedimientos para Empleados///////////////////////////////////////////////////////

-- Agregar Empleado
DELIMITER $$
CREATE PROCEDURE sp_agregarEmpleado(nom VARCHAR(30), ape VARCHAR(30), sueldo DECIMAL(10,2), horaDeEntrada TIME, horaDeSalida TIME, cargoId INT, encargadoId INT)
BEGIN
    INSERT INTO Empleados(nombreEmpleado, apellidoEmpleado, sueldo, horaDeEntrada, horaDeSalida, cargoId, encargadoId)
    VALUES (nom, ape, sueldo, horaDeEntrada, horaDeSalida, cargoId, encargadoId);
END$$
DELIMITER ;
CALL sp_agregarEmpleado('Juan', 'Pérez', 2000.00, '08:00:00', '17:00:00', 1, 1 );

-- Listar Empleados
DELIMITER $$
CREATE PROCEDURE sp_listarEmpleados()
BEGIN
    SELECT * FROM Empleados;
END$$
DELIMITER ;
CALL sp_ListarEmpleados();

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
CREATE PROCEDURE sp_buscarEmpleado(empleadoId INT)
BEGIN
    SELECT * FROM Empleados WHERE empleadoId = empleadoId;
END$$
DELIMITER ;

-- ENCARGADO EMPLEADOS
Delimiter $$
create procedure sp_asignarEncargado(In empId Int, In encarId int)
begin

	Update Empleados  
		Set 
			Empleados.encargadoId = encarId
			Where empleadoId = empId;
end$$
Delimiter ;
call sp_asignarEncargado(1,1);
 
 -- //////////////////////////////////////////////////////////////////////Procedimientos Cargos///////////////////////////////////////////////////////
 
-- Agregar Cargo
DELIMITER $$
CREATE PROCEDURE sp_agregarCargo(nom VARCHAR(30), des VARCHAR(100))
BEGIN
    INSERT INTO Cargo(nombreCargo, descripcionCargo)
    VALUES(nom, des);
END$$
DELIMITER ;
CALL sp_agregarCargo('Gerente', 'lider de sede' );

-- Listar Cargos
DELIMITER $$
CREATE PROCEDURE sp_listarCargo()
BEGIN
    SELECT * FROM Cargo;
END$$
DELIMITER ;
call sp_listarCargo;

-- Eliminar Cargo
DELIMITER $$
CREATE PROCEDURE sp_eliminarCargo(in carId INT)
BEGIN
    DELETE FROM Cargo WHERE cargoId = carId;
END$$
DELIMITER ;
call sp_eliminarCargo(2);


-- editar Cargo
DELIMITER $$
CREATE PROCEDURE sp_editarCargo(carId INT, nom VARCHAR(30), des VARCHAR(100))
BEGIN
    UPDATE Cargo
    SET nombreCargo = nom, descripcionCargo = des
    WHERE cargoId = carId;
END$$
DELIMITER ;
call sp_editarCargo(1, 'Programador', 'Programa');

-- Buscar Cargo
DELIMITER $$
CREATE PROCEDURE sp_buscarCargo(carId INT)
BEGIN
    SELECT * FROM Cargo WHERE cargoId = carId;
END$$
DELIMITER ;
call sp_buscarCargo(2);


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
CREATE PROCEDURE sp_buscarCompra(compraId INT)
BEGIN
    SELECT * FROM Compras WHERE compraId = compraId;
END$$
DELIMITER ;

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Categoria Productos///////////////////////////////////////////////////////
 
-- Agregar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_agregarCategoriasProducto(nom VARCHAR(30), descCateg VARCHAR(100))
BEGIN
    INSERT INTO CategoriaProductos(nombreCategoria, descripcionCategoria)
    VALUES(nom, descCateg);
END$$
DELIMITER ;
call sp_agregarCategoriasProducto('Limpieza e higiene','cepillo de dientes');


-- Listar Categorías de Producto
DELIMITER $$
CREATE PROCEDURE sp_listarCategoriasProductos()
BEGIN
    SELECT * FROM CategoriaProductos;
END$$
DELIMITER ;
call sp_listarCategoriasProductos();

-- Eliminar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_eliminarCategoriaProducto(IN categoriaPId INT)
BEGIN
    DELETE FROM CategoriaProductos 
    WHERE categoriaProductosId = categoriaPId;
END$$
DELIMITER ;
call sp_eliminarCategoriaProducto(2);

--  Editar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_editarCategoriaProducto(IN categoriaPId INT, nom VARCHAR(30), descCateg VARCHAR(100))
BEGIN
    UPDATE CategoriaProductos
    SET nombreCategoria = nom, descripcionCategoria = descCateg
    WHERE categoriaProductosId = categoriaPId;
END$$
DELIMITER ;
call sp_editarCategoriaProducto(1, 'Ropa 1', 'Descripcion 1');

-- Buscar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_buscarCategoriaProducto(categoriaPId INT)
BEGIN
    SELECT * FROM CategoriaProductos WHERE categoriaProductosId = categoriaPId;
END$$
DELIMITER ;
call sp_buscarCategoriaProducto(2);

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Distribuidores///////////////////////////////////////////////////////


-- Agregar Distribuidor
DELIMITER $$
CREATE PROCEDURE sp_agregarDistribuidor(nomDistribuidor VARCHAR(30), dirDistribuidor VARCHAR(200), nitDistribuidor VARCHAR(15), telDistribuidor VARCHAR(15), webDistribuidor VARCHAR(50))
BEGIN
    INSERT INTO Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web)
    VALUES(nomDistribuidor, dirDistribuidor, nitDistribuidor, telDistribuidor, webDistribuidor);
END$$
DELIMITER ;
call sp_agregarDistribuidor('No cap','Real facts','4272870','42119921','www.Trueno.2.0.1.9');

-- Listar Distribuidores
DELIMITER $$
CREATE PROCEDURE sp_listarDistribuidores()
BEGIN
    SELECT * FROM Distribuidores;
END$$
DELIMITER ;
call sp_listarDistribuidores;

-- Eliminar Distribuidor
DELIMITER $$
CREATE PROCEDURE sp_eliminarDistribuidor(distId INT)
BEGIN
    DELETE FROM Distribuidores WHERE distribuidorId = distId;
END$$
DELIMITER ;
call sp_eliminarDistribuidor(2);

-- Editar Distribuidor
DELIMITER $$
CREATE PROCEDURE sp_EditarDistribuidor(IN distId INT, IN nomD VARCHAR(30), IN dirD VARCHAR(200), IN niD VARCHAR(15), IN telD VARCHAR(15), IN webD VARCHAR(50))
BEGIN
    UPDATE Distribuidores
    SET nombreDistribuidor = nomD, direccionDistribuidor = dirD, nitDistribuidor = niD, telefonoDistribuidor = telD, web = webD
    WHERE distribuidorId = distId;
END$$
DELIMITER ;
call sp_editarDistribuidor(1, 'Rain','Trueno','000000','12344321','MeJodí.tainy.com');

-- Buscar Distribuidor 
DELIMITER $$
CREATE PROCEDURE sp_buscarDistribuidor(distId INT)
BEGIN
    SELECT * FROM Distribuidores WHERE distribuidorId = distId;
END$$
DELIMITER ;
call sp_buscarDistribuidor(1);

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
CREATE PROCEDURE sp_buscarProducto(productosId INT)
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
CREATE PROCEDURE sp_buscarPromocion(promocionesId INT)
BEGIN
    SELECT * FROM Promociones WHERE promocionesId = promocionesId;
END$$
DELIMITER ;


 -- //////////////////////////////////////////////////////////////////////Procedimientos para TicketSoporte///////////////////////////////////////////////////////
-- Agregar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_agregarTicketSoporte(descT VARCHAR(250), cliId INT, factId INT)
BEGIN
    INSERT INTO TicketSoporte(descripcionTicket, estatus, clienteId, facturaId)
    VALUES(descT, 'Recién Creado', cliId, factId);
END$$
DELIMITER ;
call sp_agregarTicketSoporte('Problema ',1, NULL);

-- Listar Tickets de Soporte
DELIMITER $$
CREATE PROCEDURE sp_listarTicketSoporte()
BEGIN
    select TS.ticketSoporteId, TS.descripcionTicket, TS.estatus,
	CONCAT("ID:", C.clienteId, " | ",  C.nombre,  " ", C.apellido)AS Cliente, TS.facturaId from TicketSoporte TS
	join Clientes C on TS.clienteId = C.clienteId;
    
END$$
DELIMITER ;
call sp_listarTicketSoporte();

-- Eliminar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_eliminarTicketSoporte(ticketSId INT)
BEGIN
    DELETE FROM TicketSoporte WHERE ticketSoporteId = ticketSId;
END$$
DELIMITER ;
call sp_eliminarTicketSoporte(2);

-- Editar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_actualizarTicketSoporte(ticketId INT, descT VARCHAR(250), est VARCHAR(30), cliId INT, factId INT)
BEGIN
    UPDATE TicketSoporte
    SET descripcionTicket = descT, estatus = est, clienteId = cliId, facturaId = factId
    WHERE ticketSoporteId = ticketId;
END$$
DELIMITER ;

call sp_actualizarTicketSoporte(1 ,'Problema resuelto', 'Resuelto', 2, NULL);

-- Buscar Ticket de Soporte
DELIMITER $$
CREATE PROCEDURE sp_buscarTicketSoporte(ticketSId INT)
BEGIN
    SELECT * FROM TicketSoporte WHERE ticketSoporteId = ticketSId;
END$$
DELIMITER ;
call sp_buscarTicketSoporte(1);

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Facturas///////////////////////////////////////////////////////

-- Agregar Factura
DELIMITER $$
CREATE PROCEDURE sp_agregarFactura(fec DATE, hor TIME, cliId INT, empId INT, tot DECIMAL(10,2))
BEGIN
    INSERT INTO Facturas(fecha, hora, clienteId, empleadoId, total)
    VALUES(fec, hor, cliId, empId, tot);
END$$
DELIMITER ;
call sp_agregarFactura ('2024-05-07', '12:00', 1, 1, '12.00');


-- Listar Facturas
DELIMITER $$
CREATE PROCEDURE sp_listarFacturas()
BEGIN
    SELECT * FROM Facturas;
END$$
DELIMITER ;
call sp_listarFacturas();

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
CREATE PROCEDURE sp_buscarFactura(facturaId INT)
BEGIN
    SELECT * FROM Facturas WHERE facturaId = facturaId;
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

-- Buscar Detalle de Factura 
DELIMITER $$
CREATE PROCEDURE sp_buscarDetalleFactura(detalleFacturaId INT)
BEGIN
    SELECT * FROM DetalleFactura WHERE detalleFacturaId = detalleFacturaId;
END$$
DELIMITER ;