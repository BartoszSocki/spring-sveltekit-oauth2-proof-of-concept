insert into resource_server.user (id, email, user_money, currency) values (1, 'email1.com', 1200.00, 'USD');
insert into resource_server.user (id, email, user_money, currency) values (2, 'email2.com', 1200.00, 'USD');

insert into resource_server.category (id, name) values (1, 'AAA');

insert into resource_server.product_inventory (id, quantity, products_bought) values (1, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (2, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (3, 100, 0);

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (1, 'aaa', 100.00, 'USD', 1, 1, 1);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (2, 'abb', 200.00, 'USD', 1, 2, 1);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (3, 'bbb', 300.00, 'USD', 1, 3, 1);

insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (1, 2, 1, 1);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (2, 2, 1, 2);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (3, 2, 1, 2);

insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (4, 2, 2, 4);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (5, 2, 2, 4);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (6, 2, 2, 5);

insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (7, 2, 3, 2);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (8, 2, 3, 2);
insert into resource_server.product_review (id, reviewer_id, product_catalog_id, five_star_score) values (9, 2, 3, 3);
