\c resource_server_db;

insert into resource_server.user (id, email, user_money, currency) values (1, 'eve@gmail.com', 1102.1, 'USD');
insert into resource_server.user (id, email, user_money, currency) values (2, 'adam@gmail.com', 3111.8, 'USD');
insert into resource_server.user (id, email, user_money, currency) values (3, 'bob@gmail.com', 1412.4, 'USD');

insert into resource_server.category (name) values ('Food');
insert into resource_server.category (name) values ('Games');
insert into resource_server.category (name) values ('Toys');
insert into resource_server.category (name) values ('Books');
insert into resource_server.category (name) values ('Fashion');
insert into resource_server.category (name) values ('Beverages');

insert into resource_server.tag (name) values ('For Kids');
insert into resource_server.tag (name) values ('Green');
insert into resource_server.tag (name) values ('Red');
insert into resource_server.tag (name) values ('Classic');
insert into resource_server.tag (name) values ('Sci-Fi');

insert into resource_server.tag (name) values ('Fruits');
insert into resource_server.tag (name) values ('Not For Kids');
insert into resource_server.tag (name) values ('Non Alcoholic');
insert into resource_server.tag (name) values ('Craft Bear');
insert into resource_server.tag (name) values ('No Added Sugars');

insert into resource_server.tag (name) values ('Organic');
insert into resource_server.tag (name) values ('Bio');
insert into resource_server.tag (name) values ('Factory builder');
insert into resource_server.tag (name) values ('Russian Literature');
insert into resource_server.tag (name) values ('Romantic');

insert into resource_server.product_inventory (id, quantity) values (1, 100);
insert into resource_server.product_inventory (id, quantity) values (2, 200);
insert into resource_server.product_inventory (id, quantity) values (3, 300);
insert into resource_server.product_inventory (id, quantity) values (4, 400);
insert into resource_server.product_inventory (id, quantity) values (5, 100);
insert into resource_server.product_inventory (id, quantity) values (6, 200);
insert into resource_server.product_inventory (id, quantity) values (7, 300);
insert into resource_server.product_inventory (id, quantity) values (8, 400);
insert into resource_server.product_inventory (id, quantity) values (9, 50);
insert into resource_server.product_inventory (id, quantity) values (10, 80);
insert into resource_server.product_inventory (id, quantity) values (11, 10);

insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (1, 'Apples 1KG', 10, 'USD', 1, 'Food', 'Very good and tastefull organic apples');
insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (2, 'Oranges 1KG', 20, 'USD', 2, 'Food', 'Very good ogranges');
insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (3, 'Pears 1KG', 25, 'USD', 3, 'Food', 'Very good and sweet pears');

insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (4, 'Coca-Cola bottle', 2, 'USD', 4, 'Beverages', 'Everybody knows what coke is');
insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (5, 'Beer', 3.50, 'USD', 5, 'Beverages', 'Home made bear');

insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (6, 'Counter Strike Global Offensive', 10, 'USD', 6, 'Games', 'Everybody played CS once in their life');
insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (7, 'Minecraft', 19.99, 'USD', 7, 'Games', 'Everybody played this once in their life');
insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (8, 'Factorio', 19.99, 'USD', 8, 'Games', 'Very good game');

insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (9, '1984', 9.99, 'USD', 9, 'Books', 'Literaly 1984');
insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (10, 'Solaris', 9.99, 'USD', 10, 'Books', 'Written by stanislaw lem');
insert into resource_server.product_catalog (id, name, price, currency, inventory_id, category_id, description) values (11, 'Crime and Punishment', 8.99, 'USD', 11, 'Books', 'You should read this');

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 'Fruits');
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 'Organic');
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 'Bio');
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 'No Added Sugars');

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 6); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 10); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 11); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 12); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 6); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 10); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 11); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 12); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (4, 8); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (4, 10); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (5, 9); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (5, 10); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (6, 7); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (7, 1); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (7, 4); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (8, 5); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (8, 7); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (8, 13); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (9, 4); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (10, 4); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (10, 14); */

/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (11, 4); */
/* insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (11, 5); */

insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (1, 1, 4);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (1, 2, 3);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (1, 3, 5);

insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (2, 1, 5);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (2, 2, 5);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (2, 3, 5);

insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (3, 1, 4);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (3, 2, 4);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (3, 3, 3);

insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (4, 1, 4);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (4, 2, 1);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (4, 3, 3);

insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (5, 1, 5);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (5, 2, 4);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (5, 3, 4);

insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (6, 1, 5);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (6, 2, 5);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (6, 3, 5);

insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (7, 1, 4);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (7, 2, 4);
insert into resource_server.product_review (product_catalog_id, reviewer_id, five_star_score) values (7, 3, 1);

-- -- -- -- --

alter table resource_server.user alter column id restart with 2000;
alter table resource_server.product_inventory alter column id restart with 2000;
alter table resource_server.product_catalog alter column id restart with 2000;
alter table resource_server.product_review alter column id restart with 2000;
