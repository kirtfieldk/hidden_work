create table if not exists file(
    file_id integer not null unique,
    file_name varchar(255),
    file_content varchar(25000),
    uploaded timestamp,
    repository_id integer references repositories(repository_id),
    project_id integer references project(project_id),
    primary key (file_id, repository_id)
);