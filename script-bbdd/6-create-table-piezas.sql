-- public.pieza definition

-- Drop table

-- DROP TABLE public.pieza;

CREATE TABLE public.pieza (
	id_pieza int8 NOT NULL,
	codigo varchar(30) NOT NULL,
	codigo_interno varchar(30) NOT NULL,	
	nombre varchar(150) NOT NULL,
	denominacion varchar(150) NULL,
	tipo varchar(30) NULL,
	esProducto bool NOT NULL,	
	CONSTRAINT pieza_pk PRIMARY KEY (id_pieza),
	CONSTRAINT pieza_un UNIQUE (codigo)
);


-- public.piezaSimple definition

-- Drop table

-- DROP TABLE public.piezaSimple;

CREATE TABLE public.piezaSimple (
	id_pieza int8 NOT NULL,
	CONSTRAINT id_piezaSimple_pk PRIMARY KEY (id_pieza),
	CONSTRAINT piezaSimple_un UNIQUE (id_pieza),	
	CONSTRAINT piezaSimple_fk FOREIGN KEY (id_pieza) REFERENCES public.pieza(id_pieza)

);

-- public.piezaSimple foreign keys

ALTER TABLE public.piezaSimple ADD CONSTRAINT piezaSimple_id_fk FOREIGN KEY (id_pieza) REFERENCES public.pieza(id_pieza);


-- public.piezaCompuesta definition

-- Drop table

-- DROP TABLE public.piezaCompuesta;

CREATE TABLE public.piezaCompuesta (
	id_pieza int8 NOT NULL,
	CONSTRAINT id_piezaCompuesta_pk PRIMARY KEY (id_pieza),
	CONSTRAINT piezaCompuesta_un UNIQUE (id_pieza),
	CONSTRAINT piezaCompuesta_fk FOREIGN KEY (id_pieza) REFERENCES public.pieza(id_pieza)
);

-- public.piezaCompuesta foreign keys

ALTER TABLE public.piezaCompuesta ADD CONSTRAINT piezaCompuesta_id_fk FOREIGN KEY (id_pieza) REFERENCES public.pieza(id_pieza);


CREATE SEQUENCE pieza_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
-- DROP TABLE 	public.piezaDePieza ;

CREATE TABLE public.piezaDePieza (
	id_pieza_compuesta int8 NOT NULL,
	id_pieza int8 NOT NULL,
	CONSTRAINT id_piezaDePieza_pk PRIMARY KEY (id_pieza,id_pieza_compuesta),
	CONSTRAINT piezaCompuesta_un UNIQUE (id_pieza,id_pieza_compuesta),
	CONSTRAINT piezaSimplePieza_fk FOREIGN KEY (id_pieza) REFERENCES public.pieza(id_pieza),
	CONSTRAINT piezaCompuestaPieza_fk FOREIGN KEY (id_pieza_compuesta) REFERENCES public.pieza(id_pieza)
);	