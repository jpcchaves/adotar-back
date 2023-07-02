CREATE TABLE public.states
(
    id   bigserial NOT NULL,
    name varchar(60),
    uf   varchar(2),
    CONSTRAINT states_pkey
        PRIMARY KEY (id)
);

INSERT INTO "public"."states" ("name", "uf")
VALUES ('Acre', 'AC'),
       ('Alagoas', 'AL'),
       ('Amazonas', 'AM'),
       ('Amapá', 'AP'),
       ('Bahia', 'BA'),
       ('Ceará', 'CE'),
       ('Distrito Federal', 'DF'),
       ('Espírito Santo', 'ES'),
       ('Goiás', 'GO'),
       ('Maranhão', 'MA'),
       ('Minas Gerais', 'MG'),
       ('Mato Grosso do Sul', 'MS'),
       ('Mato Grosso', 'MT'),
       ('Pará', 'PA'),
       ('Paraíba', 'PB'),
       ('Pernambuco', 'PE'),
       ('Piauí', 'PI'),
       ('Paraná', 'PR'),
       ('Rio de Janeiro', 'RJ'),
       ('Rio Grande do Norte', 'RN'),
       ('Rondônia', 'RO'),
       ('Roraima', 'RR'),
       ('Rio Grande do Sul', 'RS'),
       ('Santa Catarina', 'SC'),
       ('Sergipe', 'SE'),
       ('São Paulo', 'SP'),
       ('Tocantins', 'TO');

