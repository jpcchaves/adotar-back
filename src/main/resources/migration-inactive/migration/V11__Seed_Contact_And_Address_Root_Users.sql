INSERT INTO public.contacts ("phone1", "phone2", "phone3")
VALUES ('00999999999', '00999999999', '00999999999'),
       ('00999999999', '00999999999', '00999999999');

INSERT INTO public.addresses ("zipcode", "street", "number", "complement", "neighborhood", "city", "state")
VALUES ('', '', '', '', '', '', ''),
       ('', '', '', '', '', '', '');

UPDATE public.users
SET address_id = 1
WHERE id = 1;
UPDATE public.users
SET address_id = 2
WHERE id = 2;

UPDATE public.users
SET contact_id = 1
WHERE id = 1;
UPDATE public.users
SET contact_id = 2
WHERE id = 2;