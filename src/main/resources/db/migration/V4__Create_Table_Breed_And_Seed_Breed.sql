CREATE TABLE
    public.breeds
(
    id             bigserial             NOT NULL,
    name           character varying(50) NOT NULL,
    animal_type_id bigint                NULL
);

ALTER TABLE
    public.breeds
    ADD
        CONSTRAINT breeds_pkey PRIMARY KEY (id);
