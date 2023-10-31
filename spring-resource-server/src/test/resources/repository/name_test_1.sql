insert into resource_server.user (id, email, user_money, currency) values (100, 'email1.com', 1200.00, 'USD');
insert into resource_server.user (id, email, user_money, currency) values (200, 'email2.com', 1200.00, 'USD');

insert into resource_server.category (id, name) values (100, 'AAA');

insert into resource_server.product_inventory (id, quantity, products_bought) values (100, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (200, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (300, 100, 0);

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (100, 'aaa', 10000.00, 'USD', 100, 100, 100);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (200, 'abb', 20000.00, 'USD', 100, 200, 100);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (300, 'bbb', 30000.00, 'USD', 100, 300, 100);

insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (100, 200, 100, 1);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (200, 200, 100, 2);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (300, 200, 100, 2);

insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (400, 200, 200, 4);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (500, 200, 200, 4);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (600, 200, 200, 5);

insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (700, 200, 300, 2);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (800, 200, 300, 2);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (900, 200, 300, 3);
