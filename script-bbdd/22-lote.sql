CREATE TABLE public.lote
(
    id_lote bigint NOT NULL,
    nro_lote character varying(5) COLLATE pg_catalog."default" NOT NULL,
    id_formula bigint NOT NULL,
    observaciones text COLLATE pg_catalog."default",
    fecha timestamp without time zone NOT NULL,
    estado character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_estado timestamp without time zone,
    obs_estado text COLLATE pg_catalog."default",
    CONSTRAINT pk_lote PRIMARY KEY (id_lote),
    CONSTRAINT unq_nrolote UNIQUE (nro_lote),
    CONSTRAINT fk_lote_formula FOREIGN KEY (id_formula)
        REFERENCES public.formula (id_formula) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE public.lote_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;