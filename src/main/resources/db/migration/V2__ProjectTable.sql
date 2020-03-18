create table if not exists project(
project_id integer primary key not null unique,
repository varchar(255),
description varchar(255)
);