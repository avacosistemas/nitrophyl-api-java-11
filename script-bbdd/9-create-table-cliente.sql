CREATE TABLE cliente (
	id_cliente int8 NOT NULL, 
	razon_social varchar(255) NOT NULL, 
	domicilio varchar(255),
	codigo_postal varchar(10),
	localidad varchar(255),
	provincia varchar(255),
	tel_fijo varchar(255),
	tel_celular varchar(255), 
	email varchar(255), 
	web_site varchar(255), 
	cuit varchar(255), 
	ingresos_brutos float8, 
	PRIMARY KEY (id_cliente)
);



CREATE TABLE cliente_contacto (
	id_contacto int8 NOT NULL, 
	id_cliente int8 NOT NULL, 
	tipo_contacto varchar(255) NOT NULL, 
	nombre_contacto varchar(255), 
	email_contacto varchar(255),
	tel_contacto varchar(255), 	
	PRIMARY KEY (id_contacto)
);

CREATE SEQUENCE public.cliente_contacto_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;