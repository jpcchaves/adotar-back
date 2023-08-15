CREATE TABLE public.user_saved_pets
(
    id      bigserial NOT NULL,
    pet_id  int8      NOT NULL,
    user_id int8      NOT NULL,
    CONSTRAINT user_saved_pets_pkey PRIMARY KEY (id, user_id, pet_id),
    CONSTRAINT fk6l1ff1qs6ie8pgq1ccw7cwj1h FOREIGN KEY (pet_id) REFERENCES public.pets (id),
    CONSTRAINT fknpt573nrlwws6oj8md4ycn8m4 FOREIGN KEY (user_id) REFERENCES public.users (id)
);