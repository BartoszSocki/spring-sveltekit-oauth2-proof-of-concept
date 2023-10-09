create database authorization_server_db;

\c authorization_server_db;

create schema authorization_server;
create user authorization_server with password 'Password1!';

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists authorization_server.users (
	id serial primary key,
	email text not null unique,
	password text null
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists authorization_server.roles (
	id serial primary key,
	name text not null unique
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists authorization_server.user_role (
	id serial primary key,
	user_id int not null,
	role_id int not null, 

	constraint fk_user_id foreign key (user_id) references authorization_server.users(id),
	constraint fk_role_id foreign key (role_id) references authorization_server.roles(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

grant all privileges on schema authorization_server to authorization_server;
grant all privileges on database authorization_server_db to authorization_server;
grant all privileges on all tables in schema authorization_server to authorization_server;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

insert into authorization_server.users (id, email, password) values (1, 'user', 'password');
insert into authorization_server.roles (id, name) values (1, 'USER');
insert into authorization_server.user_role (user_id, role_id) values (1, 1);

