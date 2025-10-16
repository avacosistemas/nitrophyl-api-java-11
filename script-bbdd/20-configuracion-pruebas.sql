create table public.conf_prueba
(
    "id_conf_prueba" bigint not null,
    "id_maquina" bigint not null,
    "id_formula" bigint not null,
    "fecha" date not null,
    constraint "pk_conf_prueba" primary key ("id_conf_prueba"),
    constraint "fk_conf_prueba_maquina" foreign key ("id_maquina")
        references public.maquina (id_maquina) match simple
        on update no action
        on delete no action
        not valid,
    constraint "fk_conf_prueba_formula" foreign key ("id_formula")
        references public.formula (id_formula) match simple
        on update no action
        on delete no action
        not valid
);

alter table public.conf_prueba
    owner to postgres;
	
create table public."conf_prueba_param"
(
    "id_conf_prueba_param" bigint not null,
    "id_conf_prueba" bigint not null,
    "nombre" character varying(100),
    "valor_minimo" double precision,
    "valor_maximo" double precision,
    constraint "pk_conf_prueba_param" primary key ("id_conf_prueba_param"),
    constraint "fk_conf_prueba_param" foreign key ("id_conf_prueba")
        references public.conf_prueba ("id_conf_prueba") match simple
        on update no action
        on delete no action
        not valid
);

alter table public."conf_prueba_param"
    owner to postgres;
	
	
create table public."conf_prueba_cond"
(
    "id_conf_prueba_cond" bigint not null,
    "id_conf_prueba" bigint not null,
    "nombre" character varying(100),
    "valor" double precision,
    constraint "pk_conf_prueba_cond" primary key ("id_conf_prueba_cond"),
    constraint "fk_conf_prueba_cond" foreign key ("id_conf_prueba")
        references public.conf_prueba ("id_conf_prueba") match simple
        on update no action
        on delete no action
        not valid
);

alter table public."conf_prueba_cond"
    owner to postgres;
	
CREATE SEQUENCE public.conf_prueba_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
	
CREATE SEQUENCE public.conf_prueba_param_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE public.conf_prueba_cond_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;