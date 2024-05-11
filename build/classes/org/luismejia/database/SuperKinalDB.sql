drop database if exists SuperKinalDB;
 
create database if not exists SuperKinalDB;
 
use SuperKinalDB;

SET GLOBAL time_zone = '-6:00';
 
create table Clientes(
		clienteId int not null auto_increment,
        nombre varchar (30) not null,	
        apellido varchar (30) not null,
        telefono varchar ( 15) not null,
        direccion varchar (200) not null,
        nit varchar(15) not null,
        primary key PK_clienteId (clienteId)
);

create table Cargo(
		cargoId int(11) not null auto_increment primary key,
        nombreCargo varchar(30) not null,
        descripcionCargo varchar(100)

);

create table Compras(
		compraId int(11) not null auto_increment primary key,
        fechaCompra date not null,
        totalCompra decimal(10,2)
        
);

create table CategoriaProductos (
		categoriaProductosId int(11) not null auto_increment primary key,
        nombreCategoria varchar(30) not null,
        descripcionCategoria varchar(100) not null

);

create table Distribuidores (
		distribuidorId int(11) not null auto_increment primary key,
        nombreDistribuidor varchar(30) not null,
        direccionDistribuidor varchar(200) not null,
        nitDistribuidor varchar(15) not null,
        telefonoDistribuidor varchar(15) not null,
        web varchar(50) 
);

create table Productos(
		productosId int(11) not null primary key auto_increment,
        nombreProducto varchar(50) not null,
        descripcionProducto varchar(100) not null,
        cantidadStock int(11) not null,
        precionVentaUnitario decimal(10,2) not null,
        precioVentaMayor decimal(10,2) not null,
        precioCompra decimal(10,2) not null,
        imagen blob,
        distribuidorId int not null,
        categoriaProductosId int not null,
        constraint FK_DistribuidorId_Productos foreign key Productos(distribuidorId) references Distribuidores (distribuidorId),
        constraint FK_CategoriaProductosId_Productos foreign key Productos(categoriaProductosId) references CategoriaProductos (categoriaProductosId)
);

create table Promociones (
		promocionesId int(11) primary key not null auto_increment,
        precioPromocion decimal(10,2) not null,
        descripcionPromocion varchar(100),
        fechaInicio date not null,
        fechaFinalizacion date not null,
        productosId int not null,
        constraint FK_ProductosId_Promociones foreign key Promociones(productosId) references Productos(productosId)
);

create table Empleados(
		empleadoId int(11) not null primary key auto_increment,
        nombreEmpleado varchar(30) not null,
        apellidoEmpleado varchar(30) not null,
        sueldo decimal(10,2) not null,
        horaDeEntrada time not null,
        horaDeSalida time not null,
        cargoId int not null,
        encargadoId int,
        constraint FK_CargoId_Empleados foreign key Empleados(cargoId) references Cargo (cargoId),
        constraint FK_EncargadoId foreign key Empleados(encargadoId) references Empleados(empleadoId)
);

create table Facturas(
		facturaId int(11) not null auto_increment primary key,
        fecha date not null,
        hora time not null,
        clienteId int not null,
        empleadoId int not null,
        total decimal(10,2), 
			constraint FK_clienteId_Facturas foreign key (clienteId) 
				references Clientes(clienteId),
		constraint FK_empleadoId_Facturas foreign key (empleadoId) references Empleados(empleadoId)
);

create table TicketSoporte(
    ticketSoporteId int not null auto_increment,
    descripcionTicket varchar(250) not null,
    estatus Varchar(30) not null,
    clienteId int not null,
    facturaId int,
    primary key (ticketSoporteId), constraint FK_clienteId_TicketSoporte foreign key (clienteId) references Clientes(clienteId),
    constraint FK_facturaId_TicketSoporte foreign key (facturaId) references Facturas(facturaId)
);

create table DetalleFactura(
		detalleFacturaId int(11) not null auto_increment primary key,
        facturaId int not null,
        productoId int not null,
        constraint FK_FacturaId_DetalleFactura foreign key DetalleFactura (facturaId) references Facturas(facturaId),
        constraint FK_ProductoId_DetalleFactura foreign key DetalleFactura (productoId) references Productos(productosId)
);
 
INSERT INTO Clientes ( nombre, apellido, telefono, direccion,nit) values
 
	('Pablo', 'Blas', '4321-1234', 'Pueblo Paleta','487564-0'),
    ('Daniel', 'Flakk', '9874-5612', 'Ciudad Ferrica','84921579-2'),
    ('Luis', 'Mejia', '1000-2000', 'Elmore','93475124-0'),
    ('Bobina', 'Tesla', '1000-2000', 'Elmore','93475124-0');

    SELECT * FROM Clientes;
 
INSERT INTO Cargo( nombreCargo, descripcionCargo)VALUES
('Desarrollador', 'Servicio tecnico');
SELECT * FROM Cargo;
SELECT * from Empleados;
select * from facturas;
