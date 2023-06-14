CREATE TABLE
    public.pets_characteristics
(
    pet_id            bigint NOT NULL,
    characteristic_id bigint NOT NULL
);

ALTER TABLE
    public.pets_characteristics
    ADD
        CONSTRAINT pets_characteristics_pkey PRIMARY KEY (characteristic_id);