insert into courses (course_name)
values ('Java'),
       ('HTML'),
       ('C++');

insert into groups (group_name, course_id)
values ('Java 101', 1),
       ('HTML 101', 2),
       ('C++ 101', 3);

INSERT INTO students (email, first_name, last_name, group_id)
VALUES ('sem@quispedeSuspendisse.net', 'Macon', 'Walters', 2),
       ('Mauris.ut.quam@feugiatplaceratvelit.co.uk', 'Clarke', 'Rivas', 1),
       ('velit.eu@lectusconvallisest.co.uk', 'Athena', 'Mcneil', 3),
       ('facilisis.vitae.orci@seddolor.co.uk', 'Abra', 'Harrison', 1),
       ('facilisis.facilisis.magna@elementumsemvitae.ca', 'Bruno', 'Howell', 3),
       ('fermentum.arcu.Vestibulum@nibhDonecest.org', 'Jasper', 'Mccarthy', 1),
       ('purus.accumsan@Quisque.co.uk', 'Ashely', 'Benjamin', 2),
       ('leo@massarutrummagna.com', 'Michelle', 'Goff', 2),
       ('fringilla@et.com', 'Francesca', 'Burke', 1),
       ('velit.Cras.lorem@velmauris.net', 'Micah', 'Rosario', 1);

INSERT INTO teachers (email, first_name, last_name)
VALUES ('lorem.vitae@inceptoshymenaeosMauris.edu', 'Constance', 'Ortiz'),
       ('neque@lorem.ca', 'Camille', 'Becker'),
       ('consequat@sem.edu', 'Jada', 'Hahn');

INSERT INTO topics (topic_name, teacher_id)
VALUES ('leo, in lobortis tellus justo', 1),
       ('Sed nulla ante, iaculis nec,', 2),
       ('Cras eu tellus eu augue', 3);

INSERT INTO lessons (date_time, name, topic_id, group_id)
VALUES ('2021-12-26 19:40:44', 'Mauris', 1, 1),
       ('2021-01-08 06:32:53', 'dolor', 2, 2),
       ('2021-03-19 12:17:35', 'non,', 3, 3);
