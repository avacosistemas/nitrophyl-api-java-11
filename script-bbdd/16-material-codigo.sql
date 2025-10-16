ALTER TABLE public.material ADD COLUMN codigo character varying(50) NOT NULL;

INSERT INTO public.material(id_material, nombre, codigo) VALUES (1, 'Nitrilo','NBR');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (2, 'EPDM','EPDM');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (3, 'Viton','FKM');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (4, 'Hypalon','CSM');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (5, 'Neopreno','CR');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (6, 'Silicona','VMQ');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (7, 'Natural','NR');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (8, 'Butilico','IIR');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (9, 'SBR','SBR');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (10, 'Poliurtano','AU');
INSERT INTO public.material(id_material, nombre, codigo) VALUES (11, 'Butadiero','BR');