create database resource_server_db;

\c resource_server_db;

create schema resource_server;
create user resource_server with password 'Password1!';

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.product(
	id int generated always as identity primary key,
	created_at timestamp not null default now(),
	last_changed_at timestamp null,

	last_sale_at timestamp null,
	seller int not null,
	product_details int not null,
	name text not null,
	quantity int not null,

	price int not null,
	currency text not null
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.product_details (
	id int generated always as identity primary key,
	created_at timestamp not null default now(),
	last_changed_at timestamp null,

	product_id int not null,
	description text null,
	five_star_score int not null,

	constraint fk_product foreign key (product_id) references resource_server.product(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.category (
	id int generated always as identity primary key,
	name text not null
);

create table if not exists resource_server.tag (
	id int generated always as identity primary key,
	name text not null
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.product_details_tag (
	id int generated always as identity primary key,
	product_details_id int not null,
	tag_id int not null,

	constraint fk_product_details foreign key (product_details_id) references resource_server.product_details(id),
	constraint fk_tag foreign key (tag_id) references resource_server.tag(id)
);

create table if not exists resource_server.product_details_category (
	id int generated always as identity primary key,
	product_details_id int not null,
	category_id int not null,

	constraint fk_product_details foreign key (product_details_id) references resource_server.product_details(id),
	constraint fk_category foreign key (category_id) references resource_server.category(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

/* create table if not exists resource_server.user_role ( */
/* 	id serial primary key, */
/* 	user_id int not null, */
/* 	role_id int not null, */ 

/* 	constraint fk_user_id foreign key (user_id) references resource_server.users(id), */
/* 	constraint fk_role_id foreign key (role_id) references resource_server.roles(id) */
/* ); */

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

grant all privileges on schema resource_server to resource_server;
grant all privileges on database resource_server_db to resource_server;
grant all privileges on all tables in schema resource_server to resource_server;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
