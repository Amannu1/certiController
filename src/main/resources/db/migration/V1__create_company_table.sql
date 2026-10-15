create table tbcompany(
    id bigint primary key auto_increment,
    name varchar(100) not null,
    active boolean not null default false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

insert into tbcompany(NAME, ACTIVE, CREATED_AT) VALUES ('Company 1', false, now());
insert into tbcompany(NAME, ACTIVE, CREATED_AT) VALUES ('Company 2', true, now());