CREATE TABLE phonebook
(
  id serial NOT NULL,
  name character varying NOT NULL,
  phone_number character varying NOT NULL,
  CONSTRAINT phonebook_pkey PRIMARY KEY (id)
);
