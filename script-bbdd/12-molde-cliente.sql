CREATE TABLE public.molde_cliente
(
    id_molde integer NOT NULL,
    id_cliente integer NOT NULL,
    CONSTRAINT pk_molde_cliente PRIMARY KEY (id_molde, id_cliente),
    CONSTRAINT fk_molde FOREIGN KEY (id_molde)
        REFERENCES public.moldes (id_molde) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.molde_cliente
    OWNER to postgres;