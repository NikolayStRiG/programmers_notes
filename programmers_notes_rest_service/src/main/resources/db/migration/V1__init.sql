create sequence programmers_notes.hibernate_sequence
increment 1
start 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

create table programmers_notes.INFO_RESOURCE_ENTITY (
    "id" bigint primary key not null,
    "name" varchar(255) unique not null,
    "address" varchar (255) unique not null,
    "description" text
);