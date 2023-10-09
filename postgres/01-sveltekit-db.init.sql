create database sveltekit_db;

\c sveltekit_db;

create schema sveltekit;
create user sveltekit with password 'Password1!';

/* grant all privileges on all sequences in schema sveltekit to sveltekit; */
/* grant all privileges on all functions in schema sveltekit to sveltekit; */


/* grant usage on schema sveltekit to sveltekit; */
/* grant all on all tables in schema sveltekit to sveltekit; */
/* grant all on all sequences in schema sveltekit to sveltekit; */
/* grant all on all functions in schema sveltekit to sveltekit; */

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists sveltekit.users (
	id uuid not null primary key,
	email text not null,
	access_token text null,
	refresh_token text null,

	created_at timestamp,
	updated_at timestamp
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists sveltekit.sessions (
	id varchar(32) not null primary key,
	ip_address text,
	expiration_date timestamp with time zone,
	expiration_date_idle timestamp with time zone,
	user_id uuid not null,

	state text null,

	created_at timestamp,
	updated_at timestamp,

	constraint fk_user_id foreign key (user_id) references sveltekit.users(id) on delete cascade
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

insert into sveltekit.users (id, email) values ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'anonymous');

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

grant all privileges on schema sveltekit to sveltekit;
grant all privileges on database sveltekit_db to sveltekit;
grant all privileges on all tables in schema sveltekit to sveltekit;
