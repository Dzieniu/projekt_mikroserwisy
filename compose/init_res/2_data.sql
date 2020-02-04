 INSERT INTO public."Rooms" ("Id", "Description", "ReservationPrize")
 VALUES (1,'First room',11),
 (2,'Second room room',22),
 (3,'Third room',33),
 (4,'Fourth room',44),
 (5,'Fifth room',55),
 (6,'Sixth room',66);



INSERT INTO public."Reservations" ("ID", "UserId", "RoomId", "ReservationDate")
 VALUES (1,1,1,'2020-03-01'),
 (2,1,1,'2020-03-01'),
 (3,1,1,'2020-04-01'),
 (4,5,2,'2020-01-01'),
 (5,3,2,'2020-02-03'),
 (6,2,3,'2020-05-11'),
 (7,1,4,'2020-01-01');