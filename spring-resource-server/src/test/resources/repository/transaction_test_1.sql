insert into resource_server.user (id, email, user_money, currency) values (100, 'email.com', 1200.00, 'USD');

insert into resource_server.category (id, name) values (100, 'AAA');
insert into resource_server.category (id, name) values (200, 'BBB');
insert into resource_server.category (id, name) values (300, 'CCC');

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
