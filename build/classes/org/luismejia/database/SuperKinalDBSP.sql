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
call sp_actualizarCliente(1,'Cole','Vargas','2222-3333','Cdad de Guatemala','8746521-0');
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
CREATE PROCEDURE sp_agregarEmpleado(nom VARCHAR(30), ape VARCHAR(30), sueldo DECIMAL(10,2), horaDeEntrada TIME, horaDeSalida TIME, cargoId INT)
BEGIN
    INSERT INTO Empleados(nombreEmpleado, apellidoEmpleado, sueldo, horaDeEntrada, horaDeSalida, cargoId)
    VALUES (nom, ape, sueldo, horaDeEntrada, horaDeSalida, cargoId);
END$$
DELIMITER ;
CALL sp_agregarEmpleado('Luis ', 'Mejia', 9800.00, '07:00:00', '16:00:00', 1  );
CALL sp_agregarEmpleado('Pedro ', 'Bravo', 9800.00, '07:00:00', '16:00:00', 1 );

-- Listar Empleados
DELIMITER $$
CREATE PROCEDURE sp_listarEmpleados()
BEGIN
    SELECT EP.empleadoId, EP.nombreEmpleado, EP.apellidoEmpleado, EP.sueldo, EP.horaDeEntrada, EP.horaDeSalida,
        CONCAT("Id: ", Ca.cargoId, " | ", Ca.nombreCargo) AS cargo, 
        CONCAT(EE.nombreEmpleado, ' ', EE.apellidoEmpleado) AS nombreEncargado
    FROM Empleados EP
    JOIN Cargo Ca ON EP.cargoId = Ca.cargoId
    LEFT JOIN Empleados EE ON EP.encargadoId = EE.empleadoId;
END$$
DELIMITER ;
CALL sp_listarEmpleados();

-- Eliminar Empleado
DELIMITER $$
CREATE PROCEDURE sp_eliminarEmpleado(empId INT)
BEGIN
    DELETE FROM Empleados WHERE empleadoId = empId;
END$$
DELIMITER ;

call sp_eliminarEmpleado(2);

--  Editar Empleado
DELIMITER $$
CREATE PROCEDURE sp_editarEmpleado(IN empId INT, nom VARCHAR(30), ape VARCHAR(30), suel DECIMAL(10,2), hEntr TIME, hSal TIME, carId INT)
BEGIN
    UPDATE Empleados
    SET nombreEmpleado = nom, apellidoEmpleado = ape, sueldo = suel, horaDeEntrada = hEntr, horaDeSalida = hSal, cargoId = carId
    WHERE empleadoId = empId;
END$$
DELIMITER ;
CALL sp_editarEmpleado(2, 'Skinny', 'Flakk', 3500.00, '07:00:00', '16:00:00', 1);

-- Buscar Empleado
DELIMITER $$
CREATE PROCEDURE sp_buscarEmpleado(in empId INT)
BEGIN
    SELECT EP.empleadoId, EP.nombreEmpleado, EP.apellidoEmpleado, EP.sueldo, EP.horaDeEntrada, EP.horaDeSalida,
        CONCAT("Id: ", Ca.cargoId, " | ", Ca.nombreCargo) AS cargo, 
        CONCAT(EE.nombreEmpleado, ' ', EE.apellidoEmpleado) AS nombreEncargado
    FROM Empleados EP
    JOIN Cargo Ca ON EP.cargoId = Ca.cargoId
    LEFT JOIN Empleados EE ON EP.encargadoId = EE.empleadoId
	WHERE EP.empleadoId = empId;
END$$
DELIMITER ;
call sp_buscarEmpleado(1);

-- Asignar encargado
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
call sp_agregarCargo('Inspector' , 'Inspector de seguridad');

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
CREATE PROCEDURE sp_agregarCompra()
BEGIN
    INSERT INTO Compras(fechaCompra)
    VALUES(curdate());
END$$
DELIMITER ;
call sp_agregarCompra();

-- Listar Compras
DELIMITER $$
CREATE PROCEDURE sp_listarCompra()
BEGIN
    SELECT * FROM Compras;
END$$
DELIMITER ;
call sp_listarCompra();

-- Eliminar Compra
DELIMITER $$
CREATE PROCEDURE sp_eliminarCompra(compId INT)
BEGIN
    DELETE FROM Compras WHERE compraId = compId;
END$$
DELIMITER ;
call sp_eliminarCompra(2);

-- Editar Compra
DELIMITER $$
CREATE PROCEDURE sp_actualizarCompra(IN compId INT, IN fechaComp DATE)
BEGIN
    UPDATE Compras
    SET fechaCompra = fechaComp
    WHERE compraId = compId;
END$$
DELIMITER ;
call sp_actualizarCompra(3, '2025-05-12');

-- Buscar Compra
DELIMITER $$
CREATE PROCEDURE sp_buscarCompra(compId INT)
BEGIN
    SELECT * FROM Compras WHERE compraId = compId;
END$$
DELIMITER ;
call sp_buscarCompra(1);


 -- //////////////////////////////////////////////////////////////////////Procedimientos para Categoria Productos///////////////////////////////////////////////////////
 
-- Agregar Categoría de Producto
DELIMITER $$
CREATE PROCEDURE sp_agregarCategoriasProducto(nom VARCHAR(30), descCateg VARCHAR(100))
BEGIN
    INSERT INTO CategoriaProductos(nombreCategoria, descripcionCategoria)
    VALUES(nom, descCateg);
END$$
DELIMITER ;
call sp_agregarCategoriasProducto('Albúm de música','Rancho Humilde');


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
call sp_editarCategoriaProducto(1, 'Tecnología ', 'Productos de informática');

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
call sp_editarDistribuidor(1, 'Lenovo Computers','USA','126420-4','42119921','Lenovo.com');

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
CREATE PROCEDURE sp_agregarProducto( nom VARCHAR(50), descProd VARCHAR(100), cant INT, precioUV DECIMAL(10,2), precioMV DECIMAL(10,2), precioC DECIMAL(10,2), img longblob, distId INT, catId INT)
BEGIN
    INSERT INTO Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagen, distribuidorId, categoriaProductosId)
    VALUES(nom, descProd, cant, precioUV, precioMV, precioC, img, distId, catId);
END$$
DELIMITER ;
CALL sp_agregarProducto('Fatal Fantassy', 'Alvaro Díaz', 100, 4500, 4300, 5000, NULL, 1, 1);

--  Listar Productos
DELIMITER $$
CREATE PROCEDURE sp_listarProductos()
BEGIN
    SELECT P.productosId, P.nombreProducto, P.descripcionProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor,  P.precioCompra,P.imagen, 
       CONCAT("Distribuidor: ", D.nombreDistribuidor) AS distribuidor,
       CONCAT("Categoría: ", CP.nombreCategoria) AS categoria
	FROM Productos P
	LEFT JOIN Distribuidores D ON P.distribuidorId = D.distribuidorId
	LEFT JOIN CategoriaProductos CP ON P.categoriaproductosId = CP.categoriaproductosId;
END$$
DELIMITER ;
call sp_listarProductos();

-- Eliminar Producto
DELIMITER $$
CREATE PROCEDURE sp_eliminarProducto(prodId INT)
BEGIN
    DELETE FROM Productos WHERE productosId = prodId;
END$$
DELIMITER ;
call sp_eliminarProducto(2);

-- Editar Producto
DELIMITER $$
CREATE PROCEDURE sp_editarProducto(IN prodId INT, nom VARCHAR(50), descProd VARCHAR(100), cant INT, precioUV DECIMAL(10,2), precioMV DECIMAL(10,2), precioC DECIMAL(10,2), img longblob, distribuidorId INT, categoriaProductosId INT)
BEGIN
    UPDATE Productos
    SET nombreProducto = nom, descripcionProducto = descProd, cantidadStock = cant, precioVentaUnitario = precioUV, precioVentaMayor = precioMV, precioCompra = precioC, imagen = img, distribuidorId = distribuidorId, categoriaProductosId = categoriaProductosId
    WHERE productosId = prodId;
END$$
DELIMITER ;
CALL sp_editarProducto(1, 'Laptop Lenovo', 'Laptop gamer', 100, 4500, 4300, 5000, NULL, 1, 1);

-- Buscar Producto
DELIMITER $$
CREATE PROCEDURE sp_buscarProducto(IN prodId INT)
BEGIN
    SELECT P.productosId, P.nombreProducto, P.descripcionProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor,  P.precioCompra,P.imagen, 
       CONCAT("Distribuidor: ", D.nombreDistribuidor) AS distribuidor,
       CONCAT("Categoría: ", CP.nombreCategoria) AS categoria
		FROM Productos P
		LEFT JOIN Distribuidores D ON P.distribuidorId = D.distribuidorId
		LEFT JOIN CategoriaProductos CP ON P.categoriaproductosId = CP.categoriaproductosId
		WHERE productosId = prodId;
END$$
DELIMITER ;
CALL sp_buscarProducto(1);

-- ////////////////////////////////////////////////////////////////////// Procedimientos para DetalleCompra ///////////////////////////////////////////////////////

DELIMITER $$
CREATE PROCEDURE sp_agregarDetalleCompra(cantComp INT, prodIdComp INT, compId INT)
BEGIN
    INSERT INTO detalleCompra(cantidadCompra, productosId, compraId)
    VALUES (cantComp, prodIdComp, compId);
END$$
DELIMITER ;


CALL sp_agregarDetalleCompra(50, 1, 1);

DELIMITER $$
CREATE PROCEDURE sp_listarDetalleCompra()
BEGIN
    SELECT *
	FROM detalleCompra;
END$$
DELIMITER ;

CALL sp_listarDetalleCompra();


DELIMITER $$
CREATE PROCEDURE sp_eliminarDetalleCompra(IN detCompId INT)
BEGIN
    DELETE FROM detalleCompra 
    WHERE detalleCompraId = detCompId;
END$$
DELIMITER ;

CALL sp_eliminarDetalleCompra(1);


DELIMITER $$
CREATE PROCEDURE sp_buscarDetalleCompra(IN detCompId INT)
BEGIN
    SELECT detalleCompraId, 
		cantidadCompra, 
		productosId, 
		compraId
	FROM detalleCompra 
	WHERE detalleCompraId = detCompId;
END$$
DELIMITER ;

CALL sp_buscarDetalleCompra(2);


DELIMITER $$
CREATE PROCEDURE sp_actualizarDetalleCompra(IN detCompId INT, IN cantComp INT, IN prodIdComp INT, IN compId INT)
BEGIN
    UPDATE detalleCompra
    SET cantidadCompra = cantComp, productosId = prodIdComp, compraId = compId
    WHERE detalleCompraId = detCompId;
END$$
DELIMITER ;

CALL sp_actualizarDetalleCompra(1, 10, 1, 1);

 -- //////////////////////////////////////////////////////////////////////Procedimientos para Promociones///////////////////////////////////////////////////////

-- Agregar Promoción
DELIMITER $$
CREATE PROCEDURE sp_agregarPromocion(precio DECIMAL(10,2), descPromo VARCHAR(100), fechaIni DATE, fechaFin DATE, productosId INT)
BEGIN
    INSERT INTO Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productosId)
    VALUES(precio, descPromo, fechaIni, fechaFin, productosId);
END$$
DELIMITER ;
CALL sp_agregarPromocion(0000.00, 'Que los generos sean para la música', '2024-05-12', '2025-05-12', 1);

-- Listar Promociones
DELIMITER $$
CREATE PROCEDURE sp_listarPromociones()
BEGIN
    SELECT 
		PR.promocionesId, 
		PR.precioPromocion, 
		PR.descripcionPromocion, 
		PR.fechaInicio, 
		PR.fechaFinalizacion, 
		CONCAT("Id: ", P.productosId," | ", P.nombreProducto) AS Producto
	FROM 
		Promociones PR
	JOIN 
		Productos P ON PR.productosId = P.productosId;
END$$
DELIMITER ;
call sp_listarPromociones();

-- Eliminar Promoción
DELIMITER $$
CREATE PROCEDURE sp_eliminarPromocion(IN promId INT)
BEGIN
    DELETE FROM Promociones WHERE promocionesId = promId;
END$$
DELIMITER ;
call sp_eliminarPromocion(2);

-- Editar Promoción
DELIMITER $$
CREATE PROCEDURE sp_actualizarPromocion(IN promId INT, IN precio DECIMAL(10,2), IN descPromo VARCHAR(100), IN fechaIni DATE, IN fechaFin DATE, IN prodId INT)
BEGIN
    UPDATE Promociones
    SET precioPromocion = precio, descripcionPromocion = descPromo, fechaInicio = fechaIni, fechaFinalizacion = fechaFin, productosId = prodId
    WHERE promocionesId = promId;
END$$
DELIMITER ;
CALL sp_actualizarPromocion(1, 1000, 'Black Friday', '2024-05-01', '2024-05-31', 1);


-- Buscar Promoción
DELIMITER $$
CREATE PROCEDURE sp_buscarPromocion(IN promId INT)
BEGIN
    SELECT 
		PR.promocionesId, 
		PR.precioPromocion, 
		PR.descripcionPromocion, 
		PR.fechaInicio, 
		PR.fechaFinalizacion, 
		CONCAT("Id: ", P.productosId," | ", P.nombreProducto) AS Producto
	FROM 
		Promociones PR
	JOIN 
		Productos P ON PR.productosId = P.productosId
	WHERE promocionesId = promId;
END$$
DELIMITER ;
CALL sp_buscarPromocion(2);

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
CREATE PROCEDURE sp_agregarFactura( cliId INT, empId INT)
BEGIN
    INSERT INTO Facturas(fecha, hora, clienteId, empleadoId)
    VALUES (curdate(), curtime(), cliId, empId);  
END$$
DELIMITER ;
CALL sp_agregarFactura(1, 1);


-- Listar Facturas
DELIMITER $$
CREATE PROCEDURE sp_listarFacturas()
BEGIN
    SELECT F.facturaId, F.fecha, F.hora, 
		   CONCAT("Id: ", C.clienteId, " | ", C.nombre, " ", C.apellido) AS cliente,
		   CONCAT("Id: ", E.empleadoId, " | ", E.nombreEmpleado, " ", E.apellidoEmpleado) AS empleado,
		   F.total
	FROM Facturas F
	JOIN Clientes C ON F.clienteId = C.clienteId
	JOIN Empleados E ON F.empleadoId = E.empleadoId;
END$$
DELIMITER ;
call sp_listarFacturas();

-- Eliminar Factura
DELIMITER $$
CREATE PROCEDURE sp_eliminarFactura(factId INT)
BEGIN
    DELETE FROM Facturas WHERE facturaId = factId;
END$$
DELIMITER ;
call sp_eliminarFactura(5);

-- Editar Factura
DELIMITER $$
CREATE PROCEDURE sp_actualizarFactura(IN factId INT, cliId INT, empId INT)
BEGIN
    UPDATE Facturas
    SET fecha = curdate(), hora = curtime(), clienteId = cliId, empleadoId = empId
    WHERE facturaId = factId;
END$$
DELIMITER ;
CALL sp_actualizarFactura(2, 2, 1);

-- Buscar Factura
DELIMITER $$
CREATE PROCEDURE sp_buscarFactura(factId INT)
BEGIN
    SELECT F.facturaId, F.fecha, F.hora, 
		   CONCAT("Id: ", C.clienteId, " | ", C.nombre, " ", C.apellido) AS cliente,
		   CONCAT("Id: ", E.empleadoId, " | ", E.nombreEmpleado, " ", E.apellidoEmpleado) AS empleado,
		   F.total
		FROM Facturas F
	JOIN Clientes C ON F.clienteId = C.clienteId
	JOIN Empleados E ON F.empleadoId = E.empleadoId
	WHERE facturaId = factId;
END$$
DELIMITER ;
call sp_buscarFactura(1);
 -- //////////////////////////////////////////////////////////////////////Procedimientos para DetalleFactura///////////////////////////////////////////////////////

-- Agregar Detalle de Factura
DELIMITER $$
CREATE PROCEDURE sp_agregarDetalleFactura(prodIdFact INT, factIdFact INT)
BEGIN
    INSERT INTO detalleFactura(productosId, facturaId)
    VALUES (prodIdFact, factIdFact);
END$$
DELIMITER ;
call sp_agregarDetalleFactura(1, 1);

-- Listar Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_listarDetallesFactura()
BEGIN
    SELECT * FROM DetalleFactura;
END$$
DELIMITER ;
call sp_listarDetallesFactura();

-- Eliminar Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_eliminarDetalleFactura(IN detalleFId INT)
BEGIN
    DELETE FROM DetalleFactura WHERE detalleFacturaId = detalleFId;
END$$
DELIMITER ;
call sp_eliminarDetalleFactura(4);

-- Editar Detalle de Factura
DELIMITER $$
CREATE PROCEDURE sp_EditarDetalleFactura(IN detFactId INT, IN prodIdFact INT, IN factIdFact INT)
BEGIN
    UPDATE detalleFactura
    SET productosId = prodIdFact, facturaId = factIdFact
    WHERE detalleFacturaId = detFactId;
END$$
DELIMITER ;

CALL sp_EditarDetalleFactura(1, 1, 1);

-- Buscar Detalle de Factura 
DELIMITER $$
CREATE PROCEDURE sp_buscarDetalleFactura(IN detalleFId INT)
BEGIN
    SELECT * FROM DetalleFactura WHERE detalleFacturaId = detalleFId;
END$$
DELIMITER ;
call sp_buscarDetalleFactura(4);

-- ///////////////////////////////////////////////////////////// PROCEDIMIENTOS PARA FACTURAS ///////////////////////////////////////////////////
Delimiter $$
create procedure sp_asignarTotalFactura(in factId int, in totalFact decimal (10,2))
Begin
	Update facturas
		set total = totalFact
			where facturaId =factId; 
End $$
Delimiter ;

Delimiter $$
create procedure sp_modificarStock(in detaFactId int, in stockActual int)
begin
	Update productos
		set cantidadStock = stockActual
			where productosId = detaFactId;
end $$
Delimiter ;

Delimiter $$
create procedure sp_asignarTotalCompra(in compId int, in totalC decimal (10,2))
Begin
	Update compras
		set totalCompra = totalC
			where compraId =compId; 
End $$
Delimiter ;

Delimiter $$
create procedure sp_modificarStockCompra(in productId int, in stockActual int)
begin
	Update productos
		set cantidadStock = stockActual
			where productosId = productId;
end $$
Delimiter ;

-- //////////////////////////////////////////////////////////////////////Procedimientos para Usuarios///////////////////////////////////////////////////////

-- Agregar Usuarios
DELIMITER $$
create procedure sp_agregarUsuario(us varchar(30), con varchar(100), nivAccId int, empId int)
begin
	insert into Usuarios(usuario, contrasenia, nivelAccesoId, empleadoId) values
		(us, con, nivAccId, empId);
end $$
DELIMITER ;
call sp_agregarUsuario('deSanta', '1234', 1, 1);

-- Buscar Usuarios

DELIMITER $$
create procedure sp_buscarUsuario(us varchar(30))
begin
	select * from Usuarios
		where usuario = us;
end $$
DELIMITER ;

select * from nivelesAcceso;

-- ////////////////////////////////////////////////////////////////////// Procedimientos para Niveles de Acceso ///////////////////////////////////////////////////////


DELIMITER $$
create procedure sp_listarNivelAcceso()
begin
	select * from nivelesAcceso;
end $$
DELIMITER ;

call sp_listarNivelAcceso();