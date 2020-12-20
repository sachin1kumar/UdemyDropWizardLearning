create table users(
    id bigint primary key,
    username varchar(255),
    password varchar(255)
);

create table bookmarks(
    id bigint primary key,
    name varchar(255),
    url varchar(1024),
    description varchar(2048),
    user_id bigint,
    foreign key (id) references users(id)
);