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

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (created_at, is_active, last_seen, updated_at, deleted_at, id, email, first_name, last_name, password, phone, phone2) FROM stdin;
\.


--
-- Data for Name: password_reset_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.password_reset_token (token, expiration_time, id, user_id) FROM stdin;
\.


--
-- Data for Name: pets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pets (active, adoption_date, is_available, months_age, visualizations, years_age, created_at, deleted_at, id, updated_at, user_id, breed, color, name, description, gender, health_condition, size, type, characteristics) FROM stdin;
\.


--
-- Data for Name: pet_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet_address (id, pet_id, zipcode, city, number, state, neighborhood, street, complement) FROM stdin;
\.


--
-- Data for Name: pet_picture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet_picture (id, pet_id, size, file_name, img_url, type) FROM stdin;
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
\.


--
-- Data for Name: user_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_address (id, user_id, zipcode, city, number, state, neighborhood, street, complement) FROM stdin;
\.


--
-- Data for Name: user_favorite_pets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_favorite_pets (id, pet_id, user_id) FROM stdin;
\.


--
-- Data for Name: user_picture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_picture (id, size, user_id, file_name, img_url, type) FROM stdin;
\.


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_roles (role_id, user_id) FROM stdin;
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
-- PostgreSQL database dump complete
--

