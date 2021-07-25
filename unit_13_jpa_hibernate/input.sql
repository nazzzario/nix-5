insert into corses(name)
values ('Java Enterprise'),
       ('Python Beginner');

insert into groups (group_name, course_id)
values ('CS 231', 1),
       ('AF 101', 2);

insert into students(first_name, last_name, group_id)
values ('Mary', 'Potter', 1),
       ('Jhon', 'Cena', 1),
       ('Igor', 'Pink', 1),
       ('Harry', 'Potter', 2),
       ('Alex', 'Mayson', 2),
       ('Victor', 'Morozov', 2);

insert into teachers(email, first_name, last_name, course_id)
values ('robert_martin@mail.com', 'Robert', 'Martin', 1),
       ('ivan@mail.com', 'Ivan', 'Ivanov', 2);

insert into topics (topic_name, course_id, teacher_id)
values ('Spring Basics', 1, 1),
       ('Python History', 2, 2);

insert into lessons (date_time, name, topic_id)
values ('2021-07-26 14:00:00', 'Starting spring configuration', 1),
       ('2021-07-30 15:00:00', 'Python code conversion', 2);

