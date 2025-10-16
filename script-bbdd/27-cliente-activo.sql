ALTER TABLE public.cliente
    ADD COLUMN activo boolean;
	
update cliente set activo = true;

ALTER TABLE public.cliente
    ALTER COLUMN activo SET NOT NULL;