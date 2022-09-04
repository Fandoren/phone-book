DROP TABLE IF EXISTS public.phone_book;
CREATE TABLE public.phone_book
(
    id bigint NOT NULL,
    name text,
    phone text,
    update timestamp without time zone,
    actualized timestamp without time zone,
    PRIMARY KEY (id)
);

CREATE SEQUENCE hibernate_sequence START 1;
