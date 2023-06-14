CREATE TABLE
    public.animal_types
(
    id         bigserial                      NOT NULL,
    created_at timestamp(6) without time zone NULL,
    type       character varying(30)          NULL,
    updated_at timestamp(6) without time zone NULL
);

ALTER TABLE
    public.animal_types
    ADD
        CONSTRAINT animal_types_pkey PRIMARY KEY (id);

insert into "public"."animal_types" ("created_at", "type", "updated_at")
values ('2023-06-14T04:29:43.329+05:45', 'Cachorro', '2023-06-14T04:29:43.329+05:45'),
       ('2023-06-14T04:29:43.329+05:45', 'Gato', '2023-06-14T04:29:43.329+05:45'),
       ('2023-06-14T04:29:43.329+05:45', 'Passarinho', '2023-06-14T04:29:43.329+05:45'),
       ('2023-06-14T04:29:43.329+05:45', 'Outro', '2023-06-14T04:29:43.329+05:45');
