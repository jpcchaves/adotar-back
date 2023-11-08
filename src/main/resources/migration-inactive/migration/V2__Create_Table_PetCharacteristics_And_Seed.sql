CREATE TABLE
    public.pet_characteristics
(
    id             bigserial                      NOT NULL,
    characteristic character varying(50)          NULL,
    created_at     timestamp(6) without time zone NULL,
    updated_at     timestamp(6) without time zone NULL
);

ALTER TABLE
    public.pet_characteristics
    ADD
        CONSTRAINT pet_characteristics_pkey PRIMARY KEY (id);

insert into "public"."pet_characteristics" ("characteristic", "created_at", "updated_at")
values ('Castrado', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Vermifugado', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Vacinado', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Brincalhão', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Tranquilo', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Energético', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Lida bem com outros PETs', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Lida bem com crianças', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Lida bem com visitas', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Tímido', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Carente', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Guarda', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Manhoso', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Amigão', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Dócil', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Leal', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Companheiro', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Inteligente', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Adaptável', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329'),
       ('Afetuoso', '2023-06-14 04:29:43.329', '2023-06-14 04:29:43.329');