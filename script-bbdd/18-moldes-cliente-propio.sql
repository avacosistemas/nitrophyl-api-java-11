ALTER TABLE public.moldes ADD COLUMN id_cliente_duenio BIGINT REFERENCES cliente(id_cliente);
ALTER TABLE public.moldes ADD COLUMN propio BOOLEAN;
update moldes set propio = true;
alter table moldes alter column propio set not null;