create table locations
(
    id   serial not null
        constraint locations_pk
            primary key,
    name text   not null
);

alter table locations
    owner to postgres;

create unique index locations_name_uindex
    on locations (name);

create table routes
(
    id      serial not null
        constraint routs_pk
            primary key,
    from_id integer
        constraint routs_locations_id_fk
            references locations,
    to_id   serial not null
        constraint routs_locations_id_fk_2
            references locations,
    cost    integer
);

alter table routes
    owner to postgres;

create table problems
(
    id      serial not null
        constraint problems_pk
            primary key,
    from_id integer
        constraint problems_locations_id_fk
            references locations,
    to_id   integer
        constraint problems_locations_id_fk_2
            references locations
);

alter table problems
    owner to postgres;

create table solutions
(
    problem_id integer not null
        constraint table_name_pk
            primary key
        constraint table_name_problems_id_fk
            references problems,
    cost       integer
);

alter table solutions
    owner to postgres;

