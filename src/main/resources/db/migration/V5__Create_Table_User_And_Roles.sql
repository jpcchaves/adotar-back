CREATE TABLE
    public.roles
(
    id   bigserial             NOT NULL,
    name character varying(50) NULL
);

ALTER TABLE
    public.roles
    ADD
        CONSTRAINT roles_pkey PRIMARY KEY (id);

CREATE TABLE
    public.users
(
    id         bigserial                      NOT NULL,
    created_at timestamp(6) without time zone NULL,
    deleted_at timestamp(6) without time zone NULL,
    email      character varying(150)         NOT NULL,
    first_name character varying(50)          NOT NULL,
    is_active  boolean                        NULL,
    is_admin   boolean                        NULL,
    last_name  character varying(50)          NULL,
    password   character varying(255)         NOT NULL,
    updated_at timestamp(6) without time zone NULL,
    username   character varying(100)         NOT NULL
);

ALTER TABLE
    public.users
    ADD
        CONSTRAINT users_pkey PRIMARY KEY (id);


CREATE TABLE
    public.users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);

