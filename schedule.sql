create table member (
    id bigint auto_increment primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    username varchar(255) not null,
    created_at datetime(6),
    modified_at datetime(6)
);

create table plan (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    target_date date not null,
    contents longtext,
    member_id bigint,
    created_at datetime(6),
    modified_at datetime(6),
    constraint fk_plan_member foreign key (member_id) references member(id)
);

create table comment (
    id bigint auto_increment primary key,
    contents longtext not null,
    member_id bigint,
    plan_id bigint,
    created_at datetime(6),
    modified_at datetime(6),
    constraint fk_comment_member foreign key (member_id) references member(id),
    constraint fk_comment_plan foreign key (plan_id) references plan(id)
);
