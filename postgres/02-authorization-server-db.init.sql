create database authorization_server_db;

\c authorization_server_db;

create schema authorization_server;
create user authorization_server with password 'Password1!';

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists authorization_server.users (
-- 	id uuid generated always as identity not null primary key,
    id uuid default gen_random_uuid() primary key,
	email text not null unique,
	name text null,
	surname text null
);


create table if not exists authorization_server.user_account (
    id uuid default gen_random_uuid() primary key,
	user_id uuid not null,
	role text not null,
	password text null,
	account_provider text not null,

	is_password_supplied boolean default true,
	is_account_non_locked boolean default false,
    is_account_enabled boolean default false,

	constraint fk_user_id foreign key (user_id) references authorization_server.users(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

grant all privileges on schema authorization_server to authorization_server;
grant all privileges on database authorization_server_db to authorization_server;
grant all privileges on all tables in schema authorization_server to authorization_server;
