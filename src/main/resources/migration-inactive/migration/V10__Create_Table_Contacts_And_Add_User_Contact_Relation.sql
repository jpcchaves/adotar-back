CREATE TABLE public.contacts
(
    id     bigserial NOT NULL,
    phone1 character varying(11),
    phone2 character varying(11),
    phone3 character varying(11),
    CONSTRAINT contacts_pkey
        PRIMARY KEY (id)
);

ALTER TABLE public.users
    ADD COLUMN contact_id bigint;

ALTER TABLE public.users
    ADD FOREIGN KEY (contact_id) references public.contacts ("id");
