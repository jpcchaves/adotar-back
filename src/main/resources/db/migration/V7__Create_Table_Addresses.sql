CREATE TABLE public.addresses
(
    id           bigserial              NOT NULL,
    zipcode      character varying(8)   NOT NULL,
    street       character varying(8)   NULL,
    number       character varying(20)  NULL,
    complement   character varying(255) NULL,
    neighborhood character varying(255) NULL,
    city         character varying(255) NOT NULL,
    state        character varying(50)  NOT NULL
);

ALTER TABLE
    public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);

ALTER TABLE public.users
    ADD COLUMN address_id bigint;

ALTER TABLE public.users
    ADD FOREIGN KEY (address_id) references addresses ("id");