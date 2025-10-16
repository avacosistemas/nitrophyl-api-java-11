ALTER TABLE cliente ADD COLUMN nombre character varying(200);
UPDATE cliente c SET nombre = (SELECT razon_social FROM cliente c2 WHERE id_cliente = c2.id_cliente);
ALTER TABLE public.cliente ALTER COLUMN nombre SET NOT NULL;