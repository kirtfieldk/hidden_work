create table if not exists repositories(
    repository_id integer  not null unique,
    project_id integer references project(project_id),
    repository  varchar(255),
    clone_url  varchar(350),
    primary key (repository_id, project_id)
);