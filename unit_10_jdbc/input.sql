insert into locations (name)
values ('gdansk'),
       ('bydgoszcz'),
       ('torun'),
       ('warszawa');

insert into routes (from_id, to_id, cost)
values (1, 2, 1),
       (1, 3, 3),
       (2, 1, 1),
       (2, 3, 1),
       (2, 4, 4),
       (3, 1, 3),
       (3, 2, 1),
       (3, 4, 1),
       (4, 2, 4),
       (4, 3, 1);

insert into problems(from_id, to_id)
values (1, 4),
       (2, 4);
