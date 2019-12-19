DELETE FROM course;
INSERT INTO course(id, active, teacher, title, hours, level) VALUES (1, false, 1,'Title 1', 10, 0);
INSERT INTO course(id, active, teacher, title, hours, level) VALUES (2, false, 1,'Title 3', 20, 1);
INSERT INTO course(id, active, teacher, title, hours, level) VALUES (3, false, 2,'Title 5', 30, 2);
INSERT INTO course(id, active, teacher, title, hours, level) VALUES (4, false, 4,'Title 2', 40, 2);
INSERT INTO course(id, active, teacher, title, hours, level) VALUES (5, false, 4,'Title 4', 50, 0);
COMMIT;
	