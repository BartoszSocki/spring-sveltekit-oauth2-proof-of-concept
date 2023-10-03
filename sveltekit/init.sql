create table if not exists users (
	id uuid not null primary key,
	email text not null,
	access_token text null,
	refresh_token text null,

	created_at timestamp,
	updated_at timestamp
);

create table if not exists sessions (
	id varchar(32) not null primary key,
	ip_address text,
	expiration_date timestamp with time zone,
	expiration_date_idle timestamp with time zone,
	user_id uuid not null,

	state text null,

	created_at timestamp,
	updated_at timestamp,

	constraint fk_user_id foreign key (user_id) references users(id) on delete cascade
);

insert into users (id, email) values ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'anonymous')
