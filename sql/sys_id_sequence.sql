create table sys_id_sequence (
    id bigint not null primary key,
    entity_name varchar(75) not null,
    value bigint not null
);

create index entity_name on sys_id_sequence (entity_name);
