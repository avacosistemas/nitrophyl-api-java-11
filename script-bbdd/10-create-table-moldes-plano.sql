CREATE TABLE moldeplano (
	id_molde_plano int8 NOT NULL,
	id_molde int8 NOT NULL,
	nombrearchivo varchar NULL,
	version numeric NULL,
	fecha timestamp NULL,
	archivo bytea NULL,
	CONSTRAINT moldeplano_pk PRIMARY KEY (id_molde_plano),
	CONSTRAINT moldeplano_un UNIQUE (id_molde_plano),
	CONSTRAINT moldeplano_fk FOREIGN KEY (id_molde) REFERENCES public.moldes(id_molde)
);

CREATE SEQUENCE public.moldeplano_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
CREATE TABLE moldefoto (
	id_molde_foto int8 NOT NULL,
	id_molde int8 NOT NULL,
	nombrearchivo varchar NULL,
	version numeric NULL,
	fecha timestamp NULL,
	archivo bytea NULL,
	CONSTRAINT moldefoto_pk PRIMARY KEY (id_molde_foto),
	CONSTRAINT moldefoto_un UNIQUE (id_molde_foto),
	CONSTRAINT moldefoto_fk FOREIGN KEY (id_molde) REFERENCES public.moldes(id_molde)
);

CREATE SEQUENCE public.moldefoto_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;