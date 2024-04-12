toc.dat                                                                                             0000600 0004000 0002000 00000030157 14605677560 0014465 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP       +                |           java-sprint    16.1    16.1 )    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         �           1262    49251    java-sprint    DATABASE     �   CREATE DATABASE "java-sprint" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';
    DROP DATABASE "java-sprint";
                postgres    false         �            1259    57391    doctor_patient    TABLE     h   CREATE TABLE public.doctor_patient (
    doctor_id integer NOT NULL,
    patient_id integer NOT NULL
);
 "   DROP TABLE public.doctor_patient;
       public         heap    postgres    false         �            1259    57380    health_data    TABLE     �   CREATE TABLE public.health_data (
    id integer NOT NULL,
    user_id integer NOT NULL,
    weight numeric(5,2) NOT NULL,
    height numeric(5,2) NOT NULL,
    steps integer NOT NULL,
    heart_rate integer NOT NULL,
    date date NOT NULL
);
    DROP TABLE public.health_data;
       public         heap    postgres    false         �            1259    57379    health_data_id_seq    SEQUENCE     �   CREATE SEQUENCE public.health_data_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.health_data_id_seq;
       public          postgres    false    222         �           0    0    health_data_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.health_data_id_seq OWNED BY public.health_data.id;
          public          postgres    false    221         �            1259    57368    medicine_reminders    TABLE     (  CREATE TABLE public.medicine_reminders (
    id integer NOT NULL,
    user_id integer NOT NULL,
    medicine_name character varying(100) NOT NULL,
    dosage character varying(50) NOT NULL,
    schedule character varying(100) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);
 &   DROP TABLE public.medicine_reminders;
       public         heap    postgres    false         �            1259    57367    medicine_reminders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.medicine_reminders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.medicine_reminders_id_seq;
       public          postgres    false    220         �           0    0    medicine_reminders_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.medicine_reminders_id_seq OWNED BY public.medicine_reminders.id;
          public          postgres    false    219         �            1259    57354    recommendations    TABLE     �   CREATE TABLE public.recommendations (
    id integer NOT NULL,
    user_id integer NOT NULL,
    recommendation_text text NOT NULL,
    date date NOT NULL
);
 #   DROP TABLE public.recommendations;
       public         heap    postgres    false         �            1259    57353    recommendations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.recommendations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.recommendations_id_seq;
       public          postgres    false    218         �           0    0    recommendations_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.recommendations_id_seq OWNED BY public.recommendations.id;
          public          postgres    false    217         �            1259    57345    users    TABLE       CREATE TABLE public.users (
    id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    is_doctor boolean NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false         �            1259    57344    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216         �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215         0           2604    57383    health_data id    DEFAULT     p   ALTER TABLE ONLY public.health_data ALTER COLUMN id SET DEFAULT nextval('public.health_data_id_seq'::regclass);
 =   ALTER TABLE public.health_data ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222         /           2604    57371    medicine_reminders id    DEFAULT     ~   ALTER TABLE ONLY public.medicine_reminders ALTER COLUMN id SET DEFAULT nextval('public.medicine_reminders_id_seq'::regclass);
 D   ALTER TABLE public.medicine_reminders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220         .           2604    57357    recommendations id    DEFAULT     x   ALTER TABLE ONLY public.recommendations ALTER COLUMN id SET DEFAULT nextval('public.recommendations_id_seq'::regclass);
 A   ALTER TABLE public.recommendations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218         -           2604    57348    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216         �          0    57391    doctor_patient 
   TABLE DATA           ?   COPY public.doctor_patient (doctor_id, patient_id) FROM stdin;
    public          postgres    false    223       4825.dat �          0    57380    health_data 
   TABLE DATA           [   COPY public.health_data (id, user_id, weight, height, steps, heart_rate, date) FROM stdin;
    public          postgres    false    222       4824.dat �          0    57368    medicine_reminders 
   TABLE DATA           p   COPY public.medicine_reminders (id, user_id, medicine_name, dosage, schedule, start_date, end_date) FROM stdin;
    public          postgres    false    220       4822.dat �          0    57354    recommendations 
   TABLE DATA           Q   COPY public.recommendations (id, user_id, recommendation_text, date) FROM stdin;
    public          postgres    false    218       4820.dat �          0    57345    users 
   TABLE DATA           V   COPY public.users (id, first_name, last_name, email, password, is_doctor) FROM stdin;
    public          postgres    false    216       4818.dat �           0    0    health_data_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.health_data_id_seq', 1, false);
          public          postgres    false    221         �           0    0    medicine_reminders_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.medicine_reminders_id_seq', 1, false);
          public          postgres    false    219         �           0    0    recommendations_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.recommendations_id_seq', 1, false);
          public          postgres    false    217         �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public          postgres    false    215         <           2606    57395 "   doctor_patient doctor_patient_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_pkey PRIMARY KEY (doctor_id, patient_id);
 L   ALTER TABLE ONLY public.doctor_patient DROP CONSTRAINT doctor_patient_pkey;
       public            postgres    false    223    223         :           2606    57385    health_data health_data_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.health_data
    ADD CONSTRAINT health_data_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.health_data DROP CONSTRAINT health_data_pkey;
       public            postgres    false    222         8           2606    57373 *   medicine_reminders medicine_reminders_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.medicine_reminders
    ADD CONSTRAINT medicine_reminders_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.medicine_reminders DROP CONSTRAINT medicine_reminders_pkey;
       public            postgres    false    220         6           2606    57361 $   recommendations recommendations_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.recommendations
    ADD CONSTRAINT recommendations_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.recommendations DROP CONSTRAINT recommendations_pkey;
       public            postgres    false    218         2           2606    57352    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    216         4           2606    57350    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216         @           2606    57396 ,   doctor_patient doctor_patient_doctor_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_doctor_id_fkey FOREIGN KEY (doctor_id) REFERENCES public.users(id);
 V   ALTER TABLE ONLY public.doctor_patient DROP CONSTRAINT doctor_patient_doctor_id_fkey;
       public          postgres    false    4660    223    216         A           2606    57401 -   doctor_patient doctor_patient_patient_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_patient_id_fkey FOREIGN KEY (patient_id) REFERENCES public.users(id);
 W   ALTER TABLE ONLY public.doctor_patient DROP CONSTRAINT doctor_patient_patient_id_fkey;
       public          postgres    false    4660    223    216         ?           2606    57386 $   health_data health_data_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.health_data
    ADD CONSTRAINT health_data_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.health_data DROP CONSTRAINT health_data_user_id_fkey;
       public          postgres    false    4660    222    216         >           2606    57374 2   medicine_reminders medicine_reminders_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.medicine_reminders
    ADD CONSTRAINT medicine_reminders_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 \   ALTER TABLE ONLY public.medicine_reminders DROP CONSTRAINT medicine_reminders_user_id_fkey;
       public          postgres    false    220    216    4660         =           2606    57362 ,   recommendations recommendations_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.recommendations
    ADD CONSTRAINT recommendations_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 V   ALTER TABLE ONLY public.recommendations DROP CONSTRAINT recommendations_user_id_fkey;
       public          postgres    false    4660    218    216                                                                                                                                                                                                                                                                                                                                                                                                                         4825.dat                                                                                            0000600 0004000 0002000 00000000005 14605677560 0014267 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4824.dat                                                                                            0000600 0004000 0002000 00000000005 14605677560 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4822.dat                                                                                            0000600 0004000 0002000 00000000005 14605677560 0014264 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4820.dat                                                                                            0000600 0004000 0002000 00000000005 14605677560 0014262 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4818.dat                                                                                            0000600 0004000 0002000 00000000005 14605677560 0014271 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           restore.sql                                                                                         0000600 0004000 0002000 00000023766 14605677560 0015422 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

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

DROP DATABASE "java-sprint";
--
-- Name: java-sprint; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "java-sprint" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';


ALTER DATABASE "java-sprint" OWNER TO postgres;

\connect -reuse-previous=on "dbname='java-sprint'"

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
-- Name: doctor_patient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctor_patient (
    doctor_id integer NOT NULL,
    patient_id integer NOT NULL
);


ALTER TABLE public.doctor_patient OWNER TO postgres;

--
-- Name: health_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.health_data (
    id integer NOT NULL,
    user_id integer NOT NULL,
    weight numeric(5,2) NOT NULL,
    height numeric(5,2) NOT NULL,
    steps integer NOT NULL,
    heart_rate integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.health_data OWNER TO postgres;

--
-- Name: health_data_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.health_data_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.health_data_id_seq OWNER TO postgres;

--
-- Name: health_data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.health_data_id_seq OWNED BY public.health_data.id;


--
-- Name: medicine_reminders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medicine_reminders (
    id integer NOT NULL,
    user_id integer NOT NULL,
    medicine_name character varying(100) NOT NULL,
    dosage character varying(50) NOT NULL,
    schedule character varying(100) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);


ALTER TABLE public.medicine_reminders OWNER TO postgres;

--
-- Name: medicine_reminders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.medicine_reminders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.medicine_reminders_id_seq OWNER TO postgres;

--
-- Name: medicine_reminders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.medicine_reminders_id_seq OWNED BY public.medicine_reminders.id;


--
-- Name: recommendations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recommendations (
    id integer NOT NULL,
    user_id integer NOT NULL,
    recommendation_text text NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.recommendations OWNER TO postgres;

--
-- Name: recommendations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recommendations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recommendations_id_seq OWNER TO postgres;

--
-- Name: recommendations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recommendations_id_seq OWNED BY public.recommendations.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    is_doctor boolean NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: health_data id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.health_data ALTER COLUMN id SET DEFAULT nextval('public.health_data_id_seq'::regclass);


--
-- Name: medicine_reminders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicine_reminders ALTER COLUMN id SET DEFAULT nextval('public.medicine_reminders_id_seq'::regclass);


--
-- Name: recommendations id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recommendations ALTER COLUMN id SET DEFAULT nextval('public.recommendations_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: doctor_patient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctor_patient (doctor_id, patient_id) FROM stdin;
\.
COPY public.doctor_patient (doctor_id, patient_id) FROM '$$PATH$$/4825.dat';

--
-- Data for Name: health_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.health_data (id, user_id, weight, height, steps, heart_rate, date) FROM stdin;
\.
COPY public.health_data (id, user_id, weight, height, steps, heart_rate, date) FROM '$$PATH$$/4824.dat';

--
-- Data for Name: medicine_reminders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.medicine_reminders (id, user_id, medicine_name, dosage, schedule, start_date, end_date) FROM stdin;
\.
COPY public.medicine_reminders (id, user_id, medicine_name, dosage, schedule, start_date, end_date) FROM '$$PATH$$/4822.dat';

--
-- Data for Name: recommendations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recommendations (id, user_id, recommendation_text, date) FROM stdin;
\.
COPY public.recommendations (id, user_id, recommendation_text, date) FROM '$$PATH$$/4820.dat';

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, first_name, last_name, email, password, is_doctor) FROM stdin;
\.
COPY public.users (id, first_name, last_name, email, password, is_doctor) FROM '$$PATH$$/4818.dat';

--
-- Name: health_data_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.health_data_id_seq', 1, false);


--
-- Name: medicine_reminders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.medicine_reminders_id_seq', 1, false);


--
-- Name: recommendations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recommendations_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: doctor_patient doctor_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_pkey PRIMARY KEY (doctor_id, patient_id);


--
-- Name: health_data health_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.health_data
    ADD CONSTRAINT health_data_pkey PRIMARY KEY (id);


--
-- Name: medicine_reminders medicine_reminders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicine_reminders
    ADD CONSTRAINT medicine_reminders_pkey PRIMARY KEY (id);


--
-- Name: recommendations recommendations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recommendations
    ADD CONSTRAINT recommendations_pkey PRIMARY KEY (id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: doctor_patient doctor_patient_doctor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_doctor_id_fkey FOREIGN KEY (doctor_id) REFERENCES public.users(id);


--
-- Name: doctor_patient doctor_patient_patient_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_patient_id_fkey FOREIGN KEY (patient_id) REFERENCES public.users(id);


--
-- Name: health_data health_data_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.health_data
    ADD CONSTRAINT health_data_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: medicine_reminders medicine_reminders_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicine_reminders
    ADD CONSTRAINT medicine_reminders_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: recommendations recommendations_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recommendations
    ADD CONSTRAINT recommendations_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          