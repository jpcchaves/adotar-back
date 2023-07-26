CREATE TABLE public.user_saved_pets
(
    user_id bigint NOT NULL,
    pet_id  bigint NOT NULL
);

ALTER TABLE public.user_saved_pets
    ADD FOREIGN KEY (user_id) REFERENCES public.users ("id");

ALTER TABLE public.user_saved_pets
    ADD FOREIGN KEY (pet_id) REFERENCES public.pets ("id");

