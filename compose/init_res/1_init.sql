-- Database: ReservationDB

-- DROP DATABASE "ReservationDB";
	
CREATE TABLE public."Rooms"
(
    "Id" integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "Description" text COLLATE pg_catalog."default",
    "ReservationPrize" integer NOT NULL,
    CONSTRAINT "PK_Rooms" PRIMARY KEY ("Id")
)

TABLESPACE pg_default;

ALTER TABLE public."Rooms"
    OWNER to postgres;
	
-- Table: public."Reservations"

-- DROP TABLE public."Reservations";

CREATE TABLE public."Reservations"
(
    "ID" integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "UserId" integer NOT NULL,
    "RoomId" integer NOT NULL,
    "ReservationDate" date NOT NULL,
    CONSTRAINT "PK_Reservations" PRIMARY KEY ("ID"),
    CONSTRAINT "FK_Reservations_Rooms_RoomId" FOREIGN KEY ("RoomId")
        REFERENCES public."Rooms" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public."Reservations"
    OWNER to postgres;

-- Index: IX_Reservations_RoomId

-- DROP INDEX public."IX_Reservations_RoomId";

CREATE INDEX "IX_Reservations_RoomId"
    ON public."Reservations" USING btree
    ("RoomId" ASC NULLS LAST)
    TABLESPACE pg_default;
	

