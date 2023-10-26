insert into resource_server.user (id, email, user_money, currency) values (100, 'email1.com', 1200.00, 'USD');

insert into resource_server.category (id, name) values (100, 'Chairs');
insert into resource_server.category (id, name) values (200, 'Food');
insert into resource_server.category (id, name) values (300, 'Games');

insert into resource_server.product_inventory (id, quantity, products_bought) values (100, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (200, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (300, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (400, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (500, 100, 0);

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (100, 'Chair 1', 100.00, 'USD', 100, 100, 100);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (200, 'Apple', 100.00, 'USD', 100, 200, 200);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (300, 'Bear', 100.00, 'USD', 100, 300, 200);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (400, 'Chair 2', 100.00, 'USD', 100, 400, 100);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (500, 'Game 1', 100.00, 'USD', 100, 500, 300);

insert into resource_server.tag (id, name, value) values (100, 'China');
insert into resource_server.tag (id, name, value) values (200, 'Poland');
insert into resource_server.tag (id, name, value) values (300, 'Thailand');
insert into resource_server.tag (id, name, value) values (400, 'Germany');
insert into resource_server.tag (id, name, value) values (500, 'USA');

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (100, 100);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (100, 200);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (100, 300);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (200, 200);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (200, 300);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (200, 400);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (300, 300);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (300, 400);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (300, 500);

insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (400, 100);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (400, 200);
insert into resource_server.product_catalog_tag (product_catalog_id, tag_id) values (400, 500);

-- insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (400, 100, 200, 4);
-- insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (500, 100, 200, 4);
-- insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (600, 100, 200, 5);
--
-- insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (700, 100, 300, 2);
-- insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (800, 100, 300, 2);
-- insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (900, 100, 100, 3);
