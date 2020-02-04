CREATE TABLE public.image (
	id serial NOT NULL,
	room_id int4 NOT NULL,
	image bytea NULL,
	image_name text NOT NULL,
	CONSTRAINT image_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_comment (
	id serial NOT NULL,
	user_name text NULL,
	room_id int4 NOT NULL,
	"comment" text NULL,
	rate int8 NOT NULL DEFAULT 0,
	CONSTRAINT user_comment_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.user_comment OWNER TO postgres;
GRANT ALL ON TABLE public.user_comment TO postgres;
