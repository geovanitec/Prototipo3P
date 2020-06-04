create DATABASE siu;
USE siu;
#drop database siu;
-- -----------------------------------------------------
-- Table `educativo`.`Alumnos`
-- -----------------------------------------------------
CREATE TABLE alumnos
 (
  carnet_alumno VARCHAR(15),
  nombre_alumno VARCHAR(45),
  direccion_alumno VARCHAR(45),
  telefono_alumno VARCHAR(45),
  email_alumno VARCHAR(20),
  estatus_alumno VARCHAR(1),
  PRIMARY KEY (carnet_alumno)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------

-- Table `educativo`.`REGISTRO LOGIN`
-- -----------------------------------------------------

create table usuarios 
(
ID int primary key auto_increment,
NombreUsuario varchar (30) not null,
Pass varchar (30) not null,
CorreoUsuario varchar (40) not null

)ENGINE=INNODB DEFAULT CHARSET=latin1;
-- -----------------------------------------------------

-- Table `educativo`.`Maestros`
-- -----------------------------------------------------
select * from  maestros;
CREATE TABLE maestros
(
  codigo_maestro VARCHAR(5),
  nombre_maestro VARCHAR(45),
  direccion_maestro VARCHAR(45),
  telefono_maetro VARCHAR(45),
  email_maestro VARCHAR(20),
  estatus_maestro VARCHAR(1),
  PRIMARY KEY (codigo_maestro)
 ) ENGINE = InnoDB DEFAULT CHARSET=latin1;
  
-- -----------------------------------------------------
-- Table `educativo`.`Facultades`
-- -----------------------------------------------------
CREATE TABLE facultades
(
  codigo_facultad VARCHAR(5),
  nombre_facultad VARCHAR(45),
  estatus_facultad VARCHAR(1),
  PRIMARY KEY (codigo_facultad)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;



-- -----------------------------------------------------
-- Table `educativo`.`Carreras`
-- -----------------------------------------------------
CREATE TABLE carreras
(
  codigo_carrera VARCHAR(5),
  nombre_carrera VARCHAR(45),
  codigo_facultad VARCHAR(5),
  estatus_carrera VARCHAR(1),
  PRIMARY KEY (codigo_carrera),
  FOREIGN KEY (codigo_facultad) REFERENCES facultades(codigo_facultad)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------
-- Table `educativo`.`Cursos`
-- -----------------------------------------------------
CREATE TABLE cursos
(
  codigo_curso VARCHAR(5),
  codigo_carrera varchar(5),
  nombre_curso VARCHAR(45),
  estatus_curso VARCHAR(1),
  PRIMARY KEY (codigo_curso),
   FOREIGN KEY (codigo_carrera) REFERENCES carreras(codigo_carrera)
 ) ENGINE = InnoDB DEFAULT CHARSET=latin1;
 

-- -----------------------------------------------------
-- Table `educativo`.`Secciones`
-- -----------------------------------------------------
CREATE TABLE secciones
(
  codigo_seccion VARCHAR(5),
  nombre_seccion VARCHAR(45),
  estatus_seccion VARCHAR(1),
  PRIMARY KEY (codigo_seccion)
 ) ENGINE = InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `educativo`.`Sedes`
-- -----------------------------------------------------
CREATE TABLE sedes
(
  codigo_sede VARCHAR(5),
  nombre_sede VARCHAR(45),
  estatus_sede VARCHAR(1),
  PRIMARY KEY (codigo_sede)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------
-- Table `educativo`.`Aulas`
-- -----------------------------------------------------
CREATE TABLE aulas
(
  codigo_aula VARCHAR(5),
  nombre_aula VARCHAR(45),
  estatus_aula VARCHAR(1),
  PRIMARY KEY (codigo_aula)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE jornadas
(
	codigo_jornada VARCHAR(5),
    nombre_jornada VARCHAR(45),
    estatus_jornada VARCHAR(1),
    PRIMARY KEY (codigo_jornada)
) ENGINE=INNODB DEFAULT CHARSET=latin1;
 
-- -----------------------------------------------------
-- Table `educativo`.`Asignacion_cursos_alumnos`
-- -----------------------------------------------------
CREATE TABLE asignacioncursosalumnos
( 
  id_Alumno varchar(5),
  codigo_carrera VARCHAR(5),
  codigo_sede VARCHAR(5),
  codigo_jornada VARCHAR(5),
  codigo_seccion VARCHAR(5),
  codigo_aula VARCHAR(5),
  codigo_curso VARCHAR(5),
  carnet_alumno VARCHAR(15),
  Tipo_Nota varchar(70),
  Parcial_1 float,
  Parcial_2 float,
  Parcial_3 float,
  Parcial_1T float,
  Parcial_2T float,
  Parcial_3T float,
  Parcial_1B float,
  Unidad_1 float,
  Unidad_2 float,
  Unidad_3 float,
  Unidad_4 float,
  Extraordinario float,
  Privado float,
  Zona float,
  nota_asignacioncursoalumnos float, 
  PRIMARY KEY (id_Alumno,codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, carnet_alumno),
  FOREIGN KEY (codigo_carrera) REFERENCES carreras(codigo_carrera),
  FOREIGN KEY (codigo_sede) REFERENCES sedes(codigo_sede),
  FOREIGN KEY (codigo_jornada) REFERENCES jornadas(codigo_jornada),
  FOREIGN KEY (codigo_seccion) REFERENCES secciones(codigo_seccion),
  FOREIGN KEY (codigo_aula) REFERENCES aulas(codigo_aula),
  FOREIGN KEY (codigo_curso) REFERENCES cursos(codigo_curso),
  FOREIGN KEY (carnet_alumno) REFERENCES alumnos(carnet_alumno)
  ) ENGINE = InnoDB DEFAULT CHARSET=latin1;
-- -----------------------------------------------------
-- Table `educativo`.`Asignacion_cursos_maestros`
-- -----------------------------------------------------
CREATE TABLE asignacioncursosmastros
(
  id_Maestro varchar(5),
  codigo_carrera VARCHAR(5),
  codigo_sede VARCHAR(5),
  codigo_jornada VARCHAR(5),
  codigo_seccion VARCHAR(5),
  codigo_aula VARCHAR(5),
  codigo_curso VARCHAR(5),
  codigo_maestro VARCHAR(5),
  PRIMARY KEY (id_Maestro,codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso),
  FOREIGN KEY (codigo_carrera) REFERENCES carreras(codigo_carrera),
  FOREIGN KEY (codigo_sede) REFERENCES sedes(codigo_sede),
  FOREIGN KEY (codigo_jornada) REFERENCES jornadas(codigo_jornada),
  FOREIGN KEY (codigo_seccion) REFERENCES secciones(codigo_seccion),
  FOREIGN KEY (codigo_aula) REFERENCES aulas(codigo_aula),
  FOREIGN KEY (codigo_curso) REFERENCES cursos(codigo_curso),
  FOREIGN KEY (codigo_maestro) REFERENCES maestros(codigo_maestro)
  ) ENGINE = InnoDB DEFAULT CHARSET=latin1;
  
   create table Laboratorios
  (
    Codigo_Laboratorio varchar(5) primary key,
    Nombre_Laboratorio varchar(100),
    Nota_Laboratorio float,
    
    id_Alumno varchar(5),
    id_Maestro varchar(5),
    
    foreign key(id_Alumno) references asignacioncursosalumnos(id_Alumno),
    foreign key(id_Maestro) references asignacioncursosmastros(id_Maestro)
  )ENGINE = InnoDB DEFAULT CHARSET=latin1;

 
  select *from asignacioncursosalumnos;
  select *from Laboratorios;
  select *from usuarios ;
  select * from alumnos;
  select * from cursos;
  
  
  insert into usuarios(ID,NombreUsuario,Pass,CorreoUsuario) values (1,"123","123","root@gamil.com" );
  insert into alumnos(carnet_alumno,nombre_alumno,direccion_alumno,telefono_alumno,email_alumno,estatus_alumno) values ("1","Esduardo","zona 14"," 548784512","Esduardo@gamil.com","T");
	insert into maestros(codigo_maestro,nombre_maestro,direccion_maestro,telefono_maetro,email_maestro,estatus_maestro)values("2","Eddy","zona 18","54123657","eddybatres@gmail.com","T");
  insert into facultades (codigo_facultad,nombre_facultad,estatus_facultad) values ("8","Ingenieria","T");
  insert into carreras (codigo_carrera,nombre_carrera,codigo_facultad,estatus_carrera) values ("8","sistemas","8","T");
   insert into secciones (codigo_seccion,nombre_seccion,estatus_seccion) values ("10","A","T"); 
   insert into cursos(codigo_curso,codigo_carrera,nombre_curso,estatus_curso) values ("9","8","Programacion","T");
    insert into sedes (codigo_sede,nombre_sede,estatus_sede) values ("11","jutiapa","T");
      insert into aulas (codigo_aula,nombre_aula,estatus_aula) values ("12","b","T");
       insert into jornadas (codigo_jornada,nombre_jornada,estatus_jornada) values ("13","Matutina","T");
          #insert into Laboratorios (Codigo_Laboratorio,Nombre_Laboratorio,Nota_Laboratorio,id_Alumno,id_Maestro) values ("14","OBJETOS","50","1","2");
  
  