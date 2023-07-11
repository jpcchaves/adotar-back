ALTER TABLE public.users
    ADD COLUMN last_seen timestamp(6) without time zone NULL;

UPDATE public.users
SET last_seen = '2023-06-28'
WHERE id = 1;

UPDATE public.users
SET last_seen = '2023-06-28'
WHERE id = 2;
