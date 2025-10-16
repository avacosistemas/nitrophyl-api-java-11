CREATE TABLE public.ensayo_resultado
(
    id_ensayo_resultado bigint NOT NULL,
    resultado double precision NOT NULL,
    redondeo double precision NOT NULL,
    id_ensayo bigint NOT NULL,
    maximo double precision NOT NULL,
    minimo double precision NOT NULL,
    nombre character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_ensayo_resultado PRIMARY KEY (id_ensayo_resultado),
    CONSTRAINT fk_ensayo_res_ensayo FOREIGN KEY (id_ensayo)
        REFERENCES public.ensayo (id_ensayo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE public.ensayo_resultado_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

