CREATE TABLE MOLDES (
	id_molde int8 NOT NULL,
	codigo varchar(30) NOT NULL,
	estado boolean NOT NULL,
	nombre varchar(100) NOT NULL,
	ubicacion text NULL,
	bocas numeric NULL,
	observaciones text NULL,
	CONSTRAINT moldes_pk PRIMARY KEY (id_molde),
	CONSTRAINT moldes_un UNIQUE (codigo)
);

CREATE SEQUENCE moldes_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	

CREATE TABLE moldeboca (
	id_molde_boca int8 NOT NULL,
	id_molde int8 NOT NULL,
	nroboca numeric NOT NULL,
	estado boolean NOT NULL,
	CONSTRAINT moldeboca_pk PRIMARY KEY (id_molde_boca),
	CONSTRAINT moldeboca_un UNIQUE (id_molde_boca),
	CONSTRAINT moldeboca_fk FOREIGN KEY (id_molde) REFERENCES public.moldes(id_molde)
);

CREATE SEQUENCE moldeboca_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
CREATE TABLE moldedimension (
	id_molde_dimension int8 NOT NULL,
	id_molde int8 NOT NULL,
	tipodimension varchar NULL,
	valordimension numeric NULL,
	CONSTRAINT moldedimension_pk PRIMARY KEY (id_molde_dimension),
	CONSTRAINT moldedimension_un UNIQUE (id_molde_dimension),
	CONSTRAINT moldedimension_fk FOREIGN KEY (id_molde) REFERENCES public.moldes(id_molde)
);

CREATE SEQUENCE public.moldedimension_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;