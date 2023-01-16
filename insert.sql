CREATE OR REPLACE FUNCTION get_random_string() RETURNS text LANGUAGE SQL AS $$
	SELECT STRING_AGG (SUBSTR ( 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', CEIL (RANDOM() * 52)::integer, 1), '')
	FROM GENERATE_SERIES(1, 10)
$$;

CREATE OR REPLACE FUNCTION insert_record(i NUMERIC) RETURNS VOID LANGUAGE PLPGSQL AS $$
	DECLARE avatar_url TEXT='https://i.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U';
	DECLARE full_name TEXT= INITCAP(get_random_string());
	DECLARE city TEXT='Ho Chi Minh';
	DECLARE country TEXT='Vietnam';

	DECLARE email TEXT= LOWER(CONCAT(full_name, '@gmail.com'));
	DECLARE phone BIGINT=CAST(1000000000 + FLOOR(RANDOM() * 9000000000) AS TEXT);
	DECLARE facebook_url TEXT='https://www.facebook.com/zuck/';
	DECLARE instagram_url TEXT='https://www.instagram.com/zuck/';
	DECLARE tiktok_url TEXT='https://www.tiktok.com/@datvilla94';
	DECLARE youtube_url TEXT='https://www.youtube.com/@atvilla4233';

	BEGIN
		INSERT INTO public.customer
			(customer_type, id, avatar_url, city, country, email, full_name, phone, facebook_url, instagram_url, tiktok_url, youtube_url)		
			VALUES ('influencer', i, avatar_url, city, country, email, full_name, phone, facebook_url, instagram_url, tiktok_url, youtube_url);
	END
$$;

do $$
begin
	for i in 1..1000000 loop
		PERFORM insert_record(i);
	end loop;
end; $$