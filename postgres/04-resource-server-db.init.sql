\c resource_server_db;

insert into resource_server.user (id, email, user_money, currency) values (1, 'eve@gmail.com', 1102.1, 'USD');
insert into resource_server.user (id, email, user_money, currency) values (2, 'adam@gmail.com', 3111.8, 'USD');
insert into resource_server.user (id, email, user_money, currency) values (3, 'bob@gmail.com', 1412.4, 'USD');

insert into resource_server.category (id, name) values (1, 'Food');
insert into resource_server.category (id, name) values (2, 'Games');
insert into resource_server.category (id, name) values (3, 'Toys');
insert into resource_server.category (id, name) values (4, 'Books');
insert into resource_server.category (id, name) values (5, 'Fashion');
insert into resource_server.category (id, name) values (6, 'Beverages');

insert into resource_server.tag (id, name) values (1, 'For Kids');
insert into resource_server.tag (id, name) values (2, 'Green');
insert into resource_server.tag (id, name) values (3, 'Red');
insert into resource_server.tag (id, name) values (4, 'Classic');
insert into resource_server.tag (id, name) values (5, 'Sci-Fi');

insert into resource_server.tag (id, name) values (6, 'Fruits');
insert into resource_server.tag (id, name) values (7, 'Not For Kids');
insert into resource_server.tag (id, name) values (8, 'Non Alcoholic');
insert into resource_server.tag (id, name) values (9, 'Craft Bear');
insert into resource_server.tag (id, name) values (10, 'No Added Sugars');

insert into resource_server.tag (id, name) values (11, 'Organic');
insert into resource_server.tag (id, name) values (12, 'Bio');
insert into resource_server.tag (id, name) values (13, 'Factory builder');
insert into resource_server.tag (id, name) values (14, 'Russian Literature');
insert into resource_server.tag (id, name) values (15, 'Romantic');

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

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (1, 'Apples 1KG', 10, 'USD', 1, 1, 1, 'Very good and tastefull organic apples');
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (2, 'Oranges 1KG', 20, 'USD', 1, 2, 1, 'Very good ogranges');
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (3, 'Pears 1KG', 25, 'USD', 1, 3, 1, 'Very good and sweet pears');

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (4, 'Coca-Cola bottle', 2, 'USD', 1, 4, 6, 'Everybody knows what coke is');
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (5, 'Beer', 3.50, 'USD', 1, 5, 6, 'Home made bear');

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (6, 'Counter Strike Global Offensive', 10, 'USD', 2, 6, 2, 'Everybody played CS once in their life');
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (7, 'Minecraft', 19.99, 'USD', 2, 7, 2, 'Everybody played this once in their life');
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (8, 'Factorio', 19.99, 'USD', 2, 8, 2, 'Very good game');

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (9, '1984', 9.99, 'USD', 3, 9, 4, 'Literaly 1984');
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (10, 'Solaris', 9.99, 'USD', 3, 10, 4, 'Written by stanislaw lem');
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id, description) values (11, 'Crime and Punishment', 8.99, 'USD', 3, 11, 4, 'You should read this');

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 6);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 10);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 11);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (1, 12);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 6);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 10);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 11);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (2, 12);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 6);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 10);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 11);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (3, 12);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (4, 8);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (4, 10);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (5, 9);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (5, 10);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (6, 7);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (7, 1);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (7, 4);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (8, 5);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (8, 7);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (8, 13);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (9, 4);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (10, 4);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (10, 14);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (11, 4);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (11, 5);

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
alter table resource_server.tag alter column id restart with 2000;
alter table resource_server.category alter column id restart with 2000;
