ALTER TABLE public.formula
    ADD COLUMN fecha date NOT NULL;

ALTER TABLE public.formula
    ADD COLUMN version integer NOT NULL;
	
ALTER TABLE public.formula
    ADD COLUMN norma character varying(100) NOT NULL;