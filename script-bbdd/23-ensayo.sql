CREATE TABLE public.ensayo
(
	id_ensayo bigint NOT NULL,
    id_lote bigint NOT NULL,
    fecha date NOT NULL,
    observaciones text COLLATE pg_catalog."default",
    condiciones text COLLATE pg_catalog."default" NOT NULL,
    maquina character varying(100) COLLATE pg_catalog."default" NOT NULL,
    estado character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_ensayo PRIMARY KEY (id_ensayo),
    CONSTRAINT fk_ensayo_lote FOREIGN KEY (id_lote)
        REFERENCES public.lote (id_lote) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.ensayo
    ALTER COLUMN fecha TYPE timestamp without time zone ;

CREATE SEQUENCE public.ensayo_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999;