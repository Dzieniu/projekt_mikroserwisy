
INSERT INTO public.users(
	id, email, first_name, last_name, password, role, username)
	VALUES
	(nextval('hibernate_sequence'),'dzieniu@gmail.com','Dawid','Dzien','$2a$10$P.BskjFEuFoftqotDbWts.TnquihcYq.Tex.npt.bIAnTkkzCk6HC',0,'admin'),
	(nextval('hibernate_sequence'),'user1@gmail.com','Damian','Kawka','$2a$10$Mpn4Zd9YBbKPeSLsEA40z.wRzIQAr615AZlvu.XZZXTLbSvV5q0j2',1,'user1'),
	(nextval('hibernate_sequence'),'user2@gmail.com','Bartek','Zabek','$2a$10$zh3d1Lhy6XyfNULqQ1Aaru1SbpHWhF5bWmSTwJCLlKKNJBtJJg00e',1,'user2'),
	(nextval('hibernate_sequence'),'user3@gmail.com','Jacek','Korab','$2a$10$O4K67CB3vMYz0Tya1/HfHOhBfn6YbGmjv/7Gcu/Cb5oLvmt/jOg1a',1,'user3'),
	(nextval('hibernate_sequence'),'user4@gmail.com','Tomasz','Waberski','$2a$10$WAd/mSyzXQ2vgDSeERiUwOvwDFnzvt2uB6hn0US7XbobqYMqcd2NS',1,'user4');