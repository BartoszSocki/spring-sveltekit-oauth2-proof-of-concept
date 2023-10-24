create database resource_server_db;

\c resource_server_db;

create schema resource_server;
create user resource_server with password 'Password1!';

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.user (
	id int generated by default as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	email text not null unique,

	user_money numeric(10, 2) not null, 
	currency text not null
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.category (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	name text not null unique
);

create table if not exists resource_server.tag (
	id int generated always as identity primary key,
	created_at timestamp null default now(),

	name text not null,
	value text not null
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.product_inventory (
	id int generated always as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	quantity int not null,
	products_bought int not null
);

create table if not exists resource_server.product_catalog (
	id int generated by default as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	name text not null,
	price numeric(10, 2) not null check (price > 0),
	currency text not null,
	owner_id int not null,
	inventory_id int not null,
	category_id int not null,

	description text null,
	image_url text null,

	constraint fk_owner foreign key (owner_id) references resource_server.user(id),
	constraint fk_inventory foreign key (inventory_id) references resource_server.product_inventory(id),
	constraint fk_category foreign key (category_id) references resource_server.category(id)
);


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.product_review (
	id int generated by default as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	product_catalog_id int not null,
	reviewer_id int not null,
	five_star_score int not null check (five_star_score > 0 and five_star_score < 6),
	review text null,

	constraint fk_product_catalog foreign key (product_catalog_id) references resource_server.product_catalog(id),
	constraint fk_reviewer foreign key (reviewer_id) references resource_server.user(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.product_catalog_tag (
	id int generated by default as identity primary key,
	created_at timestamp null default now(),

	product_catalog_id int not null,
	tag_id int not null,

	constraint fk_product_catalog foreign key (product_catalog_id) references resource_server.product_catalog(id),
	constraint fk_tag foreign key (tag_id) references resource_server.tag(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.address (
	id int generated by default as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	user_id int not null,

	address_line_1 text not null,
	address_line_2 text not null,

	country text not null,
	city text not null,
	postal_code text not null,

	constraint fk_user foreign key (user_id) references resource_server.user(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.transaction (
	id int generated by default as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	buyer_id int not null,
	address_id int not null,
	transaction_status text not null,

	constraint fk_buyer foreign key (buyer_id) references resource_server.user(id),
	constraint fk_address foreign key (address_id) references resource_server.address(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.bought_product (
	id int generated by default as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	name text not null,
	price numeric(10, 2) not null check (price > 0),
	currency text not null,
	owner_id int not null,
	category_id int not null,

	description text null,
	image_url text null,

	constraint fk_owner foreign key (owner_id) references resource_server.user(id),
	constraint fk_category foreign key (category_id) references resource_server.category(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

create table if not exists resource_server.order (
	id int generated always as identity primary key,
	created_at timestamp null default now(),
	updated_at timestamp null default now(),

	product_catalog_id int not null,
	transaction_id int not null,

	constraint fk_product_catalog foreign key (product_catalog_id) references resource_server.product_catalog(id),
	constraint fk_transaction foreign key (transaction_id) references resource_server.transaction(id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

grant all privileges on schema resource_server to resource_server;
grant all privileges on database resource_server_db to resource_server;
grant all privileges on all tables in schema resource_server to resource_server;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
