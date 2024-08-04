--
-- PostgreSQL database dump
--

-- Dumped from database version 12.19 (Debian 12.19-1.pgdg120+1)
-- Dumped by pg_dump version 12.19 (Debian 12.19-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: password_reset_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.password_reset_token (
    id bigint NOT NULL,
    expiration_time timestamp(6) with time zone NOT NULL,
    token character varying(6) NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.password_reset_token OWNER TO postgres;

--
-- Name: pet_address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pet_address (
    id bigint NOT NULL,
    city character varying(50) NOT NULL,
    complement character varying(255),
    neighborhood character varying(75) NOT NULL,
    number character varying(50),
    state character varying(50) NOT NULL,
    street character varying(100) NOT NULL,
    zipcode character varying(8) NOT NULL,
    pet_id bigint NOT NULL
);


ALTER TABLE public.pet_address OWNER TO postgres;

--
-- Name: pet_picture; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pet_picture (
    id bigint NOT NULL,
    file_name character varying(255),
    img_url character varying(255),
    size bigint NOT NULL,
    type character varying(255),
    pet_id bigint NOT NULL
);


ALTER TABLE public.pet_picture OWNER TO postgres;

--
-- Name: pets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pets (
    id bigint NOT NULL,
    active boolean,
    adoption_date date,
    breed character varying(100) NOT NULL,
    characteristics text NOT NULL,
    color character varying(100),
    created_at timestamp(6) without time zone,
    deleted_at timestamp(6) without time zone,
    description character varying(255),
    gender character varying(255),
    health_condition character varying(255),
    is_available boolean,
    months_age integer NOT NULL,
    name character varying(100) NOT NULL,
    size character varying(255),
    type character varying(255),
    updated_at timestamp(6) without time zone,
    visualizations integer,
    years_age integer NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT pets_gender_check CHECK (((gender)::text = ANY ((ARRAY['FEMALE'::character varying, 'MALE'::character varying])::text[]))),
    CONSTRAINT pets_health_condition_check CHECK (((health_condition)::text = ANY ((ARRAY['CHRONIC_DISEASE'::character varying, 'INJURED'::character varying, 'SICK'::character varying, 'PREGNANT'::character varying, 'HEALTHY'::character varying])::text[]))),
    CONSTRAINT pets_size_check CHECK (((size)::text = ANY ((ARRAY['TINY'::character varying, 'SMALL'::character varying, 'MEDIUM'::character varying, 'LARGE'::character varying])::text[]))),
    CONSTRAINT pets_type_check CHECK (((type)::text = ANY ((ARRAY['DOG'::character varying, 'CAT'::character varying, 'BIRD'::character varying])::text[])))
);


ALTER TABLE public.pets OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: seq_address; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_address
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_address OWNER TO postgres;

--
-- Name: seq_pass_reset_token; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pass_reset_token
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pass_reset_token OWNER TO postgres;

--
-- Name: seq_pet; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pet
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pet OWNER TO postgres;

--
-- Name: seq_picture; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_picture
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_picture OWNER TO postgres;

--
-- Name: seq_role; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_role
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_role OWNER TO postgres;

--
-- Name: seq_user; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_user OWNER TO postgres;

--
-- Name: seq_user_favorite_pets; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_user_favorite_pets
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_user_favorite_pets OWNER TO postgres;

--
-- Name: user_address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_address (
    id bigint NOT NULL,
    city character varying(50) NOT NULL,
    complement character varying(255),
    neighborhood character varying(75) NOT NULL,
    number character varying(50),
    state character varying(50) NOT NULL,
    street character varying(100) NOT NULL,
    zipcode character varying(8) NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_address OWNER TO postgres;

--
-- Name: user_favorite_pets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_favorite_pets (
    id bigint NOT NULL,
    pet_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_favorite_pets OWNER TO postgres;

--
-- Name: user_picture; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_picture (
    id bigint NOT NULL,
    file_name character varying(255),
    img_url character varying(255),
    size bigint NOT NULL,
    type character varying(255),
    user_id bigint NOT NULL
);


ALTER TABLE public.user_picture OWNER TO postgres;

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    created_at date,
    deleted_at timestamp(6) without time zone,
    email character varying(255) NOT NULL,
    first_name character varying(255),
    is_active boolean,
    last_name character varying(255),
    last_seen date,
    password character varying(255) NOT NULL,
    phone character varying(255),
    phone2 character varying(255),
    updated_at date
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: password_reset_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.password_reset_token (id, expiration_time, token, user_id) FROM stdin;
\.


--
-- Data for Name: pet_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet_address (id, city, complement, neighborhood, number, state, street, zipcode, pet_id) FROM stdin;
\.


--
-- Data for Name: pet_picture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet_picture (id, file_name, img_url, size, type, pet_id) FROM stdin;
\.


--
-- Data for Name: pets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pets (id, active, adoption_date, breed, characteristics, color, created_at, deleted_at, description, gender, health_condition, is_available, months_age, name, size, type, updated_at, visualizations, years_age, user_id) FROM stdin;
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
\.


--
-- Data for Name: user_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_address (id, city, complement, neighborhood, number, state, street, zipcode, user_id) FROM stdin;
\.


--
-- Data for Name: user_favorite_pets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_favorite_pets (id, pet_id, user_id) FROM stdin;
\.


--
-- Data for Name: user_picture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_picture (id, file_name, img_url, size, type, user_id) FROM stdin;
\.


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_roles (user_id, role_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, created_at, deleted_at, email, first_name, is_active, last_name, last_seen, password, phone, phone2, updated_at) FROM stdin;
\.


--
-- Name: seq_address; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_address', 1, false);


--
-- Name: seq_pass_reset_token; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pass_reset_token', 1, false);


--
-- Name: seq_pet; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pet', 1, false);


--
-- Name: seq_picture; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_picture', 1, false);


--
-- Name: seq_role; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_role', 1, false);


--
-- Name: seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_user', 1, false);


--
-- Name: seq_user_favorite_pets; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_user_favorite_pets', 1, false);


--
-- Name: password_reset_token password_reset_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_token
    ADD CONSTRAINT password_reset_token_pkey PRIMARY KEY (id);


--
-- Name: pet_address pet_address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_address
    ADD CONSTRAINT pet_address_pkey PRIMARY KEY (id);


--
-- Name: pet_picture pet_picture_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_picture
    ADD CONSTRAINT pet_picture_pkey PRIMARY KEY (id);


--
-- Name: pets pets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pets
    ADD CONSTRAINT pets_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: pet_address uk_2vv83ch9q500hsmi6cvgi99q0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_address
    ADD CONSTRAINT uk_2vv83ch9q500hsmi6cvgi99q0 UNIQUE (pet_id);


--
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: password_reset_token uk_f90ivichjaokvmovxpnlm5nin; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_token
    ADD CONSTRAINT uk_f90ivichjaokvmovxpnlm5nin UNIQUE (user_id);


--
-- Name: password_reset_token uk_g0guo4k8krgpwuagos61oc06j; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_token
    ADD CONSTRAINT uk_g0guo4k8krgpwuagos61oc06j UNIQUE (token);


--
-- Name: user_address uk_kfu0161nvirkey6fwd6orucv7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT uk_kfu0161nvirkey6fwd6orucv7 UNIQUE (user_id);


--
-- Name: user_address user_address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_address_pkey PRIMARY KEY (id);


--
-- Name: user_favorite_pets user_favorite_pets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_favorite_pets
    ADD CONSTRAINT user_favorite_pets_pkey PRIMARY KEY (id);


--
-- Name: user_picture user_picture_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_picture
    ADD CONSTRAINT user_picture_pkey PRIMARY KEY (id);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: pet_address pet_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_address
    ADD CONSTRAINT pet_fk FOREIGN KEY (pet_id) REFERENCES public.pets(id);


--
-- Name: pet_picture pet_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_picture
    ADD CONSTRAINT pet_fk FOREIGN KEY (pet_id) REFERENCES public.pets(id);


--
-- Name: user_favorite_pets pet_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_favorite_pets
    ADD CONSTRAINT pet_fk FOREIGN KEY (pet_id) REFERENCES public.users(id);


--
-- Name: user_roles role_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- Name: password_reset_token user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_token
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: pets user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pets
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: user_address user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: user_favorite_pets user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_favorite_pets
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: user_picture user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_picture
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: user_roles user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

