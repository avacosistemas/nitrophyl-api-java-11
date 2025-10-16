CREATE TABLE public.maquina_prueba
(
    id_maquina_prueba bigint NOT NULL,
    id_maquina bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    CONSTRAINT fk_maquina_prueba_maquina PRIMARY KEY (id_maquina_prueba),
    CONSTRAINT fk_maquina FOREIGN KEY (id_maquina)
        REFERENCES public.maquina (id_maquina) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.maquina_prueba
    OWNER to postgres;
	
CREATE SEQUENCE public.maquina_prueba_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.maquina_prueba_seq
    OWNER TO postgres;