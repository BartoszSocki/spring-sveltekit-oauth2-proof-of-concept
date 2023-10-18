create database resource_server_db;

\c resource_server_db;

create schema resource_server;
create user resource_server with password 'Password1!';

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- ok
create table if not exists resource_server.user (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	email text not null unique
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- ok
create table if not exists resource_server.product_details (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	name text not null,
	description text null,
	image_url text null,

	price numeric(10, 2) not null,
	currency text not null
);

-- ok
create table if not exists resource_server.product(
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	seller_id int not null,
	product_details_id int not null,
	quantity int not null,

	constraint fk_seller foreign key (seller_id) references resource_server.user(id),
	constraint fk_product_details foreign key (product_details_id) references resource_server.product_details(id)
);

-- ok
create table if not exists resource_server.concrete_product (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	product_details_id int not null,
	concrete_product_status text not null,

	constraint fk_product_details foreign key (product_details_id) references resource_server.product_details(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- ok
create table if not exists resource_server.category (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	name text not null unique
);

-- ok
create table if not exists resource_server.tag (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	name text not null unique
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- ok
create table if not exists resource_server.product_details_tag (
	id int generated always as identity primary key,
	product_details_id int not null,
	tag_id int not null,

	constraint fk_product_details foreign key (product_details_id) references resource_server.product_details(id),
	constraint fk_tag foreign key (tag_id) references resource_server.tag(id)
);

-- ok
create table if not exists resource_server.product_details_category (
	id int generated always as identity primary key,
	product_details_id int not null,
	category_id int not null,

	constraint fk_product_details foreign key (product_details_id) references resource_server.product_details(id),
	constraint fk_category foreign key (category_id) references resource_server.category(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- ok
create table if not exists resource_server.review (
	id int generated always as identity primary key,
	created_at timestamp null default now(),
	last_changed_at timestamp null,

	five_star_score int not null,
	description text null
);

-- ok
create table if not exists resource_server.product_review (
	review_id int not null primary key,
	concrete_product_id int not null,

	constraint fk_concrete_product foreign key (concrete_product_id) references resource_server.concrete_product(id),
	constraint fk_review foreign key (review_id) references resource_server.review(id)
);

-- ok
create table if not exists resource_server.user_review (
	review_id int not null primary key,
	user_id int not null,

	constraint fk_user foreign key (user_id) references resource_server.user(id),
	constraint fk_review foreign key (review_id) references resource_server.review(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- ok
create table if not exists resource_server.order (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	concrete_product_id int not null,
	seller_id int not null,
	buyer_id int not null,
	address text not null,
	status text not null,

	price numeric(10, 2) not null,
	currency text not null,

	constraint fk_seller foreign key (seller_id) references resource_server.user(id),
	constraint fk_buyer foreign key (buyer_id) references resource_server.user(id),
	constraint fk_concrete_product foreign key (concrete_product_id) references resource_server.concrete_product(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

grant all privileges on schema resource_server to resource_server;
grant all privileges on database resource_server_db to resource_server;
grant all privileges on all tables in schema resource_server to resource_server;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
