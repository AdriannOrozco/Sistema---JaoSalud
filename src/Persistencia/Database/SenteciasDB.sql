
------ 1. Nombre de la conexión ------
(La base de datos no usará MySQL Workbench de un monitor principal, sino de otro.)
Nombre de la conexión: JaoSalud 

------ 2. Nombre de la base de datos o esquema ------
jaosalud (diferencia con la conexión, las mayúsculas.)

------ 3. Creación de la primera tabla ------
Nombre: usuarios

Código SQL:

CREATE TABLE usuarios(
identificador_usuario INT PRIMARY KEY,
usuario VARCHAR(15) UNIQUE NOT NULL,
contraseña VARCHAR(15) NOT NULL,
rol VARCHAR(30) NOT NULL
);

------ 4. Creación de la segunda tabla ------
Nombre: pacientes


CREATE TABLE pacientes(

primerNombre VARCHAR(30) NOT NULL,
segundoNombre VARCHAR(30) DEFAULT "No tiene",
primerApellido VARCHAR(30) NOT NULL,
segundoApellido VARCHAR(30) NOT NULL,
tipoDocumento VARCHAR(15) NOT NULL, 
numeroDocumento VARCHAR(15) NOT NULL UNIQUE PRIMARY KEY,
telefono VARCHAR(15) UNIQUE DEFAULT "No tiene",
direccionResidencia VARCHAR(50) NOT NULL,
estadoCivil VARCHAR(25) NOT NULL,
genero VARCHAR(20) NOT NULL DEFAULT "Sin especificar.",
email VARCHAR(60) UNIQUE DEFAULT "No tiene.",
EPS VARCHAR(20) NOT NULL,
tipoSangre VARCHAR(20) NOT NULL,
fechaNacimiento DATE NOT NULL,
fechaRegistro DATE NOT NULL,
edad VARCHAR(4) NOT NULL
);


------ 5. Creación de la tercera tabla -----

Nombre: consultorios

CREATE TABLE consultorios (
 idConsultorio INT AUTO_INCREMENT PRIMARY KEY,
 consultorio VARCHAR(25) NOT NULL);

------ 6. Creación de la cuarta tabla ------

Nombre: medicos

CREATE TABLE medicos (
primerNombre VARCHAR(30) NOT NULL,
segundoNombre VARCHAR(30),
primerApellido VARCHAR(30) NOT NULL,
segundoApellido VARCHAR(30) NOT NULL,
identificacionDoctor VARCHAR(15) NOT NULL UNIQUE PRIMARY KEY,
especialidad VARCHAR(20) NOT NULL,
salario DOUBLE NOT NULL,
añosExperiencia INT NOT NULL
);

------ 7. Creación de la quinta tabla ------

Nombre: citas

CREATE TABLE citas (
    idConsultorio INT NOT NULL,
    identificacionDoctor VARCHAR(15) NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    fechaCita DATE NOT NULL,
    hora VARCHAR(12) NOT NULL,
    idCita INT NOT NULL UNIQUE PRIMARY KEY,
    fechaRegistro DATE NOT NULL,
    nombrePaciente VARCHAR(100) NOT NULL,
    numeroDocumento VARCHAR(15) NOT NULL,
    estado BOOLEAN DEFAULT true,
    FOREIGN KEY (idConsultorio) REFERENCES consultorios(idConsultorio),
    FOREIGN KEY (identificacion) REFERENCES medicos(identificacion)
);

------ 8. Creación de la sexta tabla ------

Nombre: medicamentos.

CREATE TABLE medicamentos (
idMedicamento INT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(30) NOT NULL UNIQUE, 
precio DOUBLE NOT NULL,
dosisDiarias INT NOT NULL,
disponible BOOLEAN NOT NULL
);
