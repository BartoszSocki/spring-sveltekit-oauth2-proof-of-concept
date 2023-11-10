insert into resource_server.user (id, email, user_money, currency) values (100, 'email1.com', 1200.00, 'USD');
insert into resource_server.user (id, email, user_money, currency) values (200, 'email2.com', 1200.00, 'USD');

insert into resource_server.category (id, name) values (100, 'AAA');

insert into resource_server.product_inventory (id, quantity, products_bought) values (100, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (200, 100, 0);
insert into resource_server.product_inventory (id, quantity, products_bought) values (300, 100, 0);

insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (100, 'aaa', 10000.00, 'USD', 100, 100, 100);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (200, 'abb', 20000.00, 'USD', 100, 200, 100);
insert into resource_server.product_catalog (id, name, price, currency, owner_id, inventory_id, category_id) values (300, 'bbb', 30000.00, 'USD', 100, 300, 100);