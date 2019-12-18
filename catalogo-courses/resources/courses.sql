DELETE FROM course;
INSERT INTO course(id, active, id_teacher, title, hours, level) VALUES (1, true, 1,'Title 1', 10, 1);
INSERT INTO course(id, active, id_teacher, title, hours, level) VALUES (2, true, 1,'Title 3', 20, 2);
INSERT INTO course(id, active, id_teacher, title, hours, level) VALUES (3, true, 2,'Title 4', 30, 3);
INSERT INTO course(id, active, id_teacher, title, hours, level) VALUES (4, true, 4,'Title 5', 40, 3);
INSERT INTO course(id, active, id_teacher, title, hours, level) VALUES (5, true, 4,'Title 2', 50, 1);
COMMIT;
	