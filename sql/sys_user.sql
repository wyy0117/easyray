create table sys_user (
    id bigint not null primary key,
    user_id bigint null,
    username varchar(75) not null,
    password varchar(75) not null,
    full_name varchar(75) not null,
    phone varchar(75) null,
    email varchar(75) null,
    open_id bigint null,
    union_id bigint null,
    portrait_id bigint null,
    job_title varchar(75) null,
    address varchar(75) null,
    status bigint null,
    create_date datetime null,
    modified_date datetime null
);

create index username on sys_user (username);
