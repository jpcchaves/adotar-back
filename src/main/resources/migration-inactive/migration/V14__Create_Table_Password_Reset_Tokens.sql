CREATE TABLE public.password_reset_tokens
(
    id              bigserial     not null,
    expiration_time timestamp(6) with time zone,
    token           varchar(6)    NOT NULL,
    user_id         bigint UNIQUE NOT NULL,
    primary key (id)
);

ALTER TABLE public.password_reset_tokens
    ADD FOREIGN KEY (user_id) references public.users ("id");
