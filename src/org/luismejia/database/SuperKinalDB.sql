drop database if exists SuperKinalDB;

create database if not exists SuperKinalDB;

use SuperKinalDB;

SET GLOBAL time_zone = '-6:00';

create table Clientes(
    clienteId int not null auto_increment,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    telefono varchar(15) not null,
    direccion varchar(200) not null,
    nit varchar(15) not null,
    primary key (clienteId)
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

create table CategoriaProductos(
    categoriaProductosId int(11) not null auto_increment primary key,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(100) not null
);

create table Distribuidores(
    distribuidorId int(11) not null auto_increment primary key,
    nombreDistribuidor varchar(30) not null,
    direccionDistribuidor varchar(200) not null,
    nitDistribuidor varchar(15) not null,
    telefonoDistribuidor varchar(15) not null,
    web varchar(50)
);

create table Productos(
    productosId int(11) not null auto_increment primary key,
    nombreProducto varchar(50) not null,
    descripcionProducto varchar(100) not null,
    cantidadStock int(11) not null,
    precioVentaUnitario decimal(10,2) not null,
    precioVentaMayor decimal(10,2) not null,
    precioCompra decimal(10,2) not null,
    imagen longblob,
    distribuidorId int not null,
    categoriaProductosId int not null,
    foreign key (distribuidorId) references Distribuidores(distribuidorId),
    foreign key (categoriaProductosId) references CategoriaProductos(categoriaProductosId)
);

create table detalleCompra(
    detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productosId int not null,
    compraId int not null,
    primary key (detalleCompraId),
    foreign key (productosId) references Productos(productosId),
    foreign key (compraId) references Compras(compraId)
);

create table Promociones(
    promocionesId int(11) not null auto_increment primary key,
    precioPromocion decimal(10,2) not null,
    descripcionPromocion varchar(100),
    fechaInicio date not null,
    fechaFinalizacion date not null,
    productosId int not null,
    foreign key (productosId) references Productos(productosId)
);

create table Empleados(
    empleadoId int(11) not null auto_increment primary key,
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar(30) not null,
    sueldo decimal(10,2) not null,
    horaDeEntrada time not null,
    horaDeSalida time not null,
    cargoId int not null,
    encargadoId int,
    foreign key (cargoId) references Cargo(cargoId),
    foreign key (encargadoId) references Empleados(empleadoId)
);

create table Facturas(
    facturaId int(11) not null auto_increment primary key,
    fecha date not null,
    hora time not null,
    clienteId int not null,
    empleadoId int not null,
    total decimal(10,2),
    foreign key (clienteId) references Clientes(clienteId),
    foreign key (empleadoId) references Empleados(empleadoId)
);

create table TicketSoporte(
    ticketSoporteId int not null auto_increment primary key,
    descripcionTicket varchar(250) not null,
    estatus varchar(30) not null,
    clienteId int not null,
    facturaId int,
    foreign key (clienteId) references Clientes(clienteId),
    foreign key (facturaId) references Facturas(facturaId)
);

create table DetalleFactura(
    detalleFacturaId int(11) not null auto_increment primary key,
    facturaId int not null,
    productosId int not null,
    foreign key (facturaId) references Facturas(facturaId),
    foreign key (productosId) references Productos(productosId)
);

create table NivelesAcceso(
    nivelAccesoId int not null auto_increment primary key,
    nivelAcceso varchar(40)
);

create table Usuarios(
    usuarioId int not null auto_increment primary key,
    usuario varchar(30) not null,
    contrasenia varchar(100) not null,
    nivelAccesoId int not null,
    empleadoId int not null,
    foreign key (nivelAccesoId) references NivelesAcceso(nivelAccesoId),
    foreign key (empleadoId) references Empleados(empleadoId)
);

INSERT INTO Clientes (nombre, apellido, telefono, direccion, nit) VALUES
    ('Pablo', 'Blas', '4321-1234', 'Pueblo Paleta', '487564-0'),
    ('Daniel', 'Flakk', '9874-5612', 'Ciudad Ferrica', '84921579-2'),
    ('Luis', 'Mejia', '1000-2000', 'Elmore', '93475124-0'),
    ('Bobina', 'Tesla', '1000-2000', 'Elmore', '93475124-0');

INSERT INTO Cargo (nombreCargo, descripcionCargo) VALUES
    ('Desarrollador', 'Servicio tecnico');

INSERT INTO NivelesAcceso (nivelAcceso) VALUES 
    ('Admin'), 
    ('Usuario');

select * from Usuarios;

select * from DetalleFactura
join Facturas on DetalleFactura.facturaId = Facturas.facturaId
join Clientes on Facturas.clienteId = Clientes.clienteId
join Productos on DetalleFactura.productosId = Productos.productosId