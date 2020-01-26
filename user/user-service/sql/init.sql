create table sys_user (
    id bigint not null primary key,
    username varchar(75) not null,
    password varchar(75) not null,
    fullName varchar(75) not null,
    phone varchar(75) null,
    email varchar(75) null,
    openId bigint null,
    unionId bigint null,
    portraitId bigint null,
    jobTitle varchar(75) null,
    address varchar(75) null,
    status bigint null,
    createDate datetime null,
    modifiedDate datetime null
);

create index username on sys_user (username);
